package be.tahayasin.menubook.Handlers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;
import java.util.logging.SocketHandler;

import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Models.Shop;

public class MenuHandler {

    public static String groupName = "menu";

    public static String menuName = "fullmenu";


    public static void storeMenu(Context context, String menu){
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(menuName, menu);

        editor.apply();
    }
    public static void storeMenu(Context context, Shop shop){
        storeMenu(context, new Gson().toJson(shop));
    }
    public static HoofdModel[] getMenu(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        String a = sharedPreferences.getString(menuName, null);
        Shop shop = new Gson().fromJson(a, Shop.class);
        HoofdModel[] hoofdModel = shop.getArray();
        return hoofdModel;
    }
    public static Shop getShop(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        String a = sharedPreferences.getString(menuName, null);
        Shop shop = new Gson().fromJson(a, Shop.class);
        return shop;
    }


}