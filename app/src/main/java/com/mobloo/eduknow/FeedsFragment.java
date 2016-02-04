package com.mobloo.eduknow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FeedsFragment extends Fragment
{
    private static String URL_FEED;
    private List<FeedPojo> FeedList = new ArrayList<FeedPojo>();
    private RecyclerView mRecyclerView;
    CustomAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.feeds, container, false);

        mAdapter = new CustomAdapter(FeedList);

        SharedPreferences pref = this.getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
        URL_FEED = "http://api.eduknow.info/mobile/feeds/buttercup/"+pref.getString("MOB","");

        mRecyclerView =(RecyclerView)rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.setAdapter(mAdapter);




        JsonArrayRequest movieReq = new JsonArrayRequest(URL_FEED,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("PREMIERE:::FUCK:::", response.toString());
                        //hidePDialog();

                        SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edx = Tempx.edit();
                        edx.putString("GSON_FEED", response.toString());
                        edx.apply();

                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(Tempx.getString("GSON_FEED","")).getAsJsonArray();

                        //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                        FeedList.clear();

                        for(JsonElement obj : jArray )
                        {
                            FeedPojo cse = gson.fromJson( obj , FeedPojo.class);
                            FeedList.add(cse);

                        }
                        mAdapter.notifyDataSetChanged();

                        // Parsing json








                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(Tempx.getString("GSON_FEED","")).getAsJsonArray();

                //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                for(JsonElement obj : jArray )
                {
                    FeedPojo cse = gson.fromJson( obj , FeedPojo.class);
                    FeedList.add(cse);
                    mAdapter.notifyDataSetChanged();
                }

                VolleyLog.d("EDUKNOW::::", "Error: " + error.getMessage());
                //hidePDialog();


            }
        });

        // Adding request to request queue
        if(FeedList.isEmpty())
        {
            AppController.getInstance().addToRequestQueue(movieReq);
        }
            return rootView;
    }


}
