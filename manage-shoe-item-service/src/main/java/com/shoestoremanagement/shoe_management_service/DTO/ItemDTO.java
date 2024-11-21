package com.shoestoremanagement.shoe_management_service.DTO;

import com.shoestoremanagement.shoe_management_service.Entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String item_code;
    private String name;
    private String description;
    private BigDecimal price;
    private Long type_id;
    private int quantity;
    private Timestamp createdAt;




    // Convert Entity to DTO
    public static ItemDTO fromEntity(Item item){
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setType_id(item.getType_id());
        dto.setQuantity(item.getQuantity());
        dto.setCreatedAt(item.getCreatedAt());
        dto.setItem_code(item.getItem_code());

        return dto;
    }

    // Convert DTO to Entity
    public  Item toEntity(){
        Item item = new Item();
        item.setId(this.id);
        item.setName(this.name);
        item.setDescription(this.description);
        item.setPrice(this.price);
        item.setQuantity(this.quantity);
        item.setType_id(this.type_id);
        item.setCreatedAt(this.createdAt);
        item.setItem_code(this.getItem_code());


        return item;
    }
}
