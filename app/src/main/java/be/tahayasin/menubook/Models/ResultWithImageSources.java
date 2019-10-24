package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithImageSources extends Result {

    @SerializedName("data")
    @Expose
    private ImageSrc[] data;

    public ImageSrc[] getData() {
        return data;
    }

    public ResultWithImageSources(boolean s, String m, int c, ImageSrc[] data) {
        super(s, m, c);
    }
}