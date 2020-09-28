package com.knila.machine.domain;

import java.util.List;

import com.knila.machine.dto.BillDTO;
import com.knila.machine.dto.BillSearchCriteriaDTO;

public interface IViewCartDomain {
	public List<BillDTO> searchBill(BillSearchCriteriaDTO billsearch) throws Exception;
	
	public BillDTO addCart(BillDTO billInfo);
	
	public BillDTO removeCart(int billId);
	
	public List<BillDTO>  viewCart();
}
