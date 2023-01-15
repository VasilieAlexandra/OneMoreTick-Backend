package com.app.onemoretick.service;

import com.app.onemoretick.model.ItemList;
import com.app.onemoretick.model.ShoppingList;

import java.util.List;

public interface ItemListService {
    ItemList addItemList(ItemList itemList);
    ItemList getById(Integer id);
    ItemList updateItemList(ItemList itemList);
    void deleteItemList(Integer id);

    List<ItemList> getAllByShoppingList(ShoppingList shoppingList);
}
