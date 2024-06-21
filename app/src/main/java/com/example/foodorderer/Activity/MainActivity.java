package com.example.foodorderer.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderer.Adaptor.CategoryAdaptor;
import com.example.foodorderer.Adaptor.PopularAdaptor;
import com.example.foodorderer.Domain.CategoryDomain;
import com.example.foodorderer.Domain.FoodDomain;
import com.example.foodorderer.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCategory,adapterPopular;
    private RecyclerView recyclerViewCategorieyList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewCategoriey();
        recyclerViewPopular();
    }

    private void recyclerViewCategoriey() {
        LinearLayoutManager LinearLaoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategorieyList = findViewById(R.id.recyclerViewCategorieyList);
        recyclerViewCategorieyList.setLayoutManager(LinearLaoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hot dog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapterCategory = new CategoryAdaptor(category);
        recyclerViewCategorieyList.setAdapter(adapterCategory);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager LinearLaoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerViewPopularList);
        recyclerViewPopularList.setLayoutManager(LinearLaoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();

        foodList.add(new FoodDomain("Peperoni Pizza", "pop_1", "slices pepperoni, mozzerella cheese, fresh oregano, ground black  pepper, pizza sauce", 9.76));
        foodList.add(new FoodDomain("Cheese Burger", "pop_2","beef patty, cheese, lettuce, tomato, onion, pickles, ketchup, mustard", 5.99));
        foodList.add(new FoodDomain("Vegetable Pizza", "pop_3", "bell pepper, onion, tomato, mushroom, black olives, mozzarella cheese, pizza sauce", 11.99));

        adapterPopular = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapterPopular);
    }
}