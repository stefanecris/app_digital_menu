package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.helper.Order;
import com.stefane.digitalmenu.helper.OrderDAO;
import com.stefane.digitalmenu.helper.OrderItemDAO;
import com.stefane.digitalmenu.model.Item;
import com.stefane.digitalmenu.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class QuantityItemsActivity extends AppCompatActivity {

    private ImageView imageCoverFood;
    private TextView textFoodName, textFoodPrice, textQuantity;
    private Button buttonPlus, buttonLess, buttonOk;
    private Item item;
    private Order order;
    private OrderDAO orderDAO;
    private OrderItem orderItem;
    private int itemQuantity, idOrder;

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
                saveOrderItemOnTheDb();
                openAlertDialog();
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

    public void openAlertDialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Alerta");
        dialog.setMessage("Deseja mais algum outro item?");

        dialog.setCancelable(false);

        dialog.setIcon(android.R.drawable.btn_dialog);

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openMainActivity();
            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveOrderOnTheDb();
                openOrderItemsActivity();
            }
        });

        dialog.create();
        dialog.show();

    }

    public void saveOrderOnTheDb(){
        order = new Order(idOrder + 1);
        orderDAO.save(order);
    }

    public void saveOrderItemOnTheDb(){

        orderDAO = new OrderDAO(getApplicationContext());

        idOrder = orderDAO.getIdLastOrder();

        OrderItemDAO orderItemDAO = new OrderItemDAO(getApplicationContext());
        orderItem = new OrderItem(idOrder + 1, item.getId(), itemQuantity);

        orderItemDAO.save(orderItem);

    }

    public void openMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void openOrderItemsActivity(){
        Intent intent = new Intent(getApplicationContext(), OrderItemsActivity.class);
        intent.putExtra("idCurrentOrder", idOrder + 1);
        startActivity(intent);
    }

}
