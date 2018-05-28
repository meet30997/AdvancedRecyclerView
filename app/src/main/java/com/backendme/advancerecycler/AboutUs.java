package com.backendme.advancerecycler;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.thefinestartist.finestwebview.FinestWebView;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element privacy = new Element();
        Element apps = new Element();
        apps.setTitle("Our Other Apps");
        apps.setIconDrawable(R.drawable.play);
        privacy.setTitle("Privacy Policy");
        privacy.setIconDrawable(R.drawable.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FinestWebView.Builder(AboutUs.this)
                        .show("http://backendme.com/kathan/privacy_policy.html");
            }
        });
        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M_Intent2developerpage();
            }
        });
        View aboutPage = new AboutPage(this)

                .isRTL(false)
                .setImage(R.drawable.about)
                .setDescription("Kooky Bytes Is A Group Of People Who Are Android Development Enthusiasts. A Creative Group that loves to learn and Create. We Introduce Our New App For Arduino Lovers That Help You To Learn Arduino In Many Ways.")
                .addItem(new Element().setTitle("Version 1.1"))
                .addGroup("Connect with us")
                .addEmail("byteskooky@gmail.com")
                .addWebsite("http://backendme.com/")
                .addItem(privacy)
                .addItem(apps)
                .create();
        setContentView(aboutPage);


    }

    private void M_Intent2developerpage() {
        Intent intentdev = new Intent(Intent.ACTION_VIEW);
        intentdev.setData(Uri.parse("market://search?q=pub:Kooky Bytes Development"));
        //here Developer Name is very case-sensitive . change your developer name as shown in developers page.
        if (!MyStartActivity(intentdev)) {
            intentdev.setData(Uri.parse("https://play.google.com/store/apps/dev?id=Kooky+Bytes+Development"));
            if (!MyStartActivity(intentdev)) {
                Toast.makeText(this, "Could not open Android Google PlayStore, please install the Google play app.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }


}
