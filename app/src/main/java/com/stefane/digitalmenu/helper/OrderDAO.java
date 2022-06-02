package com.stefane.digitalmenu.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stefane.digitalmenu.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    private SQLiteDatabase write, read;

    public OrderDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Order order) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("id", order.getId());

            write.insert(DbHelper.NAME_TABLE_ORDERS, null, cv);

            Log.i("INFO DB", "Successful Saving!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when saving. " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Order order) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("id", order.getId());

            String[] args = {order.getId() + ""};

            write.update(DbHelper.NAME_TABLE_ORDERS, cv, "id = ?", args);

            Log.i("INFO DB", "Successful upgrade!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when updating. " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        try{

            String[] args = {order.getId() + ""};

            write.delete(DbHelper.NAME_TABLE_ORDERS, "id = ?", args);

            Log.i("INFO DB", "Success when deleting!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when deleting. " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Order> list() {
        List<Order> orderList = new ArrayList<>();

        Cursor cursor = read.rawQuery("SELECT * FROM " + DbHelper.NAME_TABLE_ORDERS, null);

        int indexColumnId = cursor.getColumnIndex("id");

        while(cursor.moveToNext()){

            Order order = new Order();

            order.setId(cursor.getInt(indexColumnId));

            orderList.add(order);

        }

        return orderList;
    }

    public int getIdLastOrder() {

        Cursor cursor = read.rawQuery("SELECT MAX(id) AS last_id FROM " + DbHelper.NAME_TABLE_ORDERS, null);

        int indexColumnId = cursor.getColumnIndex("last_id");

        Order order = new Order();

        if(cursor == null){
            return 0;
        }else{
            while(cursor.moveToNext()){
                order.setId(cursor.getInt(indexColumnId));
            }
        }

        return order.getId();
    }

}
