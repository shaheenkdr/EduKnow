package com.mobloo.eduknow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class StreamFragment extends Fragment
{
    private static String URL_FEED;
    private List<StreamPojo> StreamList = new ArrayList<StreamPojo>();
    private RecyclerView mRecyclerView;
    CustomAdapterStream StreamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stream, container, false);




        StreamAdapter = new CustomAdapterStream(StreamList);

        URL_FEED = "https://api.eduknow.info/mobile/stream";

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_streams);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.setAdapter(StreamAdapter);


        JsonArrayRequest StreamReq = new JsonArrayRequest(URL_FEED,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("EduKNOW::", response.toString());
                        //hidePDialog();

                        SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edx = Tempx.edit();
                        edx.putString("GSON_FEED_STREAM", response.toString());
                        edx.apply();

                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(Tempx.getString("GSON_FEED_STREAM", "")).getAsJsonArray();

                        //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                        for (JsonElement obj : jArray) {
                            StreamPojo spj = gson.fromJson(obj, StreamPojo.class);
                            StreamList.add(spj);

                        }
                        StreamAdapter.notifyDataSetChanged();

                        // Parsing json


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(Tempx.getString("GSON_FEED_STREAM", "")).getAsJsonArray();

                //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                StreamList.clear();

                for (JsonElement obj : jArray) {
                    StreamPojo spj = gson.fromJson(obj, StreamPojo.class);
                    StreamList.add(spj);
                    StreamAdapter.notifyDataSetChanged();
                }

                VolleyLog.d("EDUKNOW::::", "Error: " + error.getMessage());
                //hidePDialog();


            }
        });

        // Adding request to request queue

        if(StreamList.isEmpty())
        {
            AppController.getInstance().addToRequestQueue(StreamReq);
        }

        return rootView;
    }
}
