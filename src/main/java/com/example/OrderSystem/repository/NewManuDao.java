package com.example.OrderSystem.repository;

import com.example.OrderSystem.entity.NewMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewManuDao extends JpaRepository<NewMenu,Integer> {
}
