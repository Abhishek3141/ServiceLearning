package com.abhi.servicelearningappv2;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhi.servicelearningappv2.databinding.ActivityMainBinding;
import com.abhi.servicelearningappv2.databinding.ActivityTestBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    CircularProgressIndicator progress_circular;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<ServiceModel> serviceList;
    private ServiceAdapter serviceAdapter = null;
    ServiceAdapter.OnImageClickListener onImageClickListener;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());
        mActivity = MainActivity.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);
        recyclerView = findViewById(R.id.recycler_view);
        progress_circular = findViewById(R.id.progress_circular);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        serviceList = new ArrayList<>();
        onImageClickListener = new ServiceAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int positionOfTheImage) {
                Intent intent = new Intent(MainActivity.this, ServiceItem.class);
                intent.putExtra("serviceImage", serviceList.get(positionOfTheImage).getServiceImage());
                intent.putExtra("serviceName", serviceList.get(positionOfTheImage).getServiceName());
                intent.putExtra("serviceDesc", serviceList.get(positionOfTheImage).getServiceDesc());
                startActivity(intent);

            }

        };


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Services");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ServiceModel serviceModel = dataSnapshot.getValue(ServiceModel.class);
                    serviceList.add(serviceModel);
                }
                serviceAdapter = new ServiceAdapter(mContext, mActivity, (ArrayList<ServiceModel>) serviceList, onImageClickListener);
                recyclerView.setAdapter(serviceAdapter);
                serviceAdapter.notifyDataSetChanged();
                progress_circular.setVisibility(View.GONE);



            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
