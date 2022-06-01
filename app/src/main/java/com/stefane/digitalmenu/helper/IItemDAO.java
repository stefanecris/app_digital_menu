package com.stefane.digitalmenu.helper;

import com.stefane.digitalmenu.model.Item;

import java.util.List;

public interface IItemDAO {

    public boolean save(Item item);
    public boolean update(Item item);
    public boolean delete(Item item);
    public List<Item> list();

}
