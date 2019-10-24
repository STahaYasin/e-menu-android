package be.tahayasin.menubook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import be.tahayasin.menubook.Handlers.ImageFactory;


class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    Context context;
    String sUrl;
    public DownloadImage(Context context){
        this.context = context;
    }
    private String TAG = "DownloadImage";
    private Bitmap downloadImageBitmap(String sUrl) {
        this.sUrl = sUrl;
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
            bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
            inputStream.close();
        } catch (Exception e) {
            Log.d(TAG, "Exception 1, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String[] params) {
        return downloadImageBitmap(params[0]);
    }

    protected void onPostExecute(Bitmap result) {
        ImageFactory.Save(context, this.sUrl,result);
    }
}