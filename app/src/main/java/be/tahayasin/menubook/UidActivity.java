package be.tahayasin.menubook;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uid);

        checkConnection();
    }
    private void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean a = cm.getActiveNetworkInfo() != null;

        if(cm.getActiveNetworkInfo() == null){
            Snackbar.make(findViewById(R.id.activity_uid_coo), "Please check your internet connection", Snackbar.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Checking id", Toast.LENGTH_LONG).show();
            checkStoredUid();
        }
    }
    private void checkStoredUid(){
        Integer uid = SharedPrefencesManager.getUid(this);
        String pin = SharedPrefencesManager.getPin(this);

        if(uid == -1 || pin == null || pin.equals("")){
            Toast.makeText(this, "Request id", Toast.LENGTH_LONG).show();
            requestNewId();
        }
        else{
            Toast.makeText(this, "Get salt", Toast.LENGTH_LONG).show();
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

                /*OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(AppStrings.hostWithSlash + "api/getsaltandsgallengefortablet.php?tablet_id=" + uid)
                        .get()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Postman-Token", "20c81bb2-98c5-4cf3-b3ef-9dc741b33219")
                        .build();

                ResultWithSalt res;
                try {
                    Response response = client.newCall(request).execute();
                    res = new Gson().fromJson(response.body().string(), ResultWithSalt.class);
                }
                catch (IOException i){
                    res = new ResultWithSalt(false, context.getResources().getString(R.string.error_network), 2, null);
                }
                catch (Exception e){
                    res = new ResultWithSalt(false, e.getMessage(), 0, null);
                }*/

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
                            openSettings(result);
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
    private void openSettings(ResultWithToken result){
        Intent i = new Intent(this, SettingsActivity.class);

        i.putExtra("session_id", result.getData().getSession_id());
        i.putExtra("session_token", result.getData().getSession_token());

        startActivity(i);
        finish();
    }
    private void requestNewId(){
        final Context context = this;

        Random random = new Random();
        int q = random.nextInt(868745) + 128657;

        final String pin = String.valueOf(q);

        final Date date = Calendar.getInstance().getTime();
        String salt = "";
        try {
            salt = AeSimpleSHA1.SHA256(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String hash = "";

        try {
            hash = AeSimpleSHA1.SHA512(pin + salt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String f_salt = salt;
        final String f_hash = hash;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "hash=" + f_hash + "&salt=" + f_salt);
                Request request = new Request.Builder()
                        .url(AppStrings.hostWithSlash + "api/login/requestnewid.php")
                        .post(body)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Postman-Token", "20c81bb2-98c5-4cf3-b3ef-9dc741b33219")
                        .build();

                ResultWithInt res;

                try {
                    final Response response = client.newCall(request).execute();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Toast.makeText(context, response.body().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    res = new Gson().fromJson(response.body().string(), ResultWithInt.class);
                }
                catch (final IOException i){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, i.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    res = new ResultWithInt(false, context.getResources().getString(R.string.error_network), 2, null);
                }

                final ResultWithInt result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*if(result.getSuccess()){
                            SharedPrefencesManager.setPin(context, pin);
                            SharedPrefencesManager.setUid(context, result.getData());
                        }
                        else{
                            ResCodeHandler.DeleteUidAndPinIfNeeded(context, result.getCode());
                        }
                        checkConnection();*/
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
}
