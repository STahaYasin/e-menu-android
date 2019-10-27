package be.tahayasin.menubook.Handlers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;

public class MenuHandler {

    public static String groupName = "menu";
    public static String menuName = "fullmenu";


    public static void storeMenu(Context context, String menu){
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(menuName, menu);

        editor.apply();
    }
    public static void storeMenu(Context context, HoofdModel[] menus){
        storeMenu(context, new Gson().toJson(menus));
    }
    public static Menu[] getMenu(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        String a = sharedPreferences.getString(menuName, null);
        HoofdModel[] hoofdModel = new Gson().fromJson(a, HoofdModel[].class);
        List<Menu> itemslist =  hoofdModel[0].getMenus();

        Menu[] menus = new Menu[itemslist.size()];
        menus = itemslist.toArray(menus);
        return menus;

//        if(a == null) return new Menu[0];
//        else{
//            try{
//                return new Gson().fromJson(a, Menu[].class);
//            }
//            catch (Exception e){
//                return new Menu[0];
//            }
//        }
    }
}