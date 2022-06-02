package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.model.Item;
import com.stefane.digitalmenu.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class QuantityItensActivity extends AppCompatActivity {

    private ImageView imageCoverFood;
    private TextView textFoodName, textFoodPrice, textQuantity;
    private Button buttonPlus, buttonLess, buttonOk;
    private Item item;
    private OrderItem orderItem;
    private int itemQuantity, idOrder;
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_itens);

        imageCoverFood = findViewById(R.id.imageCoverFood);
        textFoodName = findViewById(R.id.textFoodName);
        textFoodPrice = findViewById(R.id.textFoodPrice);
        textQuantity = findViewById(R.id.textQuantity);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonLess = findViewById(R.id.buttonLess);
        buttonOk = findViewById(R.id.buttonOk);
        itemQuantity = 0;
        idOrder = 1;

        getItem();

        displaysSelectedItem();

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity++;
                textQuantity.setText(itemQuantity + "");
            }
        });

        buttonLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity--;
                if(itemQuantity < 0){
                    itemQuantity = 0;
                }
                textQuantity.setText(itemQuantity + "");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemOnTheList();

                Toast.makeText(getApplicationContext(), "quantity: " + orderItems.get(0).getQuantity() + " - id_order: " + orderItems.get(0).getId_order() + " - id_item: " + orderItems.get(0).getId_item(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getItem(){
        item = (Item) getIntent().getSerializableExtra("Item");
    }

    public void displaysSelectedItem(){
        imageCoverFood.setImageResource(item.getImage());
        textFoodName.setText(item.getName());
        textFoodPrice.setText(item.getPrice() + "");
        textQuantity.setText(itemQuantity + "");
    }

    public void addItemOnTheList(){
        orderItem = new OrderItem(idOrder, item.getId(), itemQuantity);
        orderItems.add(orderItem);
    }

}
