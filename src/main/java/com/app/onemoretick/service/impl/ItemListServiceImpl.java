package com.app.onemoretick.service.impl;

import com.app.onemoretick.models.ItemList;
import com.app.onemoretick.repository.ItemListRepository;
import com.app.onemoretick.service.ItemListService;
import org.springframework.stereotype.Service;

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
        itemListFromDb.setIdList(itemList.getIdList());
        return itemListRepository.save(itemListFromDb);
    }
    @Override
    public void deleteItemList(Integer id) {
        ItemList itemList = getById(id);
        itemListRepository.delete(itemList);
    }
}

