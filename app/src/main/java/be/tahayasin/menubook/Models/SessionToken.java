package be.tahayasin.menubook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionToken{
    @SerializedName("session_id")
    @Expose
    private String session_id;

    public String getSession_id() {
        return session_id;
    }

    @SerializedName("session_token")
    @Expose
    private String session_token;

    public String getSession_token() {
        return session_token;
    }

    public SessionToken(){

    }
    public SessionToken(String session_id, String session_token){
        this.session_id = session_id;
        this.session_token = session_token;
    }
}