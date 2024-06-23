package com.example.foodorderer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderer.Adaptor.CartListAdaptor;
import com.example.foodorderer.Helper.ManagementCart;
import com.example.foodorderer.Interface.ChangeNumberItemsListener;
import com.example.foodorderer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView cartList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, totalDeliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        bottomNavigation();

    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        cartList = findViewById(R.id.cartList);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.totalTaxTxt);
        totalDeliveryTxt = findViewById(R.id.totalDeliveryTxt);
        totalTxt = findViewById(R.id.totalSumTxt);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptyTxt);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managementCart.getListCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        cartList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    private void calculateCart() {
        double precetTax = 0.2, deliveryFee = 10;
        tax = Math.round(managementCart.getTotalFee() * precetTax * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + deliveryFee) * 100) / 100;
        double itemTotal = Math.round((managementCart.getTotalFee()) * 100) / 100;
        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        totalDeliveryTxt.setText("$" + deliveryFee);
        totalTxt.setText("$" + total);
    }
}
