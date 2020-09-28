package com.knila.machine.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="View_Cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="view_cart_id")
	private Integer viewCartId;
	
	@Column(name="bill_id")
	private Integer billId;
	
	@Column(name="payer_name")
	private String payerName;
	
	@Column(name="payer_address")
	private String payerAddress;
	
	@Column(name="due_date")
	private Date dueDate;
	
	
	@Column(name="account_number")
	private String accountNumber;
	
	
	@Column(name="amount")
	private String amount;

}
