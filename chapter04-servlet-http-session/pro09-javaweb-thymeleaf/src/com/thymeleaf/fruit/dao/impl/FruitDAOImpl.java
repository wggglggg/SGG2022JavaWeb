package com.thymeleaf.fruit.dao.impl;


import com.thymeleaf.fruit.bean.Fruit;
import com.thymeleaf.fruit.dao.FruitDAO;
import com.thymeleaf.fruit.dao.base.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return executeQuery("select * from t_fruit");
    }

}