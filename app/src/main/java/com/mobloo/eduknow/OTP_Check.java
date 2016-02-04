package com.mobloo.eduknow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OTP_Check extends AppCompatActivity {
    private static String hash_received;
    private static String mob_no;
    private static String re_hashed;
    private static final String URL_HASH = "https://api.eduknow.info/mobile/verify";
    private static final String URL_RE = "http://api.eduknow.info/mobile/register";
    private EditText otpInput;
    private HashCreator h1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__check);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        h1 = new HashCreator();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            hash_received = bundle.getString("HASH");
            mob_no = bundle.getString("MOBILE");
        }

        SharedPreferences pref = getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putString("HASH", hash_received);
        ed.putString("MOB",mob_no);
        ed.apply();

        otpInput = (EditText)findViewById(R.id.otpTextInput);

        TextView mob = (TextView)findViewById(R.id.OTPMob);
        mob.setText(mob_no);

    }

    public void checkOtp(View view)
    {
        String s1 = otpInput.getText().toString();

        if(s1.matches(""))
        {
            Snackbar.make(this.findViewById(android.R.id.content), "Please enter the OTP", Snackbar.LENGTH_LONG).show();
        }

        else
        {
            re_hashed = h1.getmd5(s1+hash_received);
            new hashCheck().execute(URL_HASH);

        }


    }

    private class hashCheck extends AsyncTask<String, Void, String> {

        ProgressDialog dialogX;

        @Override
        protected void onPreExecute() {
            dialogX = new ProgressDialog(OTP_Check.this,ProgressDialog.STYLE_SPINNER);
            dialogX.setMessage("Verifying OTP");
            dialogX.show();

        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                //HttpGet httpGet = new HttpGet(urls[0]);
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpClient client = new DefaultHttpClient();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("number",mob_no));
                nameValuePairs.add(new BasicNameValuePair("hash",re_hashed));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            //Log.w("PREMIERE::::",result);
            return result;
        }

        protected void onPostExecute(String jsonString)
        {
            dialogX.dismiss();
            if(jsonString.contains("true"))
            {
                Intent launcher = new Intent(OTP_Check.this,FeedActivity.class);
                startActivity(launcher);
                finish();

            }
            else
            {
                Snackbar.make(OTP_Check.this.findViewById(android.R.id.content), "Invalid OTP", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    public void ReSendOTP(View view)
    {
        new ResendOTP().execute(URL_RE);
    }

    private class ResendOTP extends AsyncTask<String, Void, String> {

        ProgressDialog dialogY;

        @Override
        protected void onPreExecute() {
            dialogY = new ProgressDialog(OTP_Check.this,ProgressDialog.STYLE_SPINNER);
            dialogY.setMessage("resending");
            dialogY.show();
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                //HttpGet httpGet = new HttpGet(urls[0]);
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpClient client = new DefaultHttpClient();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("number",mob_no));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            //Log.w("PREMIERE::::",result);
            return result;
        }

        protected void onPostExecute(String jsonString)  {
            dialogY.dismiss();
            Snackbar.make(OTP_Check.this.findViewById(android.R.id.content), "OTP Sent", Snackbar.LENGTH_LONG).show();
            getHash1(jsonString);
        }
    }

    public void getHash1(String HashJson)
    {
        try
        {
            Gson gson = new Gson();
            HashPojo hashX = gson.fromJson(HashJson, HashPojo.class);
            hash_received = hashX.getHash();
            SharedPreferences pref = getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = pref.edit();
            ed.putString("HASH", hash_received);
            ed.putString("MOB",mob_no);
            ed.apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
