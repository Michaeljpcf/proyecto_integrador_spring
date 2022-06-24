package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.PaymentReceipt;

public interface PaymentReceiptService {
	
	public PaymentReceipt createPaymentReceipt(PaymentReceipt obj);
	public List<PaymentReceipt> findAll();

}
