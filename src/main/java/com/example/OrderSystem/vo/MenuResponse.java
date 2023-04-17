package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.Menu;

import java.util.List;
import java.util.Map;

public class MenuResponse {
    private List<Menu> menuList;
    private Map<String ,Integer> menuMap ;
    private int totalPrice;
    private String message;

    public MenuResponse(Map<String, Integer> menuMap, int totalPrice, String message) {
        this.menuMap = menuMap;
        this.totalPrice = totalPrice;
        this.message = message;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public MenuResponse(List<Menu> menuList, String message) {
        this.menuList = menuList;
        this.message = message;
    }

    public MenuResponse(String message) {
        this.message = message;
    }

    public MenuResponse() {
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Integer> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<String, Integer> menuMap) {
        this.menuMap = menuMap;
    }
}
