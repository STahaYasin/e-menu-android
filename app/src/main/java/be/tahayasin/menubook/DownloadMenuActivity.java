package be.tahayasin.menubook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class DownloadMenuActivity extends AppCompatActivity {

    private SessionToken sessionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_menu);

        sessionToken = new SessionToken(getIntent().getStringExtra("session_id"), getIntent().getStringExtra("session_token"));

        checkSession();
    }
    private void checkSession(){
        final Context context = this;
        final SessionToken fSessionToken = sessionToken;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPostHandler http = new HttpPostHandler(context, AppStrings.hostWithSlash + "api/login/checksession.php");
                http.AddPostNameValuePair(new NameValuePair("session_id", fSessionToken.getSession_id()));
                http.AddPostNameValuePair(new NameValuePair("session_token", fSessionToken.getSession_token()));

                http.Excecute();

                Result res;

                try {
                    res = new Gson().fromJson(http.getResult(), Result.class);
                }
                catch (Exception e){
                    res = new Result(false, "Json error", 3);
                }

                final Result result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getSuccess()){
                            getDownloadsJson();
                        }
                        else{
                            Toast.makeText(context, "There was some error while identifying the tablet.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
    private void getDownloadsJson(){
        final Context context = this;
        final SessionToken fSessionToken = sessionToken;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPostHandler http = new HttpPostHandler(context, AppStrings.hostWithSlash + "api/android/downloads.php");
                http.AddPostNameValuePair(new NameValuePair("session_id", fSessionToken.getSession_id()));
                http.AddPostNameValuePair(new NameValuePair("session_token", fSessionToken.getSession_token()));

                http.Excecute();

                ResultWithImageSources res;

                try {
                    res = new Gson().fromJson(http.getResult(), ResultWithImageSources.class);
                }
                catch (Exception e){
                    res = new ResultWithImageSources(false, "Json error", 3, null);
                }

                final ResultWithImageSources result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getSuccess()){
                            downLoadImages(result.getData());
                        }
                        else{
                            Toast.makeText(context, result.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
    private void downLoadImages(ImageSrc[] imageSrcs){
        ((TextView) findViewById(R.id.activity_download_menu_explanation_title)).setText(String.valueOf(imageSrcs.length) + " images");
    }
}