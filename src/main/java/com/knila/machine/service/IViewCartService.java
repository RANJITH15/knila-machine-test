package com.knila.machine.service;

import java.util.List;
import com.knila.machine.dto.BillDTO;
import com.knila.machine.dto.BillSearchCriteriaDTO;

public interface IViewCartService {
	
	public List<BillDTO> searchBill(BillSearchCriteriaDTO billsearch) throws Exception;
	
	public BillDTO addCart(BillDTO billInfo);
	
	public BillDTO removeCart(int billId);
	
	public List<BillDTO> viewCart( );
	
}
