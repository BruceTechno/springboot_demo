package com.example.OrderSystem.controller;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.service.ifs.MenuService;
import com.example.OrderSystem.vo.GetMenuResponse;
import com.example.OrderSystem.vo.MenuResponse;
import com.example.OrderSystem.vo.MenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;
    @PostMapping(value = "add_menus")
    public MenuResponse addMenus(@RequestBody MenuRequest request){
        return menuService.addMenus(request.getMenuList());
    }
@PostMapping(value = "get_menu_by_name")
    public GetMenuResponse getMenuByName (@RequestBody MenuRequest request){//
        return menuService.getMenuByName(request.getName());
    }
@GetMapping(value = "get_all_menu")
    public MenuResponse getAllMenu(@RequestBody MenuRequest request){
        return menuService.getAllMenu();
    }
@PostMapping(value = "update_Menu_Price")
    public MenuResponse updateMenuPrice(@RequestBody MenuRequest request ){
        return menuService.updateMenuPrice(request.getMenuList());
    }

}
