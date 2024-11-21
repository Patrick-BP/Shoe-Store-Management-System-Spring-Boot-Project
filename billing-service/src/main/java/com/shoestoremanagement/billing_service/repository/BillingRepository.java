package com.shoestoremanagement.billing_service.repository;

import com.shoestoremanagement.billing_service.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByOrderDate(Timestamp orderDate);
    List<Billing> findByCustomerName(String customerName);
    List<Billing> findByOrderNo(String orderNo);
}
