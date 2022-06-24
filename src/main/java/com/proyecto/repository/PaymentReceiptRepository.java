package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.PaymentReceipt;

public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, Integer> {	
	
//	@Query("select pr from payment_receipts pr where (:p_iduser is '' or pr.user.dni like :p_iduser) "
//			+ "and (:p_nom is '' or m.nombre like :p_nom) "
//			+ "and (:p_cert is '' or m.certificado like :p_cert) "
//			+ "and (:p_idtypeservice is 0  or pr.pais.idPais = :p_idtypeservice)")
//	public List<PaymentReceipt> filterByDniByNameByServiceByStatus(@Param("p_iduser") Integer iduser, @Param("p_nom") String nom,
//																   @Param("p_status") String status, @Param("p_idtypeservice") int idtypeservice);

}
