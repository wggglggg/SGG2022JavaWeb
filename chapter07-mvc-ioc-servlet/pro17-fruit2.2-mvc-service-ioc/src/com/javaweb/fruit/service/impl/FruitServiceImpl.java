package com.javaweb.fruit.service.impl;

import com.javaweb.fruit.bean.Fruit;
import com.javaweb.fruit.dao.FruitDAO;
import com.javaweb.fruit.service.FruitService;

import java.util.List;

/**
 * ClassName: FruitServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/14 13:19
 * @Version 1.0
 */
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(int pageNo, String keyword) {
        return fruitDAO.getFruitList(pageNo, keyword);
    }

    @Override
    public Fruit getFruitById(int id) {
        return fruitDAO.getFruitById(id);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }

    @Override
    public void deleteFruit(int fid) {
        fruitDAO.deleteFruit(fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Long getFruitCount() {
        return fruitDAO.getFruitCount();
    }
}
