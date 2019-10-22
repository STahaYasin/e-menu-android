package be.tahayasin.menubook;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStrings {

    public static String host = "http://e-menu.be";
    public static String hostWithSlash = host + "/";
    public static String imagehost = "https://e-menu.be/files/archief/images/content/";
    public static String imageHostWithSlash = imagehost + "/";

    public static void setPathForImg(Context context, String name, String path){
        SharedPreferences.Editor editor = context.getSharedPreferences("path", Context.MODE_PRIVATE).edit();
        editor.putString(name, path);
        editor.apply();
    }

    public static String getPathForImg(Context context, String name){
        return context.getSharedPreferences("path", Context.MODE_PRIVATE).getString(name, "");
    }
}
