package com.shoestoremanagement.sales_service.repository;

import com.shoestoremanagement.sales_service.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository <Sale, Long>{
}
