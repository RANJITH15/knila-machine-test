package com.knila.machine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knila.machine.domain.IViewCartDomain;
import com.knila.machine.dto.BillDTO;
import com.knila.machine.dto.BillSearchCriteriaDTO;
@Service
public class ViewCartServiceImpl implements IViewCartService {
	
	@Autowired
	private IViewCartDomain viewCartDomain;
	
	public List<BillDTO> searchBill(BillSearchCriteriaDTO billsearch) throws Exception {
		return viewCartDomain.searchBill(billsearch);
	}
	
	public BillDTO addCart(BillDTO billInfo) {
		return viewCartDomain.addCart(billInfo);
	}
	public BillDTO removeCart(int billId) {
		return viewCartDomain.removeCart(billId);	
	}
	
	public List<BillDTO> viewCart() {
		return viewCartDomain.viewCart();
	}
}
