package com.app.onemoretick.repository;

import com.app.onemoretick.models.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Integer> {
}
