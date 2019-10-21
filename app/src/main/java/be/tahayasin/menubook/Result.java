package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("success")
    @Expose
    protected boolean success;

    public boolean getSuccess(){
        return success;
    }

    @SerializedName("message")
    @Expose
    protected String message;

    public String getMessage() {
        return message;
    }

    public Result(boolean s, String m, int c){
        success = s;
        message = m;
        code = c;
    }

    @SerializedName("code")
    @Expose
    private int code;

    public int getCode() {
        return code;
    }
}
