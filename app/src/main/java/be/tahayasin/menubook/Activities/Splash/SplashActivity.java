package be.tahayasin.menubook.Activities.Splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import be.tahayasin.menubook.Activities.MainActivity.MainActivity;
import be.tahayasin.menubook.AeSimpleSHA1;
import be.tahayasin.menubook.Constants.AppStrings;
import be.tahayasin.menubook.Constants.MyHttp;
import be.tahayasin.menubook.Handlers.HttpPostHandler;
import be.tahayasin.menubook.Handlers.MenuHandler;
import be.tahayasin.menubook.Models.NameValuePair;
import be.tahayasin.menubook.Models.ResultWithImageSources;
import be.tahayasin.menubook.Models.ResultWithInt;
import be.tahayasin.menubook.Models.ResultWithMenus;
import be.tahayasin.menubook.Models.ResultWithSalt;
import be.tahayasin.menubook.Models.ResultWithToken;
import be.tahayasin.menubook.R;
import be.tahayasin.menubook.Handlers.ResCodeHandler;
import be.tahayasin.menubook.SharedPrefencesManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkConnection();
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
//        startLockTask();
//
//        setText();
//
//        waitAndCheckConnection();
    }
    private void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean a = cm.getActiveNetworkInfo() != null;

        if(cm.getActiveNetworkInfo() == null){
            Snackbar.make(findViewById(R.id.splashrelative), "Please check your internet connection", Snackbar.LENGTH_LONG).show();
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
            Toast.makeText(this, "Get salt" + String.valueOf(uid), Toast.LENGTH_LONG).show();
            getSalt();
        }
    }
    private void getSalt(){
        final Context context = this;
        final Integer uid = SharedPrefencesManager.getUid(context);

        Thread getSaltAndChallengeThread = new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(MyHttp.API_SALT + uid)
                        .get()
                        .build();

                ResultWithSalt res;

                try {
                    Response response = client.newCall(request).execute();
                    res = new Gson().fromJson(response.body().string(), ResultWithSalt.class);
                } catch (IOException e) {
                    e.printStackTrace();
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
                            //ResCodeHandler.DeleteUidAndPinIfNeeded(context, result.getCode());
                            //checkConnection();
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
                RequestBody body = RequestBody.create(mediaType, "session_id=" + data.getData().getSession_id() + "&hash=" + hash);
                Request request = new Request.Builder()
                        .url(MyHttp.API_LOGIN)
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .build();

                ResultWithToken res;

                try {
                    Response response = client.newCall(request).execute();
                    res = new Gson().fromJson(response.body().string(), ResultWithToken.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    res = new ResultWithToken(false, e.getMessage(), 0, null);
                }

                final ResultWithToken result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getSuccess()){

                          //  openSettings(result);
                           // getDownloadsJson(result);
                            startDownloadMenuJson(result);
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

    private void getDownloadsJson(final ResultWithToken resultToken){
        final Context context = this;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPostHandler http = new HttpPostHandler(context, AppStrings.hostWithSlash + "archief/api/android/downloads.php");
                http.AddPostNameValuePair(new NameValuePair("session_id", resultToken.getData().getSession_id()));
                http.AddPostNameValuePair(new NameValuePair("session_token", resultToken.getData().getSession_token()));

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

                            startDownloadMenuJson(resultToken);
//                            ((Button) findViewById(R.id.button)).setEnabled(true);
//                            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
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
//    private void downLoadImages(ImageSrc[] imageSrcs){
//        ((TextView) findViewById(R.id.test)).setText(imageSrcs[1].getSrc());
//        for (int i = 0; i < imageSrcs.length; i++)
//           //new DownloadImage().execute(imageSrcs[i].getSrc());
//    }

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
                RequestBody body = RequestBody.create(mediaType, "name=TestName&hash=" + f_hash + "&salt=" + f_salt +  "&pin=" + pin );
                Request request = new Request.Builder()
                        .url(MyHttp.API_NEWID)
                        .post(body)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Postman-Token", "20c81bb2-98c5-4cf3-b3ef-9dc741b33219")
                        .build();

                ResultWithInt r;

                try {
                    Response response = client.newCall(request).execute();
                    r = new Gson().fromJson(response.body().string(), ResultWithInt.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    r = new ResultWithInt(false, "HTTP Error", -1, -1);
                }

                final ResultWithInt result = r;
                ((TextView) findViewById(R.id.test)).setText( String.valueOf(result.getMessage()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  ((TextView) findViewById(R.id.test)).setText( String.valueOf(result.getMessage().toString()));
                       if(result.getSuccess()){
                            SharedPrefencesManager.setPin(context, pin);
                            SharedPrefencesManager.setUid(context, result.getData());
                        }
                        else{
                            ResCodeHandler.DeleteUidAndPinIfNeeded(context, result.getCode());
                        }
                        checkConnection();
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }

    private void startDownloadMenuJson(final ResultWithToken resultWithToken){


        final Context context = this;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPostHandler http = new HttpPostHandler(context, MyHttp.API_MENU);
                http.AddPostNameValuePair(new NameValuePair("session_id", resultWithToken.getData().getSession_id()));
                http.AddPostNameValuePair(new NameValuePair("session_token", resultWithToken.getData().getSession_token()));
                http.Excecute();

                String a;

                try {
                    a = http.getResult();
                } catch (Exception e) {
                    e.printStackTrace();
                    a = e.getMessage();
                }

                final String b = a;

                ResultWithMenus res;

                try {
                    res = new Gson().fromJson(a, ResultWithMenus.class);
                }
                catch (Exception e){
                    res = new ResultWithMenus(false, b, 2, null);
                }

                final ResultWithMenus result = res;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //((TextView) findViewById(R.id.developertext)).setText(b);

                        if(result.getSuccess()){
                            Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
                            MenuHandler.storeMenu(context, result.getData().getArray());
                            ((Button) findViewById(R.id.button)).setEnabled(true);
                            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
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

    private void openMain(){
    Intent i = new Intent(this, MainActivity.class);
//        i.putExtra("session_id", resultWithToken.getData().getSession_id());
//        i.putExtra("session_token", resultWithToken.getData().getSession_token());
        startActivity(i);
        finish();
    }

//    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
//        private String TAG = "DownloadImage";
//        String sUrl;
//        private Bitmap downloadImageBitmap(String sUrl) {
//            Bitmap bitmap = null;
//            this.sUrl = sUrl;
//            try {
//                InputStream inputStream = new URL(AppStrings.imageHostWithSlash+sUrl).openStream();   // Download Image from URL
//                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
//                inputStream.close();
//            } catch (Exception e) {
//                Log.d(TAG, "Exception 1, Something went wrong!");
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            return downloadImageBitmap(params[0]);
//        }
//
//        protected void onPostExecute(Bitmap result) {
//
//            ImageFactory.Save(getApplicationContext(),sUrl,result);
//        }
//
//    }
}

