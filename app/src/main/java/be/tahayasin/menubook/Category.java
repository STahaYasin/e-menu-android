package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    @Expose
    private Integer category_id;

    public Integer getCategory_id() {
        return category_id;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("name_in_main_lang")
    @Expose
    private String name_in_main_lang;

    public String getName_in_main_lang() {
        return name_in_main_lang;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    @SerializedName("description_in_main_lang")
    @Expose
    private String description_in_main_lang;

    public String getDescription_in_main_lang() {
        return description_in_main_lang;
    }

    @SerializedName("imgsrc")
    @Expose
    private String imgsrc;

    public String getImgsrc() {
        return imgsrc;
    }

    @SerializedName("imgsrc_in_main_lang")
    @Expose
    private String imgsrc_in_main_lang;

    public String getImgsrc_in_main_lang() {
        return imgsrc_in_main_lang;
    }
}