package com.example.OrderSystem.service.ifs;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.vo.GetMenuResponse;
import com.example.OrderSystem.vo.MenuResponse;

import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Map;

public interface MenuService {
//    public MenuResponse addMenus(List<Menu> menus);
//    public MenuResponse order(Map<String,Integer> orderMap);

    public MenuResponse  addMenus(List<Menu> menuList);
    public MenuResponse order(Map<String,Integer> orderMap);
    public GetMenuResponse getMenuByName (String name ); //name =餐點名稱
    public MenuResponse getAllMenu();
    public MenuResponse updateMenuPrice (List<Menu> menuList);
}
