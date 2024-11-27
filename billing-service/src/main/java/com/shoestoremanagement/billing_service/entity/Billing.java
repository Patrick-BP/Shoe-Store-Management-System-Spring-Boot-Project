package com.shoestoremanagement.billing_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "order_number")
    private String orderNo;
    @Column(nullable = false, name = "customer_name")
    private String customerName;
    @Column(nullable = false, name = "customer_mobile")
    private String customerMobile;
    @Column(nullable = false, name= "order_data")
    private Timestamp orderDate;
    @Column(nullable = false, name= "shoe_id")
    private Long shoeId;
    @Column(nullable = false, name = "quatity")
    private int quantity;
    @Column(nullable = false, name = "unit_price")
    private BigDecimal unitPrice;
    @Column(nullable = false, name = "total_price")
    private BigDecimal total;

//    @PrePersist
//    protected void onCreate(){
//        this.orderDate = Timestamp.from(Instant.now());
//    }


}
