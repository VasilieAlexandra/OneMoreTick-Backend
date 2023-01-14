package com.app.onemoretick.service;

import com.app.onemoretick.model.ShoppingList;
import com.app.onemoretick.model.User;

import java.util.List;

public interface ListService {
    ShoppingList addList(ShoppingList shoppingList);
    ShoppingList getById(Integer id);
    ShoppingList updateList(ShoppingList shoppingList);
    void deleteList(Integer id);
    List<ShoppingList> getAllForUser(User user);
}
