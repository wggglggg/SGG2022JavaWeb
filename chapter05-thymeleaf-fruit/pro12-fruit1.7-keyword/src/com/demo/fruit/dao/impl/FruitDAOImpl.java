package com.demo.fruit.dao.impl;




import com.demo.fruit.bean.Fruit;
import com.demo.fruit.dao.FruitDAO;
import com.demo.fruit.dao.base.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList(int pageNo, String keyword) {
        return executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? , 5", "%"+keyword+"%", "%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public Fruit getFruitById(int fid) {
        return load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql ="update t_fruit set fname=?,price=?,fcount=?,remark=? where fid = ?";
        executeUpdate(sql,fruit.getFname(), fruit.getPrice(), fruit.getFcount(),
        fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void deleteFruit(int fid) {
        String sql = "delete from t_fruit where fid = ?";
        executeUpdate(sql, fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit(fname, price, fcount, remark) values(?,?,?,?)";
        executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(),
                fruit.getRemark());
    }

    @Override
    public Long getFruitCount() {
        String sql = "select count(*) from t_fruit";
        Object[] countObject = executeComplexQuery(sql);
        Long count = (Long) countObject[0];


        return count;
    }


}