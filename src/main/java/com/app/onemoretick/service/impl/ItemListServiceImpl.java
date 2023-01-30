package com.app.onemoretick.service.impl;

import com.app.onemoretick.model.entity.ItemList;
import com.app.onemoretick.model.entity.ShoppingList;
import com.app.onemoretick.repository.ItemListRepository;
import com.app.onemoretick.service.ItemListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemListServiceImpl implements ItemListService{

    private final ItemListRepository itemListRepository;

    public ItemListServiceImpl(ItemListRepository itemListRepository) {
        this.itemListRepository = itemListRepository;
    }

    @Override
    public ItemList addItemList(ItemList itemList) {
        return itemListRepository.save(itemList);
    }

    @Override
    public ItemList getById(Integer id) {
        Optional<ItemList> itemListOptional = itemListRepository.findById(id);
        return itemListOptional.orElse(null);
    }

    @Override
    public ItemList updateItemList(ItemList itemList) {
        ItemList itemListFromDb = getById(itemList.getId());
        itemListFromDb.setName(itemList.getName());
        itemListFromDb.setIdShoppingList(itemList.getIdShoppingList());
        return itemListRepository.save(itemListFromDb);
    }
    @Override
    public void deleteItemList(Integer id) {
        ItemList itemList = getById(id);
        itemListRepository.delete(itemList);
    }

    @Override
    public List<ItemList> getAllByShoppingList(ShoppingList shoppingList) {
        return itemListRepository.getAllByIdShoppingList(shoppingList);
    }
}

