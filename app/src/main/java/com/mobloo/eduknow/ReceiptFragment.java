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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ReceiptFragment extends Fragment
{
    private static String URL_FEE;
    private List<FeePojo> fee = new ArrayList<FeePojo>();
    private RecyclerView feeList;
    CustomFeeAdapter feeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receipt, container, false);

        feeAdapter = new CustomFeeAdapter(fee);
        SharedPreferences pref = this.getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
        URL_FEE = " https://api.eduknow.info/mobile/fees/buttercup/"+pref.getString("MOB","");
        Log.w("CHECK::",""+URL_FEE);
        feeList = (RecyclerView)rootView.findViewById(R.id.rv_fee);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        feeList.setLayoutManager(llm);
        feeList.setAdapter(feeAdapter);


        JsonArrayRequest feeReq = new JsonArrayRequest(URL_FEE,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("EDUKNOW::FEE", response.toString());
                        //hidePDialog();

                        SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edx = Tempx.edit();
                        edx.putString("GSON_FEE", response.toString());
                        edx.apply();

                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(Tempx.getString("GSON_FEE","")).getAsJsonArray();

                        //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                        for(JsonElement obj : jArray )
                        {
                            FeePojo cse = gson.fromJson( obj , FeePojo.class);
                            fee.add(cse);

                        }
                        Log.w("EDUKNO::CHECK",""+fee.get(0).getSchoolName());
                        feeAdapter.notifyDataSetChanged();

                        // Parsing json








                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                SharedPreferences Tempx = getActivity().getSharedPreferences("ActivitySession", Context.MODE_PRIVATE);

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(Tempx.getString("GSON_FEE","")).getAsJsonArray();

                //ArrayList<MainPojo> uid = new ArrayList<MainPojo>();

                fee.clear();

                for(JsonElement obj : jArray )
                {
                    FeePojo cse = gson.fromJson( obj , FeePojo.class);
                    fee.add(cse);
                    feeAdapter.notifyDataSetChanged();
                }

                VolleyLog.d("EDUKNOW::::", "Error: " + error.getMessage());
                //hidePDialog();


            }
        });

        if(fee.isEmpty()){
            AppController.getInstance().addToRequestQueue(feeReq);
        }
        return rootView;
    }
}
