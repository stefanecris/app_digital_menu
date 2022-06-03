package com.stefane.digitalmenu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.stefane.digitalmenu.R;

public class OrderSentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sent);

        Toast.makeText(getApplicationContext(), "Pedido enviado para a cozinha com sucesso!", Toast.LENGTH_LONG).show();

    }
}
