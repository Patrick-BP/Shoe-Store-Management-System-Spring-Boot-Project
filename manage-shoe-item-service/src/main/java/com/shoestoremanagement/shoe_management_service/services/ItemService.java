package com.shoestoremanagement.shoe_management_service.services;


import com.shoestoremanagement.shoe_management_service.DTO.ItemDTO;
import com.shoestoremanagement.shoe_management_service.Entity.Item;
import com.shoestoremanagement.shoe_management_service.Repositories.ItemsRepository;
import com.shoestoremanagement.shoe_management_service.exceptions.InsufficientStockException;
import com.shoestoremanagement.shoe_management_service.exceptions.ItemNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {


    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository){
        this.itemsRepository = itemsRepository;
    }

    // Create a new item

    public ItemDTO createItem(ItemDTO itemDTO){
        Item item = itemDTO.toEntity();
        return ItemDTO.fromEntity(itemsRepository.save(item));
    }

    // find an Item by ID
    public ItemDTO getItemById(Long itemId){
        Item item = itemsRepository.findById(itemId)
                .orElseThrow(()-> new ItemNotFoundException(itemId));
        return ItemDTO.fromEntity(item);
    }

    //  find all items
    public List<ItemDTO> getAllItems(){
        return itemsRepository.findAll().stream()
                .map(ItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    //update item
    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO){
        Item item = itemsRepository.findById(itemId)
                .orElseThrow(()-> new ItemNotFoundException(itemId));

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setType_id(itemDTO.getType_id());

        return ItemDTO.fromEntity(itemsRepository.save(item));
    }

    // Delete an Item
    public void deleteItem(Long itemId){
        if(!itemsRepository.existsById(itemId)){
            throw new ItemNotFoundException(itemId);
        }
        itemsRepository.deleteById(itemId);


    }

    // update Stock
    public ItemDTO updateStock(Long itemId, int quantity){
        Item item = itemsRepository.findById(itemId)
                .orElseThrow(()-> new ItemNotFoundException(itemId));

        if(item.getQuantity() + quantity < 0){
            throw new InsufficientStockException("Not enough stock available");
        }
        item.setQuantity(item.getQuantity() + quantity);
        return ItemDTO.fromEntity(itemsRepository.save(item));
    }

}
