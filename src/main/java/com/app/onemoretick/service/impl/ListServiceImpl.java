package com.app.onemoretick.service.impl;

import com.app.onemoretick.model.entity.ShoppingList;
import com.app.onemoretick.model.entity.User;
import com.app.onemoretick.repository.ListRepository;
import com.app.onemoretick.service.ListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ShoppingList addList(ShoppingList shoppingList) {
        return listRepository.save(shoppingList);
    }

    @Override
    public ShoppingList getById(Integer id) {
        Optional<ShoppingList> listOptional = listRepository.findById(id);
        return listOptional.orElse(null);
    }

    @Override
    public ShoppingList updateList(ShoppingList shoppingList) {
        ShoppingList shoppingListFromDb = getById(shoppingList.getId());
        shoppingListFromDb.setName(shoppingList.getName());
        shoppingListFromDb.setItemLists(shoppingList.getItemLists());
        shoppingListFromDb.setIdUserList(shoppingList.getIdUserList());
        return listRepository.save(shoppingListFromDb);
    }

    @Override
    public void deleteList(Integer id) {
        ShoppingList shoppingList = getById(id);
        listRepository.delete(shoppingList);
    }

    @Override
    public List<ShoppingList> getAllForUser(User user) {
        return listRepository.getAllByIdUserList(user);
    }
}
