package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithToken extends Result {
    @SerializedName("data")
    @Expose
    private SessionToken data;

    public SessionToken getData() {
        return data;
    }

    public ResultWithToken(boolean s, String m, int code, SessionToken d){
        super(s, m, code);

        data = d;
    }
}