package com.knila.machine.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillSearchCriteriaDTO {
	
	private String payerName;
	
	private String payerAddress;
	
	private String accountNumber;
}
