package com.example.OrderSystem;

import com.example.OrderSystem.entity.NewMenu;
import com.example.OrderSystem.entity.NewMenu2;
import com.example.OrderSystem.repository.NewManuDao;
import com.example.OrderSystem.repository.NewMenuDao2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderSystemApplication.class)
public class NewMenuTest {
    @Autowired
    private NewManuDao newManuDao;

    @Autowired
    private NewMenuDao2 newManuDao2;
    @Test
    public void addDataTest(){
        NewMenu2 nm1 = new NewMenu2("fish","烤",110);
        NewMenu2 nm2 = new NewMenu2("fish","蒸",150);
       newManuDao2.save(nm1);
        newManuDao2.save(nm2);
    }
}
