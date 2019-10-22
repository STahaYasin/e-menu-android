package be.tahayasin.menubook;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ProductsAdapterManager {
    public static void Setup(Context context, MainActivity mainActivity, RecyclerView recyclerView, Category category){
        SetupModel1(context, mainActivity, recyclerView, category); // TODO add code to change the theme
    }
    private static void SetupModel1(Context context, MainActivity mainActivity, RecyclerView recyclerView, Category category){
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ProductAdapter_Model_1(context, mainActivity, category));
    }
}
