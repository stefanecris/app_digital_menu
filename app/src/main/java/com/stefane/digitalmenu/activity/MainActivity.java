package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.adapter.Adapter;
import com.stefane.digitalmenu.helper.ItemDAO;
import com.stefane.digitalmenu.helper.RecyclerItemClickListener;
import com.stefane.digitalmenu.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMenu;
    private List<Item> itemsMenu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerMenu = findViewById(R.id.recyclerMenu);

        recyclerMenu.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerMenu,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openQuantityItemsActivity(itemsMenu.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                }
        ));

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMenu();
    }

    public void loadMenu(){
        ItemDAO itemDAO = new ItemDAO(getApplicationContext());
        itemsMenu = itemDAO.list();

        Adapter adapter = new Adapter(itemsMenu);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(layoutManager);
        recyclerMenu.setHasFixedSize(true);
        recyclerMenu.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerMenu.setAdapter(adapter);
    }

    public void openQuantityItemsActivity(Item item){
        Intent intent = new Intent(getApplicationContext(), QuantityItemsActivity.class);
        intent.putExtra("Item", item);
        startActivity(intent);
    }

}
