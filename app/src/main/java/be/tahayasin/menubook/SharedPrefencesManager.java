package be.tahayasin.menubook;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefencesManager {

    public static void setUid(Context context, Integer i){
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putInt("uid", i);
        editor.apply();
    }
    public static Integer getUid(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("uid", -1);
    }
    public static void setPin(Context context, String s){
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString("pin", s);
        editor.apply();
    }
    public static String getPin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getString("pin", null);
    }
    public static String getSessionId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getString("SessionId", null);
    }
    public static void setSessionId(Context context, String s){
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString("SessionId", s);
        editor.apply();
    }
    public static String getSessionKey(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getString("SessionKey", null);
    }
    public static void setSessionKey(Context context, String s){
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString("SessionKey", s);
        editor.apply();
    }

    public static void deleteAll(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }


}