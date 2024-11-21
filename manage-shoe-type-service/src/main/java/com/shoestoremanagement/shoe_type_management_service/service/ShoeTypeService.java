package com.shoestoremanagement.shoe_type_management_service.service;

import com.shoestoremanagement.shoe_type_management_service.entity.ShoeType;
import com.shoestoremanagement.shoe_type_management_service.repository.ShoeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeTypeService {

    @Autowired
    private ShoeTypeRepository shoeTypeRepository;

    public List<ShoeType> getAllShoeTypes() {
        return shoeTypeRepository.findAll();
    }

    public Optional<ShoeType> getShoeTypeById(Long typeId) {
        return shoeTypeRepository.findById(typeId);
    }

    public ShoeType addShoeType(ShoeType shoeType) {
        return shoeTypeRepository.save(shoeType);
    }

    public ShoeType updateShoeType(Long typeId, ShoeType shoeType) {
        if (shoeTypeRepository.existsById(typeId)) {
            shoeType.setType_id(typeId);
            return shoeTypeRepository.save(shoeType);
        }
        return null;
    }

    public void deleteShoeType(Long typeId) {
        shoeTypeRepository.deleteById(typeId);
    }
}