package com.app.onemoretick.controller;


import com.app.onemoretick.model.ShoppingList;
import com.app.onemoretick.service.ListService;
import com.app.onemoretick.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/lists/{user_id}/")
public class ListController
{
    private final ListService listService;
    private final UserService userService;

    public ListController(ListService listService, UserService userService) {
        this.listService = listService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingList>> getAllListsForUser(@PathVariable(name = "user_id") Integer id){
        List<ShoppingList> listsForUser = listService.getAllForUser(userService.getById(id));
        if(listsForUser != null)
            return new ResponseEntity<>(listsForUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<ShoppingList> addListForUser(@PathVariable(name = "user_id") Integer id, @RequestBody ShoppingList newShoppingList){

        newShoppingList.setIdUserList(userService.getById(id));
        ShoppingList shoppingList = listService.addList(newShoppingList);
        if(shoppingList != null)
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<ShoppingList> updateUserList(@PathVariable(name = "user_id") Integer id,@RequestBody ShoppingList updatedShoppingList){

        updatedShoppingList.setIdUserList(userService.getById(id));
        ShoppingList shoppingList = listService.updateList(updatedShoppingList);
        if(shoppingList != null)
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{list_id}")
    public ResponseEntity<ShoppingList> getListForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer list_id){
        ShoppingList listForUser = listService.getById(list_id);
        if(listForUser != null && Objects.equals(listForUser.getIdUserList().getId(), id))
            return new ResponseEntity<>(listForUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{list_id}")
    public ResponseEntity<ShoppingList> deleteListForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer list_id){
        ShoppingList listForUser = listService.getById(list_id);
        if(listForUser != null && Objects.equals(listForUser.getIdUserList().getId(), id)) {
            listService.deleteList(list_id);
            return new ResponseEntity<>(listForUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
