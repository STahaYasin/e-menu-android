package be.tahayasin.menubook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaltAndChallenge {
    @SerializedName("session_id")
    @Expose
    private String session_id;

    public String getSession_id() {
        return session_id;
    }

    @SerializedName("salt")
    @Expose
    private String salt;

    public String getSalt() {
        return salt;
    }

    @SerializedName("challenge")
    @Expose
    private String challenge;

    public String getChallenge() {
        return challenge;
    }
}