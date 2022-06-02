package com.stefane.digitalmenu.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stefane.digitalmenu.helper.DbHelper;

public class Total {

    private SQLiteDatabase write, read;
    private int idCurrentOrder;

    public Total(Context context, int idCurrentOrder){
        DbHelper db = new DbHelper(context);
        this.idCurrentOrder = idCurrentOrder; 
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public float calcTotal(){

        Cursor cursor = read.rawQuery("SELECT SUM(quantity * price) AS total FROM " + DbHelper.NAME_TABLE_ITENS + " JOIN " + DbHelper.NAME_TABLE_ORDERS_ITENS +
                " ON id = id_item WHERE id_order = " + idCurrentOrder , null);

        int indexColumnTotal = cursor.getColumnIndex("total");

        float total = 0;

        while(cursor.moveToNext()){

            total = cursor.getFloat(indexColumnTotal);

        }

        return total;

    }

}
