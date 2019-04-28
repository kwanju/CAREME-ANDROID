package zangdol.careme.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {


    private ImageView iv;

    private String tag;


    public ImageDownloader(final String url, ImageView iv,String tag) {
        this.iv = iv;
        this.tag = tag;
        Log.d("Go before",tag);
        DownloadTask task = new DownloadTask();
        task.execute(url);

    }

    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected synchronized Bitmap doInBackground(String... strings) {
            HttpURLConnection conn = null;
            InputStream is = null;
            try {
                conn = (HttpURLConnection) new URL(strings[0]).openConnection();
                conn.setDoInput(true);
                conn.connect();

                is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(conn!=null)
                        conn.disconnect();
                    if(is!=null)
                        is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected synchronized void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            callListener(bitmap);
            Log.d("GO",tag);
        }
    }

    private void callListener(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

}
