package be.tahayasin.menubook.Handlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import be.tahayasin.menubook.Constants.AppStrings;

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
    public static Bitmap Load(Context context, String name) throws ExecutionException, InterruptedException {
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
        if (bitmap==null)
            return new DownloadImage(context).execute(name).get();

        return bitmap;
    }
    private static class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        Context context = null;
        String sUrl;
        public DownloadImage(Context context){
            this.context = context;
        }
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            this.sUrl = sUrl;
            try {
                InputStream inputStream = new URL(AppStrings.imageHostWithSlash+sUrl).openStream();   // Download Image from URL
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