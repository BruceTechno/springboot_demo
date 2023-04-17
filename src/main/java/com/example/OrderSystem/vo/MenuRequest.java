package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MenuRequest {
    @JsonProperty("menu_list")
    List<Menu> menuList;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }


}
