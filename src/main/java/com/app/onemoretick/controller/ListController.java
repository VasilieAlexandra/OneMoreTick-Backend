package com.app.onemoretick.controller;


import com.app.onemoretick.model.dto.ItemListDto;
import com.app.onemoretick.model.dto.ShoppingListDto;
import com.app.onemoretick.model.entity.ShoppingList;
import com.app.onemoretick.service.ListService;
import com.app.onemoretick.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/lists/{user_id}")
public class ListController
{
    private final ListService listService;
    private final UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public ListController(ListService listService, UserService userService) {
        this.listService = listService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListDto>> getAllListsForUser(@PathVariable(name = "user_id") Integer id){
        List<ShoppingList> listsForUser = listService.getAllForUser(userService.getById(id));
        if(listsForUser != null) {
            List<ShoppingListDto> listDtos = listsForUser.stream()
                    .map(list -> modelMapper.map(list, ShoppingListDto.class))
                    .toList();
            return new ResponseEntity<>(listDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<ShoppingList> addListForUser(@PathVariable(name = "user_id") Integer id, @Valid @RequestBody ShoppingListDto newShoppingList){

        newShoppingList.setIdUserList(id);
        ShoppingList shoppingList = listService.addList(modelMapper.map(newShoppingList, ShoppingList.class));
        if(shoppingList != null)
            return new ResponseEntity<>(modelMapper.map(shoppingList, ShoppingList.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<ShoppingList> updateUserList(@PathVariable(name = "user_id") Integer id,@Valid @RequestBody ShoppingListDto updatedShoppingList){

        updatedShoppingList.setIdUserList(id);
        ShoppingList shoppingList = listService.updateList(modelMapper.map(updatedShoppingList, ShoppingList.class));
        if(shoppingList != null)
            return new ResponseEntity<>(modelMapper.map(shoppingList, ShoppingList.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{list_id}")
    public ResponseEntity<ShoppingList> getListForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer list_id){
        ShoppingList listForUser = listService.getById(list_id);
        if(listForUser != null && Objects.equals(listForUser.getIdUserList().getId(), id))
            return new ResponseEntity<>(modelMapper.map(listForUser, ShoppingList.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{list_id}")
    public ResponseEntity<ShoppingList> deleteListForUser(@PathVariable(name = "user_id") Integer id, @PathVariable Integer list_id){
        ShoppingList listForUser = listService.getById(list_id);
        if(listForUser != null && Objects.equals(listForUser.getIdUserList().getId(), id)) {
            listService.deleteList(list_id);
            return new ResponseEntity<>(modelMapper.map(listForUser, ShoppingList.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
