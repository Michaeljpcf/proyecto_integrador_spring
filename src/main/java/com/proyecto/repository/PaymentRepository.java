package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}
