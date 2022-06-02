package com.stefane.digitalmenu.helper;

import java.util.List;

public interface IOrderDAO {

    public boolean save(Order order);
    public boolean update(Order order);
    public boolean delete(Order order);
    public List<Order> list();

}
