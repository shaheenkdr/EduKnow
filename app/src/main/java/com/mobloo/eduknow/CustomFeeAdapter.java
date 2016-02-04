package com.mobloo.eduknow;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomFeeAdapter extends RecyclerView.Adapter<CustomFeeAdapter.FeeViewHolder>
{
    DataHolder d1 = new DataHolder();
    ImageLoader imageLoader_fee = AppController.getInstance().getImageLoader();

    public  class FeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //CardView cvs;
        TextView schoolName;

        TextView feeName;
        TextView feeID;
        TextView feeDup;
        TextView feeAmountDup;
        Button payButton;
        CircleImageView schoolLogo;


        FeeViewHolder(View itemView) {
            super(itemView);


            schoolName = (TextView)itemView.findViewById(R.id.school_name_fee);

            feeDup = (TextView)itemView.findViewById(R.id.fee_dup);

            feeAmountDup = (TextView)itemView.findViewById(R.id.fee_amount_dup);



            feeName = (TextView)itemView.findViewById(R.id.fee_name);

            feeID = (TextView)itemView.findViewById(R.id.fee_id);

            schoolLogo = (CircleImageView)itemView.findViewById(R.id.school_logo);

            payButton = (Button)itemView.findViewById(R.id.pay_button);

            Typeface face= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            schoolName.setTypeface(face);
            feeAmountDup.setTypeface(face);
            feeDup.setTypeface(face);
            feeName.setTypeface(face);
            feeID.setTypeface(face);





            payButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            if(d1.feeX.get(getLayoutPosition()).getPaymentEnabled()==1 && d1.feeX.get(getLayoutPosition()).getIsPaid()==0) {
                Intent intent = new Intent(itemView.getContext(), WebViewFee.class);
                itemView.getContext().startActivity(intent);
            }

        }

    }

    private class DataHolder
    {
        List<FeePojo> feeX;

    }

    CustomFeeAdapter(List<FeePojo> feeX){
        this.d1.feeX = feeX;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public FeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fee_card, viewGroup, false);
        FeeViewHolder pvh = new FeeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FeeViewHolder feeViewHolder, int i)
    {

        feeViewHolder.feeDup.setText(String.valueOf(d1.feeX.get(i).getFeeName()));
        feeViewHolder.feeAmountDup.setText(String.valueOf(d1.feeX.get(i).getFeeAmount()));

        feeViewHolder.schoolName.setText(String.valueOf(d1.feeX.get(i).getSchoolName()));

        feeViewHolder.feeName.setText(String.valueOf(d1.feeX.get(i).getFeeName())+" of "+String.valueOf(d1.feeX.get(i).getStudentName()));
        feeViewHolder.feeID.setText("FEE ID: "+String.valueOf(d1.feeX.get(i).getFeeId()));

        if(d1.feeX.get(i).getPaymentEnabled()==0)
        {
            feeViewHolder.payButton.setVisibility(View.INVISIBLE);
        }

        else if((d1.feeX.get(i).getPaymentEnabled()==1)&&(d1.feeX.get(i).getIsPaid()==1))
        {
            feeViewHolder.payButton.setText("Paid");
            feeViewHolder.payButton.setBackgroundColor(Color.rgb(0, 153, 51));
        }

        else
        {
            feeViewHolder.payButton.setText("Pay "+ String.valueOf(d1.feeX.get(i).getFeeName()));
        }

       if(d1.feeX.get(i).getFeeCurrency().equals("USD"))
       {

           //String temp = feeViewHolder.itemView.getResources().getString(R.string.Rs);
           feeViewHolder.feeAmountDup.setText("$"+d1.feeX.get(i).getFeeAmount());
       }

        else if(d1.feeX.get(i).getFeeCurrency().equals("INR"))
       {
           feeViewHolder.feeAmountDup.setText(feeViewHolder.itemView.getResources().getString(R.string.Rs)+d1.feeX.get(i).getFeeAmount());
       }

       else if(d1.feeX.get(i).getFeeCurrency().equals("AED"))
       {
           feeViewHolder.feeAmountDup.setText("AED"+d1.feeX.get(i).getFeeAmount());
       }

       else if(d1.feeX.get(i).getFeeCurrency().equals("AUD"))
       {
           feeViewHolder.feeAmountDup.setText("A$"+d1.feeX.get(i).getFeeAmount());
       }
        else
       {
           feeViewHolder.feeAmountDup.setText(d1.feeX.get(i).getFeeAmount());
       }


        feeViewHolder.schoolLogo.setImageUrl(d1.feeX.get(i).getSchoolLogo(), imageLoader_fee);
    }

    @Override
    public int getItemCount()
    {

        if(d1.feeX!=null)
        {
            return d1.feeX.size();
        }
        else
        {
            return 0;
        }
    }

}
