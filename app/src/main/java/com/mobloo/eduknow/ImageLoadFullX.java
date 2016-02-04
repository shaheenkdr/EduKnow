package com.mobloo.eduknow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageLoadFullX extends AppCompatActivity
{
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load_full_x);

        Bundle extras = getIntent().getExtras();
        String URL_IMAGE = extras.getString("IMAGE");
        ImageView imx = (ImageView)findViewById(R.id.imageFullx);

        Picasso.with(this).load(URL_IMAGE).into(imx);

        mAttacher = new PhotoViewAttacher(imx);

        mAttacher.update();
    }


}
