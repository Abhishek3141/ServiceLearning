package com.abhi.servicelearningappv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.servicelearningappv2.databinding.ActivityServiceItemBinding;
import com.abhi.servicelearningappv2.databinding.ActivityTestBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class ServiceItem extends BaseActivity {

    TextView titleText;
    ImageView postImg;
    TextView description;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActivityServiceItemBinding activityServiceItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServiceItemBinding = ActivityServiceItemBinding.inflate(getLayoutInflater());

        setContentView(activityServiceItemBinding.getRoot());
        titleText = findViewById(R.id.title_text);
        postImg = findViewById(R.id.post_img);
        description = findViewById(R.id.imageDesc);




        Intent intent = getIntent();
        String ServiceName = intent.getStringExtra("serviceName");
        String ServiceImage = intent.getStringExtra("serviceImage");
        String ServiceDesc = intent.getStringExtra("serviceDesc");


        titleText.setText(ServiceName);
        description.setText(ServiceDesc);
        Glide.with(this).load(ServiceImage).into(postImg);




    }


}