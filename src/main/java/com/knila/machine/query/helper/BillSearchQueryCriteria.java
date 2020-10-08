package com.knila.machine.query.helper;

import org.apache.commons.lang3.StringUtils;

import com.knila.machine.dto.BillSearchCriteriaDTO;

public class BillSearchQueryCriteria implements BaseNativeCriteriaBuilder {
	
private final String queryAction;

BillSearchCriteriaDTO searchObj;

public BillSearchQueryCriteria(String queryAction,BillSearchCriteriaDTO searchObj) {
	super();
	this.queryAction = queryAction;
	this.searchObj=searchObj;
}
@Override
public String queryAction() {
	return null;
}
@Override
public String getCriteriaSQL() {
	final StringBuilder queryStr=new StringBuilder();
	queryStr.append("SELECT BILL_ID as billId,PAYER_NAME as payerName, PAYER_ADDRESS as payerAddress,ACCOUNT_NUMBER as accountNumber,DUE_DATE as dueDate,AMOUNT as amount  FROM BILL_INFO ");
if(searchObj!=null) {
	appendWhereClause(queryStr);
}
	
	return 	queryStr.toString();
}

private void appendWhereClause(final StringBuilder queryStr ) {
	boolean whereCheckFlag=false;
	boolean orCheckFlag=false;
	if(StringUtils.isNotEmpty(searchObj.getAccountNumber())) {
		whereCheckFlag=true;
	}
	if(StringUtils.isNotEmpty(searchObj.getPayerName())) {
		whereCheckFlag=true;
	}
	if(StringUtils.isNotEmpty(searchObj.getPayerAddress())) {
		whereCheckFlag=true;
	}
	
	if(whereCheckFlag) {
		queryStr.append(" where");
	}
	if(StringUtils.isNotEmpty(searchObj.getAccountNumber())) {
		orCheckFlag=true;
		queryStr.append(" ACCOUNT_NUMBER like '%"+searchObj.getAccountNumber()+"%'" );
	}
	
	if(StringUtils.isNotEmpty(searchObj.getPayerAddress())) {
		if(orCheckFlag) {
			queryStr.append(" or PAYER_ADDRESS like '%"+searchObj.getPayerAddress()+"%'" );
		}else {
			orCheckFlag=true;
			queryStr.append(" PAYER_ADDRESS like '%"+searchObj.getPayerAddress()+"%'" );
		}
	}
	
	if(StringUtils.isNotEmpty(searchObj.getPayerName())) {
		if(orCheckFlag) {
			queryStr.append(" or PAYER_NAME like '%"+searchObj.getPayerName()+"%'" );
		}else {
			orCheckFlag=true;
			queryStr.append(" PAYER_NAME like '%"+searchObj.getPayerName()+"%'" );
		}
	}
}
@Override

public String getCriteriaFunction() {
	return null;
}

}
