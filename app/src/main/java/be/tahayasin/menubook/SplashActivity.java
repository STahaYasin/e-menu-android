package be.tahayasin.menubook;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startLockTask();

        setText();

        waitAndCheckConnection();
    }
    private void setText(){
        //((TextView) findViewById(R.id.test)).setText("UID: " + String.valueOf(SharedPrefencesManager.getUid(this)));
    }
    private void waitAndCheckConnection(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(750);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        checkConnection();
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
    private void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean a = cm.getActiveNetworkInfo() != null;

        if(cm.getActiveNetworkInfo() == null){
            Snackbar.make(findViewById(R.id.activity_uid_coo), "Please check your internet connection", Snackbar.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "check stored id", Toast.LENGTH_LONG).show();
            checkStoredUid();
        }
    }
    private void checkStoredUid(){
        Integer uid = SharedPrefencesManager.getUid(this);
        String pin = SharedPrefencesManager.getPin(this);

        if(uid == -1 || pin == null || pin.equals("")){
            Toast.makeText(this, "Please close the app and open the settings app", Toast.LENGTH_LONG);
        }
        else{

            getSalt();
        }
    }
    private void getSalt(){
        final Context context = this;
        final Integer uid = SharedPrefencesManager.getUid(context);

        Thread getSaltAndChallengeThread = new Thread(new Runnable() {
            @Override
            public void run() {

                final HttpPostHandler http = new HttpPostHandler(context, AppStrings.hostWithSlash + "api/login/getsaltandsgallengefortablet.php");
                http.AddGetNameValuePair(new NameValuePair("tablet_id", String.valueOf(SharedPrefencesManager.getUid(context))));

                http.Excecute();

                ResultWithSalt res;

                try {
                    res = new Gson().fromJson(http.getResult(), ResultWithSalt.class);
                }
                catch (Exception e){
                    res = new ResultWithSalt(false, e.getMessage(), 2, null);
                }

                final ResultWithSalt result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getSuccess()){
                            checkHash(result);
                        }
                        else{
                            ResCodeHandler.DeleteUidAndPinIfNeeded(context, result.getCode());
                            checkConnection();
                        }
                    }
                });
            }
        });
        getSaltAndChallengeThread.setPriority(Thread.MAX_PRIORITY);
        getSaltAndChallengeThread.start();
    }
    private void checkHash(final ResultWithSalt data){
        final Context context = this;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String pin = SharedPrefencesManager.getPin(context);
                String id = String.valueOf(SharedPrefencesManager.getUid(context));
                String hash1;
                try {
                    hash1 = AeSimpleSHA1.SHA512(pin + data.getData().getSalt());
                } catch (Exception e) {
                    hash1 = "";
                }
                String hash;
                try {
                    hash = AeSimpleSHA1.SHA512(hash1 + data.getData().getChallenge());
                } catch (Exception e) {
                    hash = hash1;
                }

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "id=" + id + "&hash=" + hash + "&session_id=" + data.getData().getSession_id());
                Request request = new Request.Builder()
                        .url(AppStrings.hostWithSlash + "api/login/loginfortablet.php")
                        .post(body)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Postman-Token", "20c81bb2-98c5-4cf3-b3ef-9dc741b33219")
                        .build();

                ResultWithToken res;

                try {
                    Response response = client.newCall(request).execute();
                    res = new Gson().fromJson(response.body().string(), ResultWithToken.class);
                }
                catch (IOException i){
                    res = new ResultWithToken(false, context.getResources().getString(R.string.error_network), 2, null);
                }
                catch (Exception e){
                    res = new ResultWithToken(false, e.getMessage(), 0, null);
                }

                final ResultWithToken result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getSuccess()){
                            openMenuDownloadActivity(result.getData());
                        }
                        else{
                            ResCodeHandler.DeleteUidAndPinIfNeeded(context, result.getCode());
                            checkConnection();
                        }
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
    private void openMenuDownloadActivity(SessionToken sessionToken){
        Intent i = new Intent(this, DownloadMenuActivity.class);

        i.putExtra("session_id", sessionToken.getSession_id());
        i.putExtra("session_token", sessionToken.getSession_token());

        startActivity(i);
        finish();
    }
}