package com.stefane.digitalmenu.model;

public class OrderItem {
    private int id_order, id_item, quantity;

    public OrderItem() {
    }

    public OrderItem(int id_order, int id_item, int quantity) {
        this.id_order = id_order;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
