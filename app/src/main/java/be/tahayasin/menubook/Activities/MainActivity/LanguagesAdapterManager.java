package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;

public class LanguagesAdapterManager {

    public static void SetupRv(Context context, OnLanguageSelectListener languageSelectListener, RecyclerView recyclerView, HoofdModel[] hoofdModels){
        SetupRv_1(context, languageSelectListener, recyclerView, hoofdModels); // TODO first check to know how to setup the rv
    }
    private static void SetupRv_1(Context context, OnLanguageSelectListener languageSelectListener, RecyclerView recyclerView, HoofdModel[] hoofdModels){
        if(recyclerView == null) return;

        //recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new LanguagesAdapter_Model_1(context, languageSelectListener, hoofdModels));
    }

    public static Integer getSupportedLangImage(int id){

        return null;
    }
}