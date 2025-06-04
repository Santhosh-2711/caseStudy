package com.billing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long billno;
	private Double totalpaid;
	private String paymentStatus;
	private String paymentTime;
	private String transactionId;
	
	public PaymentDetails() {
		super();
	}

	public PaymentDetails(Long id, Long billno, Double totalpaid, String paymentStatus, String paymentTime,
			String transactionId) {
		super();
		this.id = id;
		this.billno = billno;
		this.totalpaid = totalpaid;
		this.paymentStatus = paymentStatus;
		this.paymentTime = paymentTime;
		this.transactionId = transactionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillno() {
		return billno;
	}

	public void setBillno(Long billno) {
		this.billno = billno;
	}

	public Double getTotalpaid() {
		return totalpaid;
	}

	public void setTotalpaid(Double totalpaid) {
		this.totalpaid = totalpaid;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	

}
