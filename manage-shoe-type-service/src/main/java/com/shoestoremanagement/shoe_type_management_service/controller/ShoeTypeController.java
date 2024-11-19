package com.shoestoremanagement.shoe_type_management_service.controller;

import com.shoestoremanagement.shoe_type_management_service.entity.ShoeType;
import com.shoestoremanagement.shoe_type_management_service.service.ShoeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shoe-types")
public class ShoeTypeController {

    @Autowired
    private ShoeTypeService shoeTypeService;

    @GetMapping
    public ResponseEntity<List<ShoeType>> getAllShoeTypes() {
        return ResponseEntity.ok(shoeTypeService.getAllShoeTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoeType> getShoeTypeById(@PathVariable Long id) {
        Optional<ShoeType> shoeType = shoeTypeService.getShoeTypeById(id);
        return shoeType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShoeType> addShoeType(@RequestBody ShoeType shoeType) {
        ShoeType createdShoeType = shoeTypeService.addShoeType(shoeType);
        return ResponseEntity.status( HttpStatus.CREATED).body(createdShoeType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoeType> updateShoeType(@PathVariable Long id, @RequestBody ShoeType shoeType) {
        ShoeType updatedShoeType = shoeTypeService.updateShoeType(id, shoeType);
        return updatedShoeType != null ? ResponseEntity.ok(updatedShoeType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoeType(@PathVariable Long id) {
        shoeTypeService.deleteShoeType(id);
        return ResponseEntity.noContent().build();
    }
}