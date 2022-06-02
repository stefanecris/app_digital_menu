package com.stefane.digitalmenu.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stefane.digitalmenu.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO implements IOrderItemDAO {

    private SQLiteDatabase write, read;

    public OrderItemDAO(Context context){
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(OrderItem orderItem) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("quantity", orderItem.getQuantity());
            cv.put("id_order", orderItem.getId_order());
            cv.put("id_item", orderItem.getId_item());

            write.insert(DbHelper.NAME_TABLE_ORDERS_ITENS, null, cv);
            Log.i("INFO DB", "Successful Saving!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when saving!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(OrderItem orderItem) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("quantity", orderItem.getQuantity());
            cv.put("id_order", orderItem.getId_order());
            cv.put("id_item", orderItem.getId_item());

            String[] args = {orderItem.getId_order() + "", orderItem.getId_item() + ""};

            write.update(DbHelper.NAME_TABLE_ORDERS_ITENS, cv, "id_order = ?, id_item = ?", args);

            Log.i("INFO DB", "Successful upgrade!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when updating!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(OrderItem orderItem) {
        try{

            String[] args = {orderItem.getId_order() + "", orderItem.getId_item() + ""};

            write.delete(DbHelper.NAME_TABLE_ORDERS_ITENS, "id_order = ?, id_item = ?", args);

            Log.i("INFO DB", "Success when deleting!");

        }catch(Exception e){
            Log.i("INFO DB", "Error when deleting!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<OrderItem> list() {
        List<OrderItem> orderItemList = new ArrayList<>();

        Cursor cursor = read.rawQuery("SELECT * FROM " + DbHelper.NAME_TABLE_ORDERS_ITENS, null);

        int indexColumnQuantity = cursor.getColumnIndex("quantity");
        int indexColumnIdOrder = cursor.getColumnIndex("id_order");
        int indexColumnIdItem = cursor.getColumnIndex("id_item");

        while(cursor.moveToNext()){

            OrderItem orderItem = new OrderItem();

            orderItem.setQuantity(cursor.getInt(indexColumnQuantity));
            orderItem.setId_order(cursor.getInt(indexColumnIdOrder));
            orderItem.setId_item(cursor.getInt(indexColumnIdItem));

            orderItemList.add(orderItem);

        }

        return orderItemList;
    }
}
