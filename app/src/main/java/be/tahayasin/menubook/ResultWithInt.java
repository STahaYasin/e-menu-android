package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithInt extends Result {
    @SerializedName("data")
    @Expose
    private Integer data;

    public ResultWithInt(boolean s, String m, int code, Integer i) {
        super(s, m, code);

        data = i;
    }

    public Integer getData() {
        return data;
    }
}