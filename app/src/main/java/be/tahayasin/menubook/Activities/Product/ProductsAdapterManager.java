package be.tahayasin.menubook.Activities.Product;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Interfaces.OnProductClickListener;

public class ProductsAdapterManager {
    public static void Setup(Context context, OnProductClickListener clickListener, RecyclerView recyclerView, Category category){
        SetupModel1(context, clickListener, recyclerView, category); // TODO add code to change the theme
    }
    private static void SetupModel1(Context context, OnProductClickListener clickListener, RecyclerView recyclerView, Category category){
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ProductAdapter_Model_1(context, clickListener, category));
    }
}
