package com.thymeleaf.fruit.dao;




import com.thymeleaf.fruit.bean.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

}
