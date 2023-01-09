package com.app.onemoretick.service.impl;

import com.app.onemoretick.model.List;
import com.app.onemoretick.repository.ListRepository;
import com.app.onemoretick.service.ListService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public List addList(List list) {
        return listRepository.save(list);
    }

    @Override
    public List getById(Integer id) {
        Optional<List> listOptional = listRepository.findById(id);
        return listOptional.orElse(null);
    }

    @Override
    public List updateList(List list) {
        List listFromDb = getById(list.getId());
        listFromDb.setName(list.getName());
        listFromDb.setItemLists(list.getItemLists());
        listFromDb.setIdUserList(list.getIdUserList());
        return listRepository.save(listFromDb);
    }

    @Override
    public void deleteList(Integer id) {
        List list = getById(id);
        listRepository.delete(list);
    }
}
