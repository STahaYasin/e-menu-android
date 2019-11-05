package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithMenus extends Result {

    @SerializedName("data")
    @Expose
    private Shop data;

    public Shop getData() {
        return data;
    }

    public ResultWithMenus(boolean s, String m, int c, HoofdModel[] menus) {
        super(s, m, c);
    }
}