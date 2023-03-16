package com.payment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payment.exception.InvoiceNotFoundException;
import com.payment.feignclient.DealerFeignClient;
import com.payment.feignclient.InvoiceFeignClient;
import com.payment.model.CartItem;
import com.payment.model.CropDetails;
import com.payment.model.Invoice;
import com.payment.model.PaytmDetailPojo;
import com.paytm.pg.merchant.PaytmChecksum;

@CrossOrigin()
@Controller
public class PaymentController {

	@Autowired
	private PaytmDetailPojo paytmDetailPojo;

	@Autowired
	private Environment env;

	@Autowired
	private DealerFeignClient dealerClient;
	
	@Autowired
	private InvoiceFeignClient invoiceClient;
	
	Invoice invoice = new Invoice();

	@GetMapping("/payment")
	public String home(@RequestParam("orderId") String orderId, @RequestParam("custId") String custId) {
		return "home";
	}

	@PostMapping(value = "/submitPaymentDetail")
	public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
			@RequestParam(name = "TXN_AMOUNT") String transactionAmount,
			@RequestParam(name = "ORDER_ID") String orderId) throws Exception {

		ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetailPojo.getPaytmUrl());
		TreeMap<String, String> parameters = new TreeMap<>();
		paytmDetailPojo.getDetails().forEach((k, v) -> parameters.put(k, v));
		parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
		parameters.put("EMAIL", env.getProperty("paytm.email"));
		parameters.put("ORDER_ID", orderId);
		parameters.put("TXN_AMOUNT", transactionAmount);
		parameters.put("CUST_ID", customerId);
		String checkSum = getCheckSum(parameters);
		parameters.put("CHECKSUMHASH", checkSum);
		modelAndView.addAllObjects(parameters);
		invoice.setDid(parameters.get("CUST_ID"));
		return modelAndView;
	}

	@PostMapping(value = "/pgresponse")
	public String getResponseRedirect(HttpServletRequest request, Model model) {
		List<CartItem> cartItems = dealerClient.getCart().getItem();
		List<CropDetails> crop = new ArrayList<>();
		int quantity = 0;
		for(CartItem item: cartItems) {
			crop.add(item.getCrop());
			quantity = quantity + item.getCrop().getQuantity();
		}
		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum = "";
		for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
			if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
				paytmChecksum = requestParamsEntry.getValue()[0];
			} else {
				parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
			}
		}
		String result;

		boolean isValideChecksum = false;
		System.out.println("RESULT : " + parameters.toString());
		try {
			isValideChecksum = validateCheckSum(parameters, paytmChecksum);
			if (isValideChecksum && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
					result = "Payment Successful";
				} else {
					result = "Payment Failed";
				}
			} else {
				result = "Checksum mismatched";
			}
		} catch (Exception e) {
			result = e.toString();
		}
		model.addAttribute("result", result);
		parameters.remove("CHECKSUMHASH");
        invoice.setId(parameters.get("ORDERID"));
        invoice.setTotPrice(parameters.get("TXNAMOUNT"));
        invoice.setDate(LocalDate.now());
        invoice.setCrops(crop);
        invoice.setQuantity(quantity);
        invoice.setTransId(parameters.get("TXNID"));
		model.addAttribute("parameters", parameters);
		return "report";
	}

	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
		return PaytmChecksum.verifySignature(parameters, paytmDetailPojo.getMerchantKey(), paytmChecksum);
	}

	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetailPojo.getMerchantKey());
	}
	
	@ResponseBody
	@GetMapping("/invoice")
	public Invoice getInvoice(){
		return invoice;
	}
	
	@ResponseBody
	@PostMapping("/addInvoice")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice, @RequestHeader("Authorization") String token) throws InvoiceNotFoundException{
		return invoiceClient.addInvoice(invoice,token);
	}

}
