package com.shoestoremanagement.shoe_management_service.Repositories;

import com.shoestoremanagement.shoe_management_service.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

   List<Item> findByName(String name);
//   List<Item> findByType_id(Long typeId);


}
