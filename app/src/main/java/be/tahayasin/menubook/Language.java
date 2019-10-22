package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    @SerializedName("code")
    @Expose
    private String code;

    public String getCode() {
        return code;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("imgsrc")
    @Expose
    private String imgsrc;

    public String getImgsrc() {
        return imgsrc;
    }

    @SerializedName("supported_lang")
    @Expose
    private boolean supported_lang;

    public boolean isSupported_lang() {
        return supported_lang;
    }
}