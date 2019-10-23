package be.tahayasin.menubook;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Locale;

public class CategoriesAdapterManager {
    public static void Setup(Context context, OnCategoryClickListener clickListener, RecyclerView recyclerView,Category[] categories){
        SetupModel1(context, clickListener, recyclerView, categories); // TODO add code to change the theme
    }
    private static void SetupModel1(Context context, OnCategoryClickListener clickListener, RecyclerView recyclerView, Category[] categories){
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CategoriesAdapter_Model_1(context, clickListener, categories));
    }
}