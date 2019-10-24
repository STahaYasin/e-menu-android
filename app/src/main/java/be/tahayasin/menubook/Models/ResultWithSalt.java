package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWithSalt extends Result {
    @SerializedName("data")
    @Expose
    private SaltAndChallenge data;

    public SaltAndChallenge getData() {
        return data;
    }

    public ResultWithSalt(boolean s, String m, int code, SaltAndChallenge d){
        super(s, m, code);

        data = d;
    }
}