package com.javaweb.fruit.service.impl;

import com.javaweb.fruit.bean.Fruit;
import com.javaweb.fruit.dao.FruitDAO;
import com.javaweb.fruit.service.FruitService;
import com.javaweb.util.ConnUtil;

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
        System.out.println("getFruitList -> " + ConnUtil.getConn());
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
        Fruit fruit2 = fruitDAO.getFruitById(10);
        fruit2.setFcount(99);
        fruitDAO.updateFruit(fruit2);
    }

    @Override
    public Long getFruitCount() {
        System.out.println("getFruitCount -> " + ConnUtil.getConn());
        return fruitDAO.getFruitCount();
    }
}
