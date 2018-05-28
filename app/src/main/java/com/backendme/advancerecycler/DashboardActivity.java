package com.backendme.advancerecycler;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TextView;

import com.backendme.advancerecycler.fragment.Countries;
import com.backendme.advancerecycler.fragment.CarLogos;
import com.backendme.advancerecycler.fragment.Movies;
import com.google.android.gms.ads.AdView;
import com.thefinestartist.Base;
import com.thefinestartist.finestwebview.FinestWebView;

import static com.thefinestartist.utils.content.ContextUtil.getSystemService;


public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int ITEMS_PER_AD = 8;
    private FragmentManager fragmentManager;
    private AdView mAdView;
    private BroadcastReceiver mRegistrationBroadcastReceiver;





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new Countries(),"Countries");
                    return true;
                case R.id.navigation_shield:
                    loadFragment(new CarLogos(),"CarLogos");
                    return true;
                    case R.id.navigation_projects:
                    loadFragment(new Movies(),"Movies");
                    return true;
            }

            return false;
        }
    };
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Base.initialize(this);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Specify a linear layout manager.

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        loadFragment(new Countries(),"Countries");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView textView2 = (TextView)header.findViewById(R.id.tx2);

        }

    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int seletedItemId = bottomNavigationView.getSelectedItemId();

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(Gravity.LEFT, false);
        }else if (R.id.navigation_home != seletedItemId) {
            setHomeItem(DashboardActivity.this);
        }else {

            finish();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public static void setHomeItem(Activity activity) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                activity.findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Advanced RecyclerView");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.backendme.advancerecycler";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(Gravity.LEFT, false);


        } else if (id == R.id.aboutus) {
          DrawerLayout drawer = findViewById(R.id.drawer_layout);
          BottomNavigationView navigation = findViewById(R.id.navigation);
          drawer.closeDrawer(Gravity.LEFT, false);
          Intent intent = new Intent(this, AboutUs.class);
          startActivity(intent);

        } else if (id == R.id.rateus) {
          DrawerLayout drawer = findViewById(R.id.drawer_layout);
          BottomNavigationView navigation = findViewById(R.id.navigation);
          drawer.closeDrawer(Gravity.LEFT, false);
          Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
          Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
          // To count with Play market backstack, After pressing back button,
          // to taken back to our application, we need to add following flags to intent.
          goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
          try {
                startActivity(goToMarket);
              } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
        }else if (id == R.id.feedback) {
          DrawerLayout drawer = findViewById(R.id.drawer_layout);
          BottomNavigationView navigation = findViewById(R.id.navigation);
          drawer.closeDrawer(Gravity.LEFT, false);
          Intent intent = new Intent(this,FeedbackActivity.class);
          startActivity(intent);

          }else if (id == R.id.git) {
          DrawerLayout drawer = findViewById(R.id.drawer_layout);
          BottomNavigationView navigation = findViewById(R.id.navigation);
          drawer.closeDrawer(Gravity.LEFT, false);
          new FinestWebView.Builder(DashboardActivity.this)
                  .show("https://github.com/kookybytes");
      }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.LEFT, false);
        return true;
    }




    @Override
    protected void onResume() {
        super.onResume();


    }

    private void loadFragment(Fragment fragment , String Tag) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment,Tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}