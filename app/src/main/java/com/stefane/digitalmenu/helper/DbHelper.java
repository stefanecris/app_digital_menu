package com.stefane.digitalmenu.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.model.Item;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NAME_DB = "db_menu", NAME_TABLE_ITEMS = "items", NAME_TABLE_ORDERS = "orders", NAME_TABLE_ORDERS_ITEMS = "orders_items";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ITEMS + " (id integer PRIMARY KEY AUTOINCREMENT, image integer, name varchar(45) NOT NULL, price float NOT NULL);";
            db.execSQL(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ORDERS + " (id integer PRIMARY KEY AUTOINCREMENT);";
            db.execSQL(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ORDERS_ITEMS + " (quantity float, id_order integer, id_item integer, FOREIGN KEY (id_order) REFERENCES " + NAME_TABLE_ORDERS +
                    "(id), FOREIGN KEY (id_item) REFERENCES " + NAME_TABLE_ITEMS + "(id));";
            db.execSQL(sql);
            Log.i("INFO DB", "Success in creating the tables!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when creating the tables. " + e.getMessage());
        }

        createItemsMenu(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void createItemsMenu(SQLiteDatabase db){
        Item item = new Item(R.drawable.pastel_pizza, "Pastel de Pizza", (float) 7.50);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_carne, "Pastel de Carne", (float) 6.00);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_palmito, "Pastel de Palmito", (float) 8.00);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_calabresa, "Pastel de Calabresa", (float) 7.00);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_frango, "Pastel de Frango", (float) 6.00);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_queijo, "Pastel de Queijo", (float) 6.00);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_carne_queijo, "Pastel de Carne com Queijo", (float) 6.50);
        saveItem(db, item);

        item = new Item(R.drawable.pastel_frango_catupiry, "Pastel de Frango com Catupiry", (float) 7.50);
        saveItem(db, item);

    }

    public void saveItem(SQLiteDatabase db, Item item){

        try{
            ContentValues cv = new ContentValues();
            cv.put("image", item.getImage());
            cv.put("name", item.getName());
            cv.put("price", item.getPrice());
            db.insert(NAME_TABLE_ITEMS, null, cv);
            Log.i("INFO DB", "Successful Saving!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when saving. " + e.getMessage());
        }

    }

}