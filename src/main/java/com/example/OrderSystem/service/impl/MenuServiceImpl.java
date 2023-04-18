package com.example.OrderSystem.service.impl;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.repository.MenuDao;
import com.example.OrderSystem.service.ifs.MenuService;
import com.example.OrderSystem.vo.GetMenuResponse;
import com.example.OrderSystem.vo.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Map.Entry;


@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;


    @Override
    public MenuResponse addMenus(List<Menu> menuList) {

        if (CollectionUtils.isEmpty(menuList)) {//判斷menuList==null || menuList.isEmpty()
            return new MenuResponse("新增餐點錯誤");
        }

//        if (menuList==null || menuList.isEmpty()){//檢查menulist不能為null 要檢查是否為空 不然會進不去foreach 直接新增成功 一個空的
//            return new MenuResponse("新增餐點錯誤");
//        }
        for (Menu item : menuList) {
            if (!StringUtils.hasText(item.getName())) {//檢查餐點名稱不得為全空 或空字串
                return new MenuResponse("餐點名稱不能為空!!");
            }
            if (item.getPrice() <= 0) {
                return new MenuResponse("餐點價格錯誤");
            }
        }
        List<Menu> response = menuDao.saveAll(menuList);
        return new MenuResponse(response, "新增餐點成功");

    }

    @Override
    //用for迴圈跑exist改寫order的內容 homework0418
    public MenuResponse order(Map<String, Integer> orderMap) {//beef:10 ,AAA:5 ,tea:6
        List<String> itemList = new ArrayList<>();

        for (Entry<String, Integer> item : orderMap.entrySet()) {
            if (item.getValue() < 0) {
                return new MenuResponse("餐點數量錯誤");
            }
            //對 進來的orderMap foreach 防呆 進來的餐點數量
            itemList.add(item.getKey());
            //然後加進list for the next findAllById
        }

        //目前的itemList:["beef", "AAA" , "tea"]: AAA此品項不存在
        List<Menu> result = menuDao.findAllById(itemList); //"AAA" + "beef"  只有一條符合 目前只有  一條資料
        int totalPrice = 0;
        Map<String, Integer> finalOrderMap = new HashMap<>();
        //兩個foreach 互相比對  第一層foreach 是 經過餐點數量防呆處理後並且經過 資料庫findAllById篩選後的list
        //第二個層為 62行帶進來的 map 其存放為 <餐點名稱+餐點數量>

        for (Menu menu : result) {
            String item = menu.getName();//餐點名稱(有兩筆); 第一筆 beef 第二筆 tea
            int price = menu.getPrice();
            for (Entry<String, Integer> map : orderMap.entrySet()) {
                String key = map.getKey(); //orderMap 中的餐點名稱 63行
                int value = map.getValue();// orderMap 中的餐點數量 63行

                //兩個foreach 互相比對 一樣的就是資料正確的
                if (item.equals(key)) {
                    int singleTotalPrice = price * value;// 價格*數量
                    totalPrice += singleTotalPrice;
                    finalOrderMap.put(key, value);
                }
            }
        }
        totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
        return new MenuResponse(finalOrderMap, totalPrice, "點餐成功");
    }

    @Override
    public GetMenuResponse getMenuByName(String name) {
        if (!StringUtils.hasText(name)) {
            return new GetMenuResponse("餐點名稱錯誤");
        }
        Optional<Menu> op = menuDao.findById(name);
        if (!op.isPresent()) {
            return new GetMenuResponse("資料庫內搜尋不到對應的菜單名稱");
        }
        return new GetMenuResponse(op.get(), "successful");
    }

    @Override
    public MenuResponse getAllMenu() {
        List<Menu> op = menuDao.findAll();
        return new MenuResponse(op);
    }

    @Override
    public MenuResponse updateMenuPrice(List<Menu> menuList) {
        //1.只能修改已存在的菜單價格(價格不得為負數)
        //2.不存在的菜單不能新增
        //3.返回修改後的菜單名稱和新價格
        List<String> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuList)) { //去找資料的話 沒有就沒有 如果空的字串 或有問題的字串進去 資料庫就不會找到
            return new MenuResponse("餐點錯誤");   //新增資料的話才需要去判斷  是不是空的 因為新增空的進去會有問題
        }                                         //找資料的話就不需要 但因為要 foreach所以還是要檢查
        for (Menu item : menuList) {
            if (item.getPrice() < 0) {
                return new MenuResponse("價格錯誤");
            }
//            if (!StringUtils.hasText(item.getName())) {
//                return new MenuResponse("餐點名稱空白");
//            }
            //"beef"、"fish"、"AAAA"
            list.add(item.getName()); /// maybe include some name which doesn't in the repository
        }
        List<Menu> result = menuDao.findAllById(list);
        //補防呆 list裡面如空的 的防呆
        if (result.size()==0){
            return new MenuResponse("沒有符合的");
        }
        List<Menu> finalList = new ArrayList<>();

        for (Menu menuResult : result) {
            for (Menu menu : menuList) {
                if (menu.getName().equals(menuResult.getName())) {
//                    menu.setPrice(menuResult.getPrice());//104行帶進來的
// ????              finalPrice = menu.getPrice();
                    finalList.add(menu);//修改好的menu
//        ??            finalList.add(menu.getName());
//        ??            finalList.add(menu.getPrice());
                }
            }
        }
//        List<Menu> response = menuDao.saveAll(menuList);
        return new MenuResponse(menuDao.saveAll(finalList) , "successful");
    }

}