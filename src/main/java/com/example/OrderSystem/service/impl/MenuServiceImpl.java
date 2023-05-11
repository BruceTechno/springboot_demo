package com.example.OrderSystem.service.impl;

import com.example.OrderSystem.entity.Menu;
import com.example.OrderSystem.repository.MenuDao;
import com.example.OrderSystem.service.ifs.MenuService;
import com.example.OrderSystem.vo.GetMenuResponse;
import com.example.OrderSystem.vo.MenuResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.Map.Entry;


@Service
public class MenuServiceImpl implements MenuService {
    private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j
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
        logger.info("search by ....~@#%");
        List<Menu> response = menuDao.saveAll(menuList);
        return new MenuResponse(response, "新增餐點成功");

    }

    @Override
    //用for迴圈跑exist改寫order的內容 homework0418
    public MenuResponse order(Map<String, Integer> orderMap) {//beef:10 ,AAA:5 ,tea:6
        //直接開一個新的map進去52foreach裡面接 key value
//List<Menu> menuList = new ArrayList<>();
List<String> stringList = new ArrayList<>();
//ordermap 餐點名稱存成陣列 丟進去findallbyId
        Map<String,Integer> finalMap = new HashMap<>();
        int singleTotalPrice = 0;
        int totalPrice = 0 ;

        for (Entry<String ,Integer> map : orderMap.entrySet()) {
            if (menuDao.existsById(map.getKey()) ) {// orderMap帶進來的String 確認與資料庫比對正確
                finalMap.put(map.getKey(), map.getValue());
                stringList.add(map.getKey());
              Optional<Menu> op  = menuDao.findById(map.getKey());
              if(!op.isPresent()){
                  continue;
              }
                finalMap.put(map.getKey(),map.getValue());
              totalPrice += op.get().getPrice() * map.getValue();
            }

        }
        if (finalMap.size() == 0) {
            return new MenuResponse("查無此菜單");
        }
        List<Menu> result = menuDao.findAllById(stringList);

//        for (Menu menu :result){//price
//               for (Entry<String ,Integer> ordermap : orderMap.entrySet()) {//orderMap.getValue 是分數 缺一個價格
//                    singleTotalPrice = ordermap.getValue() * menu.getPrice();
//                   totalPrice += singleTotalPrice;
//               }
//        }
        return new MenuResponse(finalMap,totalPrice,"successful");
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
        return new MenuResponse(menuDao.saveAll(finalList),"successful");
    }

}