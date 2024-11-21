package com.shoestoremanagement.shoe_management_service.Entity;


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
@Table(name = "shoe-items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "item_code")
    private String item_code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Long type_id;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "created_at")
    private Timestamp createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
    }



}
