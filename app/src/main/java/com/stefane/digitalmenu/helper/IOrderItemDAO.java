package com.stefane.digitalmenu.helper;

import com.stefane.digitalmenu.model.OrderItem;

import java.util.List;

public interface IOrderItemDAO {

    public boolean save(OrderItem orderItem);
    public boolean update(OrderItem orderItem);
    public boolean delete(OrderItem orderItem);
    public List<OrderItem> list();

}
