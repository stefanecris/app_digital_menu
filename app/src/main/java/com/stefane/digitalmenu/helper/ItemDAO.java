package com.stefane.digitalmenu.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stefane.digitalmenu.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements IItemDAO {

    private SQLiteDatabase write, read;

    public ItemDAO(Context context){
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Item item) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("image", item.getImage());
            cv.put("name", item.getName());
            cv.put("price", item.getPrice());

            write.insert(DbHelper.NAME_TABLE_ITENS, null, cv);
            Log.i("INFO DB", "Successful Saving!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when saving!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Item item) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("image", item.getImage());
            cv.put("name", item.getName());
            cv.put("price", item.getPrice());

            String[] args = {item.getId() + ""};

            write.update(DbHelper.NAME_TABLE_ITENS, cv, "id = ?", args);

            Log.i("INFO DB", "Successful upgrade!");
        }catch(Exception e){
            Log.i("INFO DB", "Error when updating!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Item item) {
        try{

            String[] args = {item.getId() + ""};

            write.delete(DbHelper.NAME_TABLE_ITENS, "id = ?", args);

            Log.i("INFO DB", "Success when deleting!");

        }catch(Exception e){
            Log.i("INFO DB", "Error when deleting!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Item> list() {

        List<Item> itens = new ArrayList<>();

        Cursor cursor = read.rawQuery("SELECT * FROM " + DbHelper.NAME_TABLE_ITENS, null);

        int indexColumnId = cursor.getColumnIndex("id");
        int indexColumnImage = cursor.getColumnIndex("image");
        int indexColumnName = cursor.getColumnIndex("name");
        int indexColumnPrice = cursor.getColumnIndex("price");

        while(cursor.moveToNext()){

            Item item = new Item();

            item.setId(cursor.getInt(indexColumnId));
            item.setImage(cursor.getInt(indexColumnImage));
            item.setName(cursor.getString(indexColumnName));
            item.setPrice(cursor.getFloat(indexColumnPrice));

            itens.add(item);

        }

        return itens;

    }
}
