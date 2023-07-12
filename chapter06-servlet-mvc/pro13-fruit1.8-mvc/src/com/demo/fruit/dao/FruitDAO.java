package com.demo.fruit.dao;






import com.demo.fruit.bean.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取指定页码上的库存列表信息 , 每页显示5条
    List<Fruit> getFruitList(int pageNo, String keyword);

    //根据fid获取特定的水果库存信息
    Fruit getFruitById(int fid);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定的库存记录
    void deleteFruit(int fid);

    //添加新库存记录
    void addFruit(Fruit fruit);

    //查询库存总记录条数
    Long getFruitCount();



}
