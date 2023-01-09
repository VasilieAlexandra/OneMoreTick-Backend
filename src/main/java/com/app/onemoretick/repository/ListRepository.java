package com.app.onemoretick.repository;

import com.app.onemoretick.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository  extends JpaRepository<List, Integer> {
}
