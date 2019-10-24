package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageSrc {
    @SerializedName("src")
    @Expose
    private String src;

    public String getSrc() {
        return src;
    }
}
