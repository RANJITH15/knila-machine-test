package com.knila.machine.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knila.machine.dto.BillDTO;
import com.knila.machine.dto.BillSearchCriteriaDTO;
import com.knila.machine.entity.BilInfo;
import com.knila.machine.entity.ViewCart;
import com.knila.machine.query.helper.BillSearchQueryCriteria;
import com.knila.machine.repo.BilInfoRepo;
import com.knila.machine.repo.IViewCartRepo;
@Component
public class ViewCartDomainImpl implements IViewCartDomain {
	@Autowired
	private BilInfoRepo bilInfoRepo;
	
	@Autowired
	private IViewCartRepo viewCartRepo;
	
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	
	public List<BillDTO> searchBill(BillSearchCriteriaDTO billsearch) throws Exception {
		 List<BillDTO> billList=new ArrayList<BillDTO>(); 
		 /*
		 int can=Integer.parseInt(billsearch.getAccountNumber());
		 String payerAddress=billsearch.getPayerName();
		 String payerName=billsearch.getPayerAddress();
		
		 if(can>0&&payerName!=null&&payerAddress!=null) {
			 billInfo= bilInfoRepo.findByAccountNumberContainingOrPayerNameContainingOrPayerAddressContaining(
					 can, payerName, payerAddress);	
		 }
		 
		 Iterator<BilInfo> iterator=billInfo.iterator();
	if(iterator!=null&& iterator.hasNext()) {
		while(iterator.hasNext()) {
			BillDTO billDTO=dozerBeanMapper.map(iterator.next(), BillDTO.class);
			billList.add(billDTO);
		}
		
	} */

		 BillSearchQueryCriteria creteria=new BillSearchQueryCriteria("test",billsearch);
		 billList=bilInfoRepo.getBillInfo(creteria);
		return billList;
	}
	
	
	public BillDTO addCart(BillDTO billInfo) {
		ViewCart viewCart=new ViewCart();
		if(billInfo!=null) {
			viewCart.setBillId(billInfo.getBillId());
			viewCart.setPayerName(billInfo.getPayerName());
			viewCart.setPayerAddress(billInfo.getPayerAddress());
			viewCart.setDueDate(billInfo.getDueDate());
			viewCart.setAccountNumber(billInfo.getAccountNumber());
			viewCart.setAmount(billInfo.getAmount());
		}
		
		 if(viewCartRepo.save(viewCart)!=null) {
			 return billInfo;
		 }
		 else {
			 return new BillDTO();
		 }
	}
	
	
	public BillDTO removeCart(int billId) {
		int count=viewCartRepo.deleteByBillId(billId);
		 return new BillDTO();
	}
	
	
	public List<BillDTO> viewCart() {
		 List<BillDTO> billList=new ArrayList<BillDTO>(); 
		 
		 List<ViewCart> viewCart=new ArrayList<ViewCart>();
		 
		 viewCart= viewCartRepo.findAll();
		 
		 Iterator<ViewCart> iterator=viewCart.iterator();
	if(iterator!=null&& iterator.hasNext()) {
		while(iterator.hasNext()) {
			BillDTO billDTO=dozerBeanMapper.map(iterator.next(), BillDTO.class);
			billList.add(billDTO);
		}
		
	}
		return billList;
	}
	
	
}
