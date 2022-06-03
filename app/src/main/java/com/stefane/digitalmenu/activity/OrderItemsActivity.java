package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.adapter.Adapter;
import com.stefane.digitalmenu.helper.OrderItemDAO;
import com.stefane.digitalmenu.model.Item;
import com.stefane.digitalmenu.model.OrderItem;
import com.stefane.digitalmenu.model.Total;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerOrderItems;
    private TextView textConfirmeMessage;
    private Button buttonYes, buttonNo;
    private List<Item> itemsOrder = new ArrayList<>();
    private int idCurrentOrder;
    private float totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);

        recyclerOrderItems = findViewById(R.id.recyclerOrderItems);
        textConfirmeMessage = findViewById(R.id.textConfirmeMessage);
        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        getIdCurrentOrder();

        getTotal();

        displaysConfirmeMessage();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pedido enviado para a cozinha com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pedido cancelado", Toast.LENGTH_LONG).show();
            }
        });

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

    public void getIdCurrentOrder(){
        idCurrentOrder = (int) getIntent().getSerializableExtra("idCurrentOrder");
    }

    public void getTotal(){
        Total total = new Total(getApplicationContext(), idCurrentOrder);
        totalPrice = total.calcTotal();
    }

    public void displaysConfirmeMessage(){
        textConfirmeMessage.setText("Confirma o pedido no valor de R$ " + String.format("%.2f", totalPrice) + "?");
    }

}
