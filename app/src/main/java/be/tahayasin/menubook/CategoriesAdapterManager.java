package be.tahayasin.menubook;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CategoriesAdapterManager {
    public static void Setup(Context context, MainActivity mainActivity, RecyclerView recyclerView, Menu menu){
        SetupModel1(context, mainActivity, recyclerView, menu); // TODO add code to change the theme
    }
    private static void SetupModel1(Context context, MainActivity mainActivity, RecyclerView recyclerView, Menu menu){
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CategoriesAdapter_Model_1(context, mainActivity, menu));
    }
}