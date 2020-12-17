package com.example.demo.repos;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

//    @Query(value = "SELECT u FROM Items WHERE u.id = :id and u.itemType = :itemType ", nativeQuery = true)
    @Query("select i from Item i where i.id = :id and i.itemType = :itemType")
    Optional<Item> findByIdAndItemType(@Param("id") Long id, @Param("itemType") ItemType itemType);

    Item findFirstByOrderByIdDesc();

    @Query(value = "SELECT * FROM items WHERE item_type = :item_type LIMIT 1",nativeQuery = true)
    Item findFirstByItemType(@Param("item_type") String item_type);

//    @Query(value = "SELECT * FROM items WHERE item_type = :item_type",nativeQuery = true)
    List<Item> findAllByItemType(/*@Param("item_type") */ItemType itemType);

    long countByItemType(ItemType itemType);

}
