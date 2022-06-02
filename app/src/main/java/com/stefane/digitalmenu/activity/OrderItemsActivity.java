package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.adapter.Adapter;
import com.stefane.digitalmenu.helper.OrderItemDAO;
import com.stefane.digitalmenu.model.Item;
import com.stefane.digitalmenu.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerOrderItems;
    private TextView textConfirmeMessage;
    private Button buttonYes, buttonNo;
    private List<OrderItem> order = new ArrayList<>();
    private List<Item> itemsOrder = new ArrayList<>();
    private int idCurrentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);

        recyclerOrderItems = findViewById(R.id.recyclerOrderItems);
        textConfirmeMessage = findViewById(R.id.textConfirmeMessage);
        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        idCurrentOrder = (int) getIntent().getSerializableExtra("idCurrentOrder");

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadOrderItems();
    }

    public void loadOrderItems(){
        OrderItemDAO orderItemDAO = new OrderItemDAO(getApplicationContext());
        itemsOrder = orderItemDAO.list(idCurrentOrder);

        Adapter adapter = new Adapter(itemsOrder);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerOrderItems.setLayoutManager(layoutManager);
        recyclerOrderItems.setHasFixedSize(true);
        recyclerOrderItems.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerOrderItems.setAdapter(adapter);
    }

}
