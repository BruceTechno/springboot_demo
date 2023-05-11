package com.example.OrderSystem;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.entity.PersonInfo;
import com.example.OrderSystem.repository.PersonInfoDao;
import com.example.OrderSystem.service.ifs.MenuService;
import com.example.OrderSystem.service.ifs.PersonInfoService;
import com.example.OrderSystem.vo.GetPersonInfoByNameOrCityRequest;
import com.example.OrderSystem.vo.MenuResponse;
import com.example.OrderSystem.vo.PersonInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

@SpringBootTest(classes = OrderSystemApplication.class)
class OrderSystemApplicationTests {
	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private MenuService menuService;
	@Autowired
	private PersonInfoService personInfoService;
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
//	@Test
//	public void  order(){
//		Map<String,Integer> testMap = new HashMap<>();
//		testMap.put("beef",5);
//		menuService.order(testMap);
//		Assert
//	}
//======================================================================================

@Test //id name age city
	public void addPersonInfoTest(){
		List<PersonInfo> list = new ArrayList<>(Arrays.asList(new PersonInfo("009","Tom",25,"Taipei"),
				                                              new PersonInfo("010","John",26,"Taoyuan"),
				                                              new PersonInfo("011","Josh",23,"Taichung")));
	PersonInfoResponse response = personInfoService.addPersonInfo(list);
}
//@Test
//	public void getPersonInfoByIdTest(String Id){
//
//}
	@Test
	public void updateNameById(){
		int result = personInfoDao.updateNameById("007","Mary2",38,"Yilan");
	}
	@Test
	public void doUpDateByNameTest(){

	}
	@Test
	public void searchByNameOrCityAll(){
//		List<PersonInfo> list = new ArrayList<>(Arrays.asList(new PersonInfo("009","Tom",25,"Taipei"),
//															  new PersonInfo("010","John",26,"Taoyuan"),
//															  new PersonInfo("011","Josh",23,"Taichung")));
		String inputName = "Nancy";
		String inputCity = "";
		String name = StringUtils.hasText(inputName) ? inputName : null;
		String city = StringUtils.hasText(inputCity) ? inputCity : null;
		List<PersonInfo> result = personInfoDao.searchByNameOrCityAll(name, city);
		System.out.println(result.size());
	}
	@Test
	public void searchByNameOrCityNone(){
		String inputName = "Nancy";
		String inputCity = "";
		String name = StringUtils.hasText(inputName) ? inputName : null;
		String city = StringUtils.hasText(inputCity) ? inputCity : null;
		List<PersonInfo> result = personInfoDao.searchByNameOrCityNone(name, city);
		System.out.println(result.size());
	}
	@Test
	public void containingTest(){

	}

}
