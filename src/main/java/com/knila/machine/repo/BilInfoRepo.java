package com.knila.machine.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knila.machine.dto.BillDTO;
import com.knila.machine.entity.BilInfo;
import com.knila.machine.query.helper.BillSearchQueryCriteria;
@Repository
public interface BilInfoRepo extends JpaRepository<BilInfo, Integer> {
	List<BilInfo> findByAccountNumberContainingOrPayerNameContainingOrPayerAddressContaining(
			  int accountNumber, String payerName, String payerAddress);
	
	public List<BillDTO> getBillInfo(BillSearchQueryCriteria  criteria) throws Exception;
	
}
