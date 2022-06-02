package com.stefane.digitalmenu.helper;

import com.stefane.digitalmenu.model.Item;

import java.util.List;

public interface IItemDAO {

    boolean save(Item item);
    boolean update(Item item);
    boolean delete(Item item);
    List<Item> list();

}
