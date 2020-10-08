package com.knila.machine.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knila.machine.entity.ViewCart;

public interface IViewCartRepo extends JpaRepository<ViewCart, Integer>{
	@Transactional
	  @Modifying 
	@Query(value = "DELETE FROM ViewCart vc WHERE vc.billId = :billId") 
	 int deleteByBillId(@Param("billId") int billId);
}
