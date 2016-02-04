package com.mobloo.eduknow;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.mobloo.eduknow.CircleImageView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.FeedsViewHolder>
{



    DataHolder d1 = new DataHolder();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    ImageLoader imageLoader2 = AppController.getInstance().getImageLoader();


    public  class FeedsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CardView cv;
        TextView contentText;
        TextView feedDate;
        NetworkImageView n1;
        TextView studentId;
        TextView SchoolName;
        CircleImageView School_logo;



        FeedsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            contentText = (TextView)itemView.findViewById(R.id.Content_Text);
            studentId = (TextView)itemView.findViewById(R.id.Student_Name);

            n1 = (NetworkImageView)itemView.findViewById(R.id.thumbnail);
            n1.setScaleType(ImageView.ScaleType.FIT_XY);

            SchoolName =(TextView)itemView.findViewById(R.id.school_name_text);
            School_logo =(CircleImageView)itemView.findViewById(R.id.school_logo_image);

            feedDate = (TextView)itemView.findViewById(R.id.feed_date);


            Typeface face= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            contentText.setTypeface(face);

            Typeface face2= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Bold.ttf");
            SchoolName.setTypeface(face2);

            Typeface face3= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            studentId.setTypeface(face);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view)
        {

            if(d1.feeds.get(getLayoutPosition()).getFeedContentType().equals("video"))
            {
                Intent intent = new Intent(itemView.getContext(), VideoPlayer.class);
                Bundle extras = new Bundle();
                extras.putString("VIDEO", d1.feeds.get(getLayoutPosition()).getFeedContentUrl());
                extras.putString("TITLE",d1.feeds.get(getLayoutPosition()).getFeedTitle().trim());
                intent.putExtras(extras);
                itemView.getContext().startActivity(intent);
            }

            if(d1.feeds.get(getLayoutPosition()).getFeedContentType().equals("image"))
            {
                Intent intent = new Intent(itemView.getContext(), ImageLoadFullX.class);
                Bundle extras = new Bundle();
                extras.putString("IMAGE", d1.feeds.get(getLayoutPosition()).getFeedContentUrl());
                intent.putExtras(extras);
                itemView.getContext().startActivity(intent);
            }

        }
    }

    private class DataHolder
    {
        List<FeedPojo> feeds;

    }



    CustomAdapter(List<FeedPojo> mpost){
        this.d1.feeds = mpost;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }








    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        FeedsViewHolder pvh = new FeedsViewHolder(v);
        return pvh;
    }



    @Override
    public void onBindViewHolder(FeedsViewHolder feedViewHolder, int i)
    {
        DateTimeZone timeZone = DateTimeZone.getDefault();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM yyyy").withZone(timeZone);
        DateTime dateTime2 = new DateTime( d1.feeds.get(i).getFeedTime(), timeZone );
        String output = formatter.print( dateTime2 );
        Log.w("TIME IF WORKS::",""+output);




        feedViewHolder.contentText.setText(d1.feeds.get(i).getFeedContent());

        feedViewHolder.studentId.setText(d1.feeds.get(i).getStudentName());

        feedViewHolder.feedDate.setText(output);
        feedViewHolder.n1.setDefaultImageResId(R.drawable.loadx);

        if(d1.feeds.get(i).getFeedContentType().equals("image"))
        {
            feedViewHolder.n1.setImageUrl(d1.feeds.get(i).getFeedContentUrl(), imageLoader);
        }
        if(d1.feeds.get(i).getFeedContentType().equals("video"))
        {
            feedViewHolder.n1.setImageUrl("https://cdn0.iconfinder.com/data/icons/flat-green-icon-set/512/Play_Icon_FlatGreen.png", imageLoader);

        }
        else
        {
            feedViewHolder.n1.setImageUrl(d1.feeds.get(i).getFeedContentUrl(), imageLoader);
        }


        feedViewHolder.School_logo.setImageUrl(d1.feeds.get(i).getSchoolLogo(), imageLoader2);

        feedViewHolder.SchoolName.setText(d1.feeds.get(i).getSchoolName()+" "+d1.feeds.get(i).getSubName());

        //if(d1.feeds.get(i).getFeedContentType().equals("text"))
       // {
          //  feedViewHolder.n1.setVisibility(View.GONE);
       // }



    }

    @Override
    public int getItemCount()
    {

        if(d1.feeds!=null)
        {
            return d1.feeds.size();
        }
        else
        {
            return 0;
        }
    }

}
