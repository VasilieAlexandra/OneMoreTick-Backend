package com.app.onemoretick.service;

import com.app.onemoretick.models.List;

public interface ListService {
    List addList(List list);
    List getById(Integer id);
    List updateList(List list);
    void deleteList(Integer id);
}
