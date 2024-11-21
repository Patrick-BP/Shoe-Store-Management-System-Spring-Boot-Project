package com.shoestoremanagement.billing_service.controller;

import com.shoestoremanagement.billing_service.DTO.BillingDTO;
import com.shoestoremanagement.billing_service.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping("/new")
    public ResponseEntity<BillingDTO> createBilling(@RequestBody BillingDTO billingDTO) {
        return new ResponseEntity<>(billingService.createBilling(billingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getBillingById(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillingById(id));
    }

    @GetMapping("all")
    public ResponseEntity<List<BillingDTO>> getAllBillings() {
        return ResponseEntity.ok(billingService.getAllBillings());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<BillingDTO>> getBillingsByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp date) {
        return ResponseEntity.ok(billingService.getBillingsByDate(date));
    }

    @GetMapping("/order")
    public ResponseEntity<List<BillingDTO>> getBillingsByOrder(
            @RequestParam String order) {

        return ResponseEntity.ok(billingService.getBillingsByOrder(order));
    }

    @GetMapping("/customer")
    public ResponseEntity<List<BillingDTO>> getBillingsByCustomer(
            @RequestParam String name) {

        return ResponseEntity.ok(billingService.getBillingsByCustomer(name));
    }

}
