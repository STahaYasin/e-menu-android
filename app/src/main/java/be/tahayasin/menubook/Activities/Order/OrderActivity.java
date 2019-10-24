package be.tahayasin.menubook.Activities.Order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.tahayasin.menubook.Activities.Product.ProductsActivity;
import be.tahayasin.menubook.Interfaces.OnOrderClickeListener;
import be.tahayasin.menubook.R;

public class OrderActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

}
