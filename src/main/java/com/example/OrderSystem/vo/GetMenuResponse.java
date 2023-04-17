package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.Menu;

import java.util.List;

public class GetMenuResponse {
    //attributes
    private Menu menu;
    private String message;
    //constructor
    public GetMenuResponse(Menu menu, String message) {
        this.menu = menu;
        this.message = message;
    }
//Getter Setter
    public GetMenuResponse(String message) {
        this.message = message;
    }

    public GetMenuResponse() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
