package com.example.OrderSystem;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.service.ifs.MenuService;
import com.example.OrderSystem.vo.MenuResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = OrderSystemApplication.class)
class OrderSystemApplicationTests {

	@Autowired
	private MenuService menuService;

	@Test
	public void addMenusTest() {
		List<Menu> list = new ArrayList<>(Arrays.asList(new Menu("beef",120),new Menu("fish",100),new Menu("tea",999)));
		MenuResponse response = menuService.addMenus(list);
		List<Menu> responseList =response.getMenuList();

//		List<Menu> list = new ArrayList<>(Arrays.asList(new Menu("beef",)))
	}
	@Test
	public void updateMenuPriceTest(){
		List<Menu> list = new ArrayList<>(Arrays.asList(new Menu("beef",990),new Menu("fish",850)));
		MenuResponse response = menuService.updateMenuPrice(list);

	}

}
