package com.backendme.advancerecycler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class DetailsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TableLayout table_layout;
    private File imagePath;
    private String message,url;
    private TabLayout tabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mImageView = (ImageView) findViewById(R.id.imageView);


        File dir = getApplicationContext().getCacheDir();
        if (dir != null && dir.isDirectory()) {
            try {
                File[] children = dir.listFiles();
                if (children.length > 0) {
                    for (int i = 0; i < children.length; i++) {
                        File[] temp = children[i].listFiles();
                        for (int x = 0; x < temp.length; x++) {
                            temp[x].delete();
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("Cache", "failed cache clean");
            }
        }


        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        url = bundle.getString("url");
        getSupportActionBar().setTitle(message);


        Glide.with(this)
                .load(url)
                .override(600,400)
                .into(mImageView);






        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {


                } else if (tab.getPosition() == 1) {



                } else {



                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }






    @Override
    public void onBackPressed() {

        DetailsActivity.super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                DetailsActivity.super.onBackPressed();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
