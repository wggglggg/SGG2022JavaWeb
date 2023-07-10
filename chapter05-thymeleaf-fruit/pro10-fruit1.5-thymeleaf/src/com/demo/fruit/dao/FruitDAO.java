package com.demo.fruit.dao;






import com.demo.fruit.bean.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    Fruit getFruitById(int fid);

    void updateFruit(Fruit fruit);

    void deleteFruit(int fid);

    void addFruit(Fruit fruit);

}
