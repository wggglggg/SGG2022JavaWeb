package com.javaweb.fruit.service;


import com.javaweb.fruit.bean.Fruit;

import java.util.List;


/**
 * ClassName: FruitService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/14 13:11
 * @Version 1.0
 */
public interface FruitService {
    //获取指定页码上的库存列表信息 , 每页显示5条
    List<Fruit> getFruitList(int pageNo, String keyword);

    //根据fid获取特定的水果库存信息
    Fruit getFruitById(int id);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定的库存记录
    void deleteFruit(int fid);

    //添加新库存记录
    void addFruit(Fruit fruit);

    //查询库存总记录条数
    Long getFruitCount();
}
