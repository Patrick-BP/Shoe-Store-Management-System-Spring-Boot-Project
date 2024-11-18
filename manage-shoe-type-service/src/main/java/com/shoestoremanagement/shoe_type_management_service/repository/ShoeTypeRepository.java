package com.shoestoremanagement.shoe_type_management_service.repository;

import com.shoestoremanagement.shoe_type_management_service.entity.ShoeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeTypeRepository extends JpaRepository<ShoeType, Long> {
}
