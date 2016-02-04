package com.mobloo.eduknow;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity
{
    private static final String URL = "https://api.eduknow.info/mobile/get_details";
    private static final String URL2 = "http://api.eduknow.info/mobile/register";
    private static final String URL3 = "http://api.eduknow.info/mobile/reg_device";
    private static final int REQUEST_READ_PHONE_STATE_PERMISSION = 225;
    private static int tempID;
    private static String tempToken;
    private static final String OS ="Android";
    private static String details;
    private EditText mob_code;
    private EditText mob_num;

    ArrayList<PushRegister> pushX = new ArrayList<PushRegister>();
    ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mob_code = (EditText)findViewById(R.id.codeInput);
        mob_num = (EditText)findViewById(R.id.phoneInput);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //String locale = this.getResources().getConfiguration().locale.getDisplayCountry();

        TextView t1 = (TextView)findViewById(R.id.loginCountry);
        EditText t_code = (EditText)findViewById(R.id.codeInput);

        Locale loc = new Locale("", getUserCountry(this));

        t1.setHint(loc.getDisplayCountry());

        if(loc.getDisplayCountry().equals("India"))
        {
            t_code.setText("+91");
        }

        else if(loc.getDisplayCountry().equals("Australia"))
        {
            t_code.setText("+61");
        }

        else if(loc.getDisplayCountry().equals("United Kingdom"))
        {
            t_code.setText("+44");
        }

        else if(loc.getDisplayCountry().equals("United Arab Emirates"))
        {
            t_code.setText("+971");
        }

        else
        {
            t_code.setText("+49");
        }









    }

    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }

    private class SimpleTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute()
        {
            dialog = new ProgressDialog(MainActivity.this,ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading Engine");
            dialog.show();
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                //HttpGet httpGet = new HttpGet(urls[0]);
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpClient client = new DefaultHttpClient();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("number",details));

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
            dialog.dismiss();
            showData(jsonString);
        }
    }

    private void showData(String jsonString)
    {
        try
        {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(jsonString).getAsJsonArray();


            SharedPreferences pref = getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);


            for(JsonElement obj : jArray )
            {
                MainPojo cse = gson.fromJson( obj , MainPojo.class);
                uid.add(cse);
                PushRegister tempx = new PushRegister(pref.getString("GCM_TOKEN",""),cse.getStudentId());
                Log.w("TAG::",""+tempx.getSID());
                pushX.add(tempx);
            }

            if(!pushX.isEmpty())
            {
                for(PushRegister px:pushX)
                {
                    tempToken = px.getToken();
                    tempID = px.getSID();
                    new PushKick().execute(URL3);

                }
            }

            if(!uid.isEmpty())
            {
                new SimpleTask2().execute(URL2);


            }
            else
            {
                Snackbar.make(this.findViewById(android.R.id.content), "Invalid Credentials", Snackbar.LENGTH_LONG).show();
            }

            //for(MainPojo px :uid)
            //{
           //     Log.w("EduKnow:::",""+px.getStudentId());
           // }

            //mAdapter = new CustomAdapter(posts);
            //mAdapter.notifyDataSetChanged();

        }
        catch (Exception e){
            Snackbar.make(this.findViewById(android.R.id.content), "Check data connection", Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }






    }

    public void DialogCreator(View view)
    {
       final TextView t1 = (TextView)findViewById(R.id.loginCountry);

        final CharSequence[] items = {"Australia", "United Kingdom", "United Arab Emirates", "India", "Germany"};
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Select Country:");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which)
            {
                t1.setText(items[which]);

            }

        });
        builder.create().show();


    }

    public void RegClick(View view)
    {
        String s1 = mob_code.getText().toString();
        String s2 = mob_num.getText().toString();

        if(s1.matches("")||(s2.matches("")))
        {
            Snackbar.make(this.findViewById(android.R.id.content), "All Fields are mandatory", Snackbar.LENGTH_LONG).show();

        }
        else
        {
            details = s1+s2;

            new SimpleTask().execute(URL);




        }


    }

    private class SimpleTask2 extends AsyncTask<String, Void, String> {

        ProgressDialog dialogY;

        @Override
        protected void onPreExecute() {
            dialogY = new ProgressDialog(MainActivity.this,ProgressDialog.STYLE_SPINNER);
            dialogY.setMessage("Speeding up");
            dialogY.show();
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                //HttpGet httpGet = new HttpGet(urls[0]);
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpClient client = new DefaultHttpClient();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("number",details));

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
            getHash(jsonString);
        }
    }

    public void getHash(String HashJson)
    {
        try
        {
            Gson gson = new Gson();
            HashPojo hashX = gson.fromJson(HashJson, HashPojo.class);

           // Log.w("EDUKNOW::",""+hashX.getHash());

            Intent otpGo = new Intent(MainActivity.this,OTP_Check.class);
            otpGo.putExtra("HASH",hashX.getHash());
            otpGo.putExtra("MOBILE",details);
            startActivity(otpGo);
            finish();


        }
        catch (Exception e)
        {
            Snackbar.make(this.findViewById(android.R.id.content), "Check data connection", Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private class PushKick extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {

        }

        protected String doInBackground(String... urls)
        {
            try {

                //HttpGet httpGet = new HttpGet(urls[0]);
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpClient client = new DefaultHttpClient();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                Log.w("THINGS WORK:",""+tempToken+" "+tempID);

                nameValuePairs.add(new BasicNameValuePair("device_channel",tempToken));

                nameValuePairs.add(new BasicNameValuePair("student_id",String.valueOf(tempID)));

                nameValuePairs.add(new BasicNameValuePair("os",OS));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200)
                {
                    Log.w("Eduknow:","Operation Success");
                    /*
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                    */
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return "";
        }

        protected void onPostExecute(String jsonString)
        {

        }

    }



}
