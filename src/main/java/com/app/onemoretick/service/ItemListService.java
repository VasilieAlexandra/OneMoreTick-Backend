package com.app.onemoretick.service;

import com.app.onemoretick.models.ItemList;

public interface ItemListService {
    ItemList addItemList(ItemList itemList);
    ItemList getById(Integer id);
    ItemList updateItemList(ItemList itemList);
    void deleteItemList(Integer id);
}
