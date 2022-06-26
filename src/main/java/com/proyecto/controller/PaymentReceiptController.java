package com.proyecto.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.PaymentReceipt;
import com.proyecto.service.PaymentReceiptService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/paymentReceipt")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PaymentReceiptController {

	@Autowired
	private PaymentReceiptService paymentReceiptService;
	
	@PostMapping("/create")	
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createPaymentReceipt(@RequestBody PaymentReceipt paymentReceipt) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {



			for (int i = 0; i < 12; i++) {
				Date date = paymentReceipt.getPaymentDate();
				PaymentReceipt receipt = new PaymentReceipt();

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
				cal.add(Calendar.MONTH, i);
				cal.add(Calendar.DATE, 5);
				
				Calendar payment = Calendar.getInstance();
				payment.setTime(date);
				payment.set(Calendar.DATE, payment.getActualMaximum(Calendar.DATE));
				payment.add(Calendar.MONTH, i);
				
				int month = cal.get(Calendar.MONTH);
				
				receipt.setMonth(month);			
				receipt.setPaymentDate(payment.getTime());
				receipt.setExpirationDate(cal.getTime());
				receipt.setAmount(paymentReceipt.getAmount());
				receipt.setTypeService(paymentReceipt.getTypeService());
				receipt.setTower(paymentReceipt.getTower());
				receipt.setUser(paymentReceipt.getUser());
				System.out.println("Valor de i " + i);
				PaymentReceipt responseService = paymentReceiptService.createPaymentReceipt(receipt);
				System.out.println("Response del Service >>> " + responseService);
			}
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear la boleta de pago en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Boleta de Pago", paymentReceipt);		
		response.put("mensaje", "Boleta de pago creado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
