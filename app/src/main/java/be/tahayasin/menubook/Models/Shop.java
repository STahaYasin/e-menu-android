package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop {
    @SerializedName("shop_id")
    @Expose
    private int shop_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("array")
    @Expose
    private HoofdModel[] array;

    public String getLogo_source() {
        return logo_source;
    }

    public void setLogo_source(String logo_source) {
        this.logo_source = logo_source;
    }

    @SerializedName("logo_source")
    @Expose
    private String logo_source;

    public int getShop_id() {
        return shop_id;
    }

    public String getName() {
        return name;
    }

    public HoofdModel[] getArray() {
        return array;
    }
}
