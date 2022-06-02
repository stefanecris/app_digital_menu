package com.stefane.digitalmenu.helper;

import com.stefane.digitalmenu.model.Order;

import java.util.List;

public interface IOrderDAO {

    boolean save(Order order);
    boolean update(Order order);
    boolean delete(Order order);
    List<Order> list();

}
