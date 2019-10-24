package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithMenus extends Result {

    @SerializedName("data")
    @Expose
    private Menu[] data;

    public Menu[] getData() {
        return data;
    }

    public ResultWithMenus(boolean s, String m, int c, Menu[] menus) {
        super(s, m, c);
    }
}