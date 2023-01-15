package com.app.onemoretick.repository;

import com.app.onemoretick.model.ItemList;
import com.app.onemoretick.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Integer> {
 public List<ItemList> getAllByIdShoppingList(ShoppingList shoppingList);
}
