package be.tahayasin.menubook;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class HttpPostHandler {

    private Context context;
    private String url;
    private ArrayList<NameValuePair> namePostValuePairs;
    private ArrayList<NameValuePair> nameGetValuePairs;

    private String result = "";

    private boolean excecuted;

    public HttpPostHandler(Context c, String fullUrl){
        this(c, fullUrl, null);
    }
    public HttpPostHandler(Context c, String fullUrl, ArrayList<NameValuePair> n){
        context = c;
        url = fullUrl;
        namePostValuePairs = n;

        excecuted = false;
    }
    public void setPostNameValuePairs(ArrayList<NameValuePair> n){
        namePostValuePairs = n;
    }
    public void AddPostNameValuePair(NameValuePair nameValuePair){
        if(namePostValuePairs == null) namePostValuePairs = new ArrayList<>();

        namePostValuePairs.add(nameValuePair);
    }
    public String getPostNameValueString(){
        if(namePostValuePairs == null) return "";

        StringBuilder sb = new StringBuilder();

        for (NameValuePair nameValuePair: namePostValuePairs) {
            sb.append(nameValuePair.toString());
        }

        return sb.toString();
    }
    public void setGetNameValuePairs(ArrayList<NameValuePair> n){
        nameGetValuePairs = n;
    }
    public void AddGetNameValuePair(NameValuePair nameValuePair){
        if(nameGetValuePairs == null) nameGetValuePairs = new ArrayList<>();

        nameGetValuePairs.add(nameValuePair);
    }
    private String getGetNameValueString(){
        if(nameGetValuePairs == null) return "";

        StringBuilder sb = new StringBuilder();

        sb.append("?");

        for (NameValuePair nameValuePair: nameGetValuePairs) {
            sb.append(nameValuePair.toString());
        }

        return sb.toString();
    }
    public void Excecute(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, getPostNameValueString());
        Request request = new Request.Builder()
                .url(url + getGetNameValueString())
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "20c81bb2-98c5-4cf3-b3ef-9dc741b33219")
                .build();

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        }
        catch (IOException i){
            result = "";
        }

        excecuted = true; // Hold this on the end of the method
    }

    public String getResult() throws NotExcecutedJetException {
        if(!excecuted) throw new NotExcecutedJetException();

        return result;
    }
    public class NotExcecutedJetException extends Exception{

    }
}