package com.example.OrderSystem.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable {
    private String category;
    private String item;

    public NewMenu2Id() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
