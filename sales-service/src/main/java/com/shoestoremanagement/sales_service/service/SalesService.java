package com.shoestoremanagement.sales_service.service;

import com.shoestoremanagement.sales_service.Exceptions.ResourceNotFoundException;
import com.shoestoremanagement.sales_service.entity.Sale;
import com.shoestoremanagement.sales_service.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

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

    public void deleteSale(Long id) {
        Sale existingSale = getSaleById(id);
        salesRepository.delete(existingSale);
    }
}
