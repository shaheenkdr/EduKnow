package com.mobloo.eduknow;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomAdapterStream extends RecyclerView.Adapter<CustomAdapterStream.FeedsViewHolder>
{
    DataHolder d1 = new DataHolder();
    ImageLoader imageLoader2 = AppController.getInstance().getImageLoader();

    public  class FeedsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //CardView cvs;
        TextView streamContent;
        TextView streamTitle;
        NetworkImageView streamPoster;
        NetworkImageView dummyStream;

        FeedsViewHolder(View itemView) {
            super(itemView);
            //cvs = (CardView)itemView.findViewById(R.id.cvsx);

            streamContent = (TextView)itemView.findViewById(R.id.stream_content);
            streamTitle = (TextView)itemView.findViewById(R.id.stream_title);

            streamPoster = (NetworkImageView)itemView.findViewById(R.id.stream_poster);
            streamPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);

            dummyStream = (NetworkImageView)itemView.findViewById(R.id.stream_play_dummy);



            Typeface face= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            streamContent.setTypeface(face);



            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {

            if(d1.streams.get(getLayoutPosition()).getStreamType().equals("youtube"))
            {
               itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(d1.streams.get(getLayoutPosition()).getStreamUrl())));
            }

            if(d1.streams.get(getLayoutPosition()).getStreamType().equals("video"))
            {
                Intent intent = new Intent(itemView.getContext(), VideoPlayer.class);
                Bundle extras = new Bundle();
                extras.putString("VIDEO", d1.streams.get(getLayoutPosition()).getStreamUrl());
                extras.putString("TITLE","Stream");
                intent.putExtras(extras);
                itemView.getContext().startActivity(intent);
            }

            if(d1.streams.get(getLayoutPosition()).getStreamType().equals("image"))
            {
                Intent intent = new Intent(itemView.getContext(), ImageLoadFullX.class);
                Bundle extras = new Bundle();
                extras.putString("IMAGE", d1.streams.get(getLayoutPosition()).getStreamUrl());
                intent.putExtras(extras);
                itemView.getContext().startActivity(intent);
            }

        }

    }

    private class DataHolder
    {
        List<StreamPojo> streams;

    }

    CustomAdapterStream(List<StreamPojo> mstream){
        this.d1.streams = mstream;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stream_card, viewGroup, false);
        FeedsViewHolder pvh = new FeedsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder feedViewHolder, int i)
    {
        feedViewHolder.streamContent.setText(d1.streams.get(i).getStreamContent());
        feedViewHolder.streamTitle.setText(d1.streams.get(i).getStreamName());


        if(d1.streams.get(i).getStreamType().equals("youtube"))
        {
            feedViewHolder.streamPoster.setImageUrl(d1.streams.get(i).getStreamPoster(), imageLoader2);
            feedViewHolder.dummyStream.setImageUrl("http://i.imgur.com/ZvxVGWF.png",imageLoader2);
        }
        else
        {
            feedViewHolder.streamPoster.setImageUrl(d1.streams.get(i).getStreamUrl(), imageLoader2);
        }
    }

    @Override
    public int getItemCount()
    {

        if(d1.streams!=null)
        {
            return d1.streams.size();
        }
        else
        {
            return 0;
        }
    }
}
