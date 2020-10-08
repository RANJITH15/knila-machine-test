package com.knila.machine.dto;

import java.sql.Date;

import com.knila.machine.query.helper.BaseNativeQueryEntity;
import com.knila.machine.query.helper.BaseQueryResultColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@BaseNativeQueryEntity
public class BillDTO {
	@BaseQueryResultColumn(expectedDataType =Integer.class,columnName="billId" )
	private Integer billId;
	@BaseQueryResultColumn(expectedDataType =String.class,columnName="payerName" )
	private String payerName;
	@BaseQueryResultColumn(expectedDataType =String.class,columnName="payerAddress" )
	private String payerAddress;
	@BaseQueryResultColumn(expectedDataType =String.class,columnName="dueDate" )
	private Date dueDate;
	@BaseQueryResultColumn(expectedDataType =Date.class,columnName="accountNumber" )
	private String accountNumber;
	@BaseQueryResultColumn(expectedDataType =String.class,columnName="amount" )
	private String amount;
}
