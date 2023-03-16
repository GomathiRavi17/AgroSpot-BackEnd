package com.dealer.model;

import java.time.LocalDate;

public class OrderInfo {
	private double totPrice;
	private int did;
	private int fid;
	private int cid;
	private int qty;
	private LocalDate date;

	public OrderInfo() {
		super();
	}

	public OrderInfo(double totPrice, int did, int fid, int cid, int qty, LocalDate date) {
		super();
		this.totPrice = totPrice;
		this.did = did;
		this.fid = fid;
		this.cid = cid;
		this.qty = qty;
		this.date = date;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "OrderInfo [totPrice=" + totPrice + ", did=" + did + ", fid=" + fid + ", cid=" + cid + ", qty=" + qty
				+ ", date=" + date + "]";
	}

}
