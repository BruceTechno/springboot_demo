package com.example.OrderSystem.repository;

import com.example.OrderSystem.entity.NewMenu;
import com.example.OrderSystem.entity.NewMenu2;
import com.example.OrderSystem.entity.NewMenu2Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewMenuDao2 extends JpaRepository<NewMenu2, NewMenu2Id> {
}
