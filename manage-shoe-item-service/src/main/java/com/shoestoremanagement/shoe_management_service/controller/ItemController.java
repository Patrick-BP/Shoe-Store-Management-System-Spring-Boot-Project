package com.shoestoremanagement.shoe_management_service.controller;

import com.shoestoremanagement.shoe_management_service.DTO.ItemDTO;
import com.shoestoremanagement.shoe_management_service.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shoe-item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createNewItem(@Valid @RequestBody ItemDTO item){
        System.out.println(item);
         itemService.createItem(item);
        return item;
    }


    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }


    @GetMapping("/all")
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @PutMapping("/{id}")
    public ItemDTO updateItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO){
        return itemService.updateItem(id, itemDTO);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
    }

    @PatchMapping("/{id}/stock")
    public ItemDTO updateStock(@PathVariable Long id, @RequestParam int quantity){
        return itemService.updateStock(id, quantity);
    }
}
