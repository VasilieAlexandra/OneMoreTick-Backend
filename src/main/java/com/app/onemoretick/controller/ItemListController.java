package com.app.onemoretick.controller;

import com.app.onemoretick.model.entity.ItemList;
import com.app.onemoretick.service.ItemListService;
import com.app.onemoretick.service.ListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/lists/{list_id}/items")
public class ItemListController {

    private final ItemListService itemListService;
    private final ListService listService;

    public ItemListController(ItemListService itemListService, ListService listService) {
        this.itemListService = itemListService;
        this.listService = listService;
    }

    @GetMapping
    public ResponseEntity<List<ItemList>> getItemsForList(@PathVariable(name = "list_id") Integer id){
        List<ItemList> items = itemListService.getAllByShoppingList(listService.getById(id));
        if(items != null)
            return new ResponseEntity<>(items, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<ItemList> addListForUser(@PathVariable(name = "list_id") Integer id, @RequestBody ItemList newItem){

        newItem.setIdShoppingList(listService.getById(id));
        ItemList item = itemListService.addItemList(newItem);
        if(item != null)
            return new ResponseEntity<>(item, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<ItemList> updateUserList(@PathVariable(name = "list_id") Integer id,@RequestBody ItemList updateItem){

        updateItem.setIdShoppingList(listService.getById(id));
        ItemList item = itemListService.updateItemList(updateItem);
        if(item != null)
            return new ResponseEntity<>(item, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemList> getListForUser(@PathVariable(name = "list_id") Integer id, @PathVariable Integer item_id){
        ItemList item = itemListService.getById(item_id);
        if(item != null && Objects.equals(item.getIdShoppingList().getId(), id))
            return new ResponseEntity<>(item, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<ItemList> deleteListForUser(@PathVariable(name = "list_id") Integer id, @PathVariable Integer item_id){
        ItemList item = itemListService.getById(item_id);
        if(item != null && Objects.equals(item.getIdShoppingList().getId(), id)) {
            itemListService.deleteItemList(item_id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
