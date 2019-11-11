package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;
import be.tahayasin.menubook.Interfaces.OnMenuClickListener;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;

public class MenuAdapterManager {

    public static void SetupRv(Context context, OnMenuClickListener menuClickListener, RecyclerView recyclerView, Menu[] menus){
        SetupRv_1(context, menuClickListener, recyclerView, menus); // TODO first check to know how to setup the rv
    }
    private static void SetupRv_1(Context context, OnMenuClickListener menuClickListener, RecyclerView recyclerView, Menu[] menus){
        if(recyclerView == null) return;

        //recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MenuAdapter_Model_1(context, menuClickListener, menus));
    }

    public static Integer getSupportedLangImage(int id){

        return null;
    }
}