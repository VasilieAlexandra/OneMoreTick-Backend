package com.app.onemoretick.controller;

import com.app.onemoretick.model.dto.ItemListDto;
import com.app.onemoretick.model.entity.ItemList;
import com.app.onemoretick.service.ItemListService;
import com.app.onemoretick.service.ListService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ModelMapper modelMapper;

    public ItemListController(ItemListService itemListService, ListService listService) {
        this.itemListService = itemListService;
        this.listService = listService;
    }

    @GetMapping
    public ResponseEntity<List<ItemListDto>> getItemsForList(@PathVariable(name = "list_id") Integer id){
        List<ItemList> items = itemListService.getAllByShoppingList(listService.getById(id));
        if(items != null) {
            List<ItemListDto> itemListDtos = items.stream()
                    .map(itemList -> modelMapper.map(itemList, ItemListDto.class))
                    .toList();
            return new ResponseEntity<>(itemListDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<ItemListDto> addItemList(@PathVariable(name = "list_id") Integer id,@Valid @RequestBody ItemListDto newItem){

        newItem.setIdShoppingList(id);
        ItemList item = itemListService.addItemList(modelMapper.map(newItem, ItemList.class));
        if(item != null)
            return new ResponseEntity<>(modelMapper.map(item, ItemListDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<ItemListDto> updateItemList(@PathVariable(name = "list_id") Integer id, @Valid @RequestBody ItemListDto updateItem){

        updateItem.setIdShoppingList(id);
        ItemList item = itemListService.updateItemList(modelMapper.map(updateItem, ItemList.class));
        if(item != null)
            return new ResponseEntity<>(modelMapper.map(item, ItemListDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemListDto> getItemList(@PathVariable(name = "list_id") Integer id, @PathVariable Integer item_id){
        ItemList item = itemListService.getById(item_id);
        if(item != null && Objects.equals(item.getIdShoppingList().getId(), id))
            return new ResponseEntity<>(modelMapper.map(item, ItemListDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<ItemListDto> deleteItemList(@PathVariable(name = "list_id") Integer id, @PathVariable Integer item_id){
        ItemList item = itemListService.getById(item_id);
        if(item != null && Objects.equals(item.getIdShoppingList().getId(), id)) {
            itemListService.deleteItemList(item_id);
            return new ResponseEntity<>(modelMapper.map(item, ItemListDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
