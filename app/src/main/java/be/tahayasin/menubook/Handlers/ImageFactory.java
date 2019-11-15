package be.tahayasin.menubook.Handlers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import be.tahayasin.menubook.Constants.AppStrings;
import be.tahayasin.menubook.Constants.MyHttp;
import be.tahayasin.menubook.R;
import be.tahayasin.menubook.SharedPrefencesManager;

public class ImageFactory {

    public static void Save(Context context, String name, Bitmap bitmap){
        if (bitmap == null)
            return;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Bitmap Load(Context context,String url, String name) throws ExecutionException, InterruptedException{
       return Load(context,url,name,false);
    }
    public static Bitmap Load(Context context, String url, String name, Boolean isImageSource) throws ExecutionException, InterruptedException {
        if (name == null)
            return null;
        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        try{
            fileInputStream = context.openFileInput(name);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (!isImageSource){
            //bitmap = new DownloadImage(context).execute(url,name).get();
            bitmap = downloadImageBitmap(context,url,name);
        }
        else {
            bitmap = new DownloadImageFromSource(context).execute(MyHttp.SenS + name).get();
        }

        Save(context,name,bitmap);

        return bitmap;
    }
    public static Integer getLanguageId(String id){
        switch (id){
            case "nl":
                return R.drawable.nl;
            case "fr":
                return R.drawable.fr;
            case "en":
                return R.drawable.gb;
            case "tr":
                return R.drawable.tr;
            default:
                return R.drawable.tr;
        }
    }
    private static Bitmap downloadImageBitmap(Context context,String URL,String image_id){
        Bitmap bitmap = null;
        try {
            OkHttpClient client = new OkHttpClient();

            // TODO vervang session_id en session_token
            String session_id = SharedPrefencesManager.getSessionId(context);
            String session_token = SharedPrefencesManager.getSessionKey(context);
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "session_id=" + session_id + "&session_token=" + session_token);
            Request request = new Request.Builder()
                    .url(URL + image_id)
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "6d5685bc-60d1-0215-18d2-57c2075c6ace")
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                InputStream inputStream = response.body().byteStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                bitmap = null;
            }
        }
        catch (Exception e){
            bitmap = null;
        }

        Save(context,image_id,bitmap);
        return bitmap;
    }
    private static class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        Context context = null;
        String sUrl;
        public DownloadImage(Context context){
            this.context = context;
        }
        private Bitmap downloadImageBitmap(String URL, String image_id) {
            Bitmap bitmap = null;
            try {
                OkHttpClient client = new OkHttpClient();

                // TODO vervang session_id en session_token
                String session_id = SharedPrefencesManager.getSessionId(context);
                String session_token = SharedPrefencesManager.getSessionKey(context);
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "session_id=" + session_id + "&session_token=" + session_token);
                Request request = new Request.Builder()
                        .url(URL + image_id)
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "6d5685bc-60d1-0215-18d2-57c2075c6ace")
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    InputStream inputStream = response.body().byteStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    bitmap = null;
                }
            }
            catch (Exception e){
                bitmap = null;
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0],params[1]);
        }

        protected void onPostExecute(Bitmap result) {

            Save(context,sUrl,result);
        }

    }

    private static class DownloadImageFromSource extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        Context context = null;
        String sUrl;
        public DownloadImageFromSource(Context context){
            this.context = context;
        }
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            this.sUrl = sUrl;
            try {
                InputStream inputStream = new URL(MyHttp.IMAGES +sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception 1, Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {

            Save(context,sUrl,result);
        }

    }


}