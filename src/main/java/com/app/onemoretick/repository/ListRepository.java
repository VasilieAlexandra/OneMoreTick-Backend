package com.app.onemoretick.repository;

import com.app.onemoretick.model.entity.ShoppingList;
import com.app.onemoretick.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository  extends JpaRepository<ShoppingList, Integer> {
    List<ShoppingList> getAllByIdUserList(User user);
}
