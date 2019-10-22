package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("menu_id")
    @Expose
    private int menu_id;

    public int getMenu_id() {
        return menu_id;
    }

    @SerializedName("shop_id")
    @Expose
    private int shop_id;

    public int getShop_id() {
        return shop_id;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("version")
    @Expose
    private String version;

    public String getVcode() {
        return version;
    }

    @SerializedName("createdon")
    @Expose
    private String createdOn;

    public String getCreatedOn() {
        return createdOn;
    }

    @SerializedName("lastediton")
    @Expose
    private String lastEditOn;

    public String getLastEditOn() {
        return lastEditOn;
    }

    @SerializedName("active")
    @Expose
    private boolean active;

    public boolean isActive() {
        return active;
    }

    @SerializedName("show")
    @Expose
    private boolean show;

    public boolean isShow() {
        return show;
    }

    @SerializedName("show_title")
    @Expose
    private boolean show_title;

    public boolean isShow_title() {
        return show_title;
    }

    @SerializedName("show_image")
    @Expose
    private boolean show_image;

    public boolean isShow_image() {
        return show_image;
    }

    @SerializedName("language")
    @Expose
    private Language language;

    public Language getLanguage() {
        return language;
    }

    @SerializedName("languages")
    @Expose
    private Language[] languages;

    public Language[] getLanguages() {
        return languages;
    }

    @SerializedName("categories")
    @Expose
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }

    @SerializedName("show_categories")
    @Expose
    private boolean show_categories;

    public boolean isShow_categories() {
        return show_categories;
    }

    @SerializedName("show_all_categories_first")
    @Expose
    private boolean show_all_categories_first;

    public boolean isShow_all_categories_first() {
        return show_all_categories_first;
    }
}