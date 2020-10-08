package com.knila.machine.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knila.machine.dto.BillDTO;
import com.knila.machine.dto.BillSearchCriteriaDTO;
import com.knila.machine.service.IViewCartService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ViewCartController {

	
	@Autowired
	IViewCartService viewCartService;
	
	
	@PostMapping(value="/searchBill")
	public ResponseEntity<?> searchBill(@RequestBody BillSearchCriteriaDTO billsearch, Errors errors) throws Exception {
		return  ResponseEntity.ok(viewCartService.searchBill(billsearch));	
	}

	@PostMapping(value="/addCart")
	public ResponseEntity<?> addCart(@RequestBody BillDTO billInfo, Errors errors) {
		return  ResponseEntity.ok(viewCartService.addCart(billInfo));	
	}
	
	@DeleteMapping(value="/removeCart/{billId}")
	public ResponseEntity<?> removeCart(@PathVariable String billId) {
		
		return  ResponseEntity.ok(viewCartService.removeCart(Integer.parseInt(billId)));
		
	}
	
	@GetMapping(value="/viewCart")
	public ResponseEntity<?> viewCart() {
		
		return  ResponseEntity.ok(viewCartService.viewCart());
		
	}
	

	
}
