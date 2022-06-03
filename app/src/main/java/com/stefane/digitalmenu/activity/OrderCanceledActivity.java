package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.stefane.digitalmenu.R;

public class OrderCanceledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_canceled);

        Toast.makeText(getApplicationContext(), "Pedido cancelado", Toast.LENGTH_LONG).show();

    }
}
