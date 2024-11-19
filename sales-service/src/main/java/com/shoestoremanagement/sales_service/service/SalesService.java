package com.shoestoremanagement.sales_service.service;

import com.shoestoremanagement.sales_service.Exceptions.ResourceNotFoundException;
import com.shoestoremanagement.sales_service.entity.Sale;
import com.shoestoremanagement.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sale> getAllSales() {
        return salesRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Sale not found with id: " + id));
    }

    public Sale createSale(Sale sale) {
        return salesRepository.save(sale);
    }

    public Sale updateSale(Long id, Sale saleDetails) {
        Sale existingSale = getSaleById(id);
        existingSale.setCustomerName(saleDetails.getCustomerName());
        existingSale.setShoeId(saleDetails.getShoeId());
        existingSale.setQuantity(saleDetails.getQuantity());
        existingSale.setTotalPrice(saleDetails.getTotalPrice());
        existingSale.setSaleDate(saleDetails.getSaleDate());
        return salesRepository.save(existingSale);
    }

    public Sale patchSale(Long id, Map<String, Object> updates) {
        Sale existingSale = getSaleById(id);

        // Map of field names to their respective update logic
        Map<String, BiConsumer<Sale, Object>> fieldUpdaters = Map.of(
                "customerName", (sale, value) -> sale.setCustomerName((String) value),
                "shoeId", (sale, value) -> sale.setShoeId(Long.valueOf(value.toString())),
                "quantity", (sale, value) -> sale.setQuantity(Integer.valueOf(value.toString())),
                "totalPrice", (sale, value) -> sale.setTotalPrice(Double.valueOf(value.toString())),
                "saleDate", (sale, value) -> sale.setSaleDate(LocalDateTime.parse((String) value)) // Adjust format if needed
        );

        // Apply updates
        updates.forEach((key, value) -> {
            BiConsumer<Sale, Object> updater = fieldUpdaters.get(key);
            if (updater != null) {
                updater.accept(existingSale, value);
            } else {
                throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return salesRepository.save(existingSale);
    }

    public void deleteSale(Long id) {
        Sale existingSale = getSaleById(id);
        salesRepository.delete(existingSale);
    }


}
