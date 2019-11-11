package be.tahayasin.menubook.Handlers;

import android.content.Context;
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

public class ImageFactory {

    public static String getMediumName(String s){
        return "" + s;
    }
    public static String getSmallName(String s){
        return "small" + s;
    }

    public static void Save(Context context, String name, Bitmap bitmap){
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Bitmap Load(Context context, String name) throws ExecutionException, InterruptedException{
       return Load(context,name,false);
    }
    public static Bitmap Load(Context context, String name, Boolean isImageSource) throws ExecutionException, InterruptedException {
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
        if (bitmap == null && !isImageSource){
            bitmap = new DownloadImage(context).execute(name).get();
        }
        else {
            bitmap = new DownloadImageFromSource(context).execute(name).get();
        }

        return bitmap;
    }

    private static Bitmap downloadImageBitmap(String image_id){
        Bitmap bitmap = null;
        try {
            OkHttpClient client = new OkHttpClient();

            // TODO vervang session_id en session_token
            String session_id = "1bdf4577d3fb3708a1f797de8c60b804";
            String session_token = "1a3d6c836457b879c2831cb6b5ff02f08708905246664eb127875fbbc0f0efe0e741322865f4651590a20001b91473d8b79d3beb863594de6b3e17451c107eaeb537ab8f30c4a17993e89c134a9d161f33495707218f6ec6e6514d207e0c6466ac883eb69003c72fa40f0da91c0bec54152342aaf74ef7b2465e4f533b581638";

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "session_id=" + session_id + "&session_token=" + session_token);
            Request request = new Request.Builder()
                    .url(MyHttp.API + image_id)
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
    private static class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        Context context = null;
        String sUrl;
        public DownloadImage(Context context){
            this.context = context;
        }
        private Bitmap downloadImageBitmap(String image_id) {
            Bitmap bitmap = null;
            try {
                OkHttpClient client = new OkHttpClient();

                // TODO vervang session_id en session_token
                String session_id = "1bdf4577d3fb3708a1f797de8c60b804";
                String session_token = "1a3d6c836457b879c2831cb6b5ff02f08708905246664eb127875fbbc0f0efe0e741322865f4651590a20001b91473d8b79d3beb863594de6b3e17451c107eaeb537ab8f30c4a17993e89c134a9d161f33495707218f6ec6e6514d207e0c6466ac883eb69003c72fa40f0da91c0bec54152342aaf74ef7b2465e4f533b581638";

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "session_id=" + session_id + "&session_token=" + session_token);
                Request request = new Request.Builder()
                        .url("http://tablet.e-menu.be/image/n_n/" + image_id)
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
            return downloadImageBitmap(params[0]);
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