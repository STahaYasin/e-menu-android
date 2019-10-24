package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;

public class LanguagesAdapterManager {

    public static void SetupRv(Context context, OnLanguageSelectListener languageSelectListener, RecyclerView recyclerView, Menu[] menus){
        SetupRv_1(context, languageSelectListener, recyclerView, menus); // TODO first check to know how to setup the rv
    }
    private static void SetupRv_1(Context context, OnLanguageSelectListener languageSelectListener, RecyclerView recyclerView, Menu[] menus){
        if(recyclerView == null) return;

        //recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new LanguagesAdapter_Model_1(context, languageSelectListener, menus));
    }

    public static Integer getSupportedLangImage(int id){

        return null;
    }
}