package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.PaymentReceipt;
import com.proyecto.repository.PaymentReceiptRepository;

@Service
public class PaymentReceiptServiceImpl implements PaymentReceiptService {

	@Autowired
	private PaymentReceiptRepository paymentReceiptRepository;
	
	@Override
	public PaymentReceipt createPaymentReceipt(PaymentReceipt obj) {
		return paymentReceiptRepository.save(obj);
	}

	@Override
	public List<PaymentReceipt> findAll() {
		return paymentReceiptRepository.findAll();
	}

}
