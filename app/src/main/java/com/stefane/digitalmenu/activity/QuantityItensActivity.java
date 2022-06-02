package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.model.Item;

public class QuantityItensActivity extends AppCompatActivity {

    private ImageView imageCoverFood;
    private TextView textFoodName, textFoodPrice;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_itens);

        imageCoverFood = findViewById(R.id.imageCoverFood);
        textFoodName = findViewById(R.id.textFoodName);
        textFoodPrice = findViewById(R.id.textFoodPrice);

        getItem();

        displaysSelectedItem();

    }

    public void getItem(){
        item = (Item) getIntent().getSerializableExtra("Item");
    }

    public void displaysSelectedItem(){
        imageCoverFood.setImageResource(item.getImage());
        textFoodName.setText(item.getName());
        textFoodPrice.setText(item.getPrice() + "");
    }

}
