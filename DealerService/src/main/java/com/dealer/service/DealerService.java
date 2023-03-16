package com.dealer.service;

import java.util.List;

import com.dealer.exception.DealerNotFoundException;
import com.dealer.model.Dealer;


public interface DealerService {
   public List<Dealer> showAllDealers();
   public Dealer showDealerById(long id) throws DealerNotFoundException;
   public Dealer addDealer(Dealer dealer) throws DealerNotFoundException;
   public Dealer updateDealer(Dealer dealer) throws DealerNotFoundException;
   public String deleteDealer(long id) throws DealerNotFoundException;
   public Dealer getDealerByName(String name) throws DealerNotFoundException;
}
