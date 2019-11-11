package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import be.tahayasin.menubook.Handlers.IHaveImageSource;

public class ImageSrc implements IHaveImageSource {
    @SerializedName("src")
    @Expose
    private String src;

    public String getSrc() {
        return src;
    }

    @Override
    public String getSourcePatch() {
        return src;
    }
}
