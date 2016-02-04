package com.mobloo.eduknow;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoPlayer extends AppCompatActivity
{
    private static String URL_VIDEO;
    private static String fileName;
    private static String title;
    private static String PATH;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    VideoView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Bundle extras = getIntent().getExtras();


        title = extras.getString("TITLE")+".mp4";
        URL_VIDEO = extras.getString("VIDEO");

        fileName=title;
        ProgressBack PB = new ProgressBack();

        videoView =(VideoView)findViewById(R.id.videoView1);
        PATH = getExternalCacheDir()+"/EdVideo/";


        File myFile = new File(PATH+title);

        if(myFile.exists())
        {
            DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
            android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) videoView.getLayoutParams();
            params.width =  metrics.widthPixels;
            params.height = metrics.heightPixels;
            params.leftMargin = 0;
            videoView.setLayoutParams(params);

            MediaController mediaController= new MediaController(VideoPlayer.this);
            mediaController.setAnchorView(videoView);
            Uri uri= Uri.parse(PATH+title);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    finish();
                }

            });
            videoView.start();


        }
        else
        {
            PB.execute("");


        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }



    private class ProgressBack extends AsyncTask<String,String,String> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute()
        {
            showDialog(DIALOG_DOWNLOAD_PROGRESS);

        }

        @Override
        protected String doInBackground(String... arg0)
        {
            //  DownloadFile(URL_VIDEO, "wow2.mp4");

            try {
                String RootDir = PATH;
                File RootFile = new File(RootDir);


                RootFile.mkdir();

                //RootFile.mkdir();
                // File root = Environment.getExternalStorageDirectory();
                URL u = new URL(URL_VIDEO);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();

                c.setDoOutput(false);
                int status = c.getResponseCode();
                Log.w("ERROR CODE:::::",""+status);
                c.setRequestMethod("GET");
                c.connect();

                int lenghtOfFile = c.getContentLength();

                FileOutputStream f = new FileOutputStream(new File(RootFile,
                        fileName));
                InputStream in = c.getInputStream();
                byte[] buffer = new byte[1024];
                int len1 = 0;
                long total = 0;

                while ((len1 = in.read(buffer)) > 0)
                {
                    total+=len1;
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    f.write(buffer, 0, len1);
                }
                f.close();


            } catch (Exception e) {

                Log.d("Error....", e.toString());
            }

            return "";

        }



        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        protected void onPostExecute(String result) {
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);

            DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
            android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) videoView.getLayoutParams();
            params.width =  metrics.widthPixels;
            params.height = metrics.heightPixels;
            params.leftMargin = 0;
            videoView.setLayoutParams(params);

            MediaController mediaController= new MediaController(VideoPlayer.this);
            mediaController.setAnchorView(videoView);
            Uri uri= Uri.parse(PATH+title);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    finish();
                }

            });
            videoView.start();

        }

    }


    //public void DownloadFile(String fileURL, String fileName) {


    //}
}
