package com.stefane.digitalmenu.helper;

import com.stefane.digitalmenu.model.Item;
import com.stefane.digitalmenu.model.OrderItem;

import java.util.List;

public interface IOrderItemDAO {

    boolean save(OrderItem orderItem);
    boolean update(OrderItem orderItem);
    boolean delete(OrderItem orderItem);
    List<Item> list(int idCurrentOrder);

}
