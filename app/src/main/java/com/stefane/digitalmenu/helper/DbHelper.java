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
    public static String NAME_DB = "db_menu", NAME_TABLE_ITENS = "itens", NAME_TABLE_ORDERS = "orders", NAME_TABLE_ORDERS_ITENS = "orders_itens";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ITENS + " (id integer PRIMARY KEY AUTOINCREMENT, image integer, name varchar(45) NOT NULL, price float NOT NULL); ";
            db.execSQL(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ORDERS + " (id integer PRIMARY KEY AUTOINCREMENT); ";
            db.execSQL(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_ORDERS_ITENS + " (quantity float, id_order integer, id_item integer, FOREIGN KEY (id_order) REFERENCES " + NAME_TABLE_ORDERS +
                    "(id), FOREIGN KEY (id_item) REFERENCES " + NAME_TABLE_ITENS + "(id)); ";
            db.execSQL(sql);
            Log.i("INFO DB", "Success in creating the tables!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when creating the tables!" + e.getMessage());
        }

        createMenu(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void createMenu(SQLiteDatabase db){
        Item item = new Item();

        item.setImage(R.drawable.pastel_pizza);
        item.setName("Pastel de Pizza");
        item.setPrice((float) 7.50);

        save(db, item);

        item.setImage(R.drawable.pastel_pizza);
        item.setName("Pastel de Carne");
        item.setPrice((float) 6.00);

        save(db, item);

        item.setImage(R.drawable.pastel_pizza);
        item.setName("Pastel de Palmito");
        item.setPrice((float) 8.50);

        save(db, item);

        item.setImage(R.drawable.pastel_pizza);
        item.setName("Pastel de Calabresa");
        item.setPrice((float) 7.50);

        save(db, item);

        item.setImage(R.drawable.pastel_pizza);
        item.setName("Pastel de Frango");
        item.setPrice((float) 6.00);

        save(db, item);

    }

    public void save(SQLiteDatabase db, Item item){
        try{
            ContentValues cv = new ContentValues();
            cv.put("image", item.getImage());
            cv.put("name", item.getName());
            cv.put("price", item.getPrice());
            db.insert(NAME_TABLE_ITENS, null, cv);
            Log.i("INFO DB", "Successful Saving!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when saving!" + e.getMessage());

        }
    }

}
