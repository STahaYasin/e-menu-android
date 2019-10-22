package be.tahayasin.menubook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LanguagesAdapterManager {

    public static void SetupRv(Context context, MainActivity mainActivity, RecyclerView recyclerView, Menu[] menus){
        SetupRv_1(context, mainActivity, recyclerView, menus); // TODO first check to know how to setup the rv
    }
    private static void SetupRv_1(Context context, MainActivity mainActivity, RecyclerView recyclerView, Menu[] menus){
        if(recyclerView == null) return;

        //recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new LanguagesAdapter_Model_1(context, mainActivity, menus));
    }

    public static Integer getSupportedLangImage(int id){

        return null;
    }
}