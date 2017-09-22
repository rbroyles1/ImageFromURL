package com.example.rxbro.imagefromurl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private ImageView imageView;
    //URL to get image
    final String ImgURL = "https://www.google.com/images/srpr/logo1w.png";
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.rl);
        button = (Button)findViewById(R.id.btnImage);
        imageView = (ImageView)findViewById(R.id.ivImageFromWeb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadImageTask(imageView).execute(ImgURL);
            }
        });
    }
    // Helper class to download image.  AsyncTask enables proper and easy
    // use of the UI thread.  This class will download the image in the background
    // and show the image without having to manipulate any threads or handlers.

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> { // <input, update, result>
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }
        // doInBackground -- perform something in the background
        @Override
        protected Bitmap doInBackground(String...urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return logo;
        }
        // onPostExecute method - Runs on UI thread after doInBackground
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
