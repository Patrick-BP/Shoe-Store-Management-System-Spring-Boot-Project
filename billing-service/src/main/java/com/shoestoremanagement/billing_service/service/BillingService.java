package com.shoestoremanagement.billing_service.service;

import com.shoestoremanagement.billing_service.DTO.BillingDTO;
import com.shoestoremanagement.billing_service.entity.Billing;
import com.shoestoremanagement.billing_service.exceptions.BillingNotFoundException;
import com.shoestoremanagement.billing_service.exceptions.InvalidBillingDataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.shoestoremanagement.billing_service.repository.BillingRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;


    public BillingDTO createBilling(BillingDTO billingDTO) {

        billingDTO.calculateTotal();

        Billing billing = billingDTO.toEntity();
        billing = billingRepository.save(billing);

        return BillingDTO.fromEntity(billing);
    }


    public BillingDTO getBillingById(Long id) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new BillingNotFoundException("Billing not found with id: " + id));
        return BillingDTO.fromEntity(billing);
    }


    public List<BillingDTO> getAllBillings() {
        return billingRepository.findAll().stream()
                .map(BillingDTO::fromEntity)
                .collect(Collectors.toList());
    }



    public void deleteBilling(Long id) {
        if (!billingRepository.existsById(id)) {
            throw new BillingNotFoundException("Billing not found with id: " + id);
        }

        billingRepository.deleteById(id);

    }


    public List<BillingDTO> getBillingsByDate(Timestamp date) {
        return billingRepository.findByOrderDate(date).stream()
                .map(BillingDTO::fromEntity)
                .collect(Collectors.toList());
    }


    public List<BillingDTO> getBillingsByCustomer(String customerName) {
        return billingRepository.findByCustomerName(customerName).stream()
                .map(BillingDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BillingDTO> getBillingsByOrder(String orderNo) {
        return billingRepository.findByOrderNo(orderNo).stream()
                .map(BillingDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private void validateBillingData(BillingDTO billingDTO) {
        if (billingDTO == null) {
            throw new InvalidBillingDataException("Billing data cannot be null");
        }
        if (billingDTO.getQuantity() <= 0) {
            throw new InvalidBillingDataException("Quantity must be greater than 0");
        }
        if (billingDTO.getUnitPrice() == null || billingDTO.getUnitPrice().signum() <= 0) {
            throw new InvalidBillingDataException("Unit price must be greater than 0");
        }
        if (billingDTO.getCustomerName() == null || billingDTO.getCustomerName().trim().isEmpty()) {
            throw new InvalidBillingDataException("Customer name cannot be empty");
        }
    }

}
