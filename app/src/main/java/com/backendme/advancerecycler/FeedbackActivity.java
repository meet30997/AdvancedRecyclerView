package com.backendme.advancerecycler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    private Button button;
    private EditText email;
    private EditText sub;
    private EditText msg;
    private String email1 = "byteskooky@gmail.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("Feedback");
        button = (Button)findViewById(R.id.btn);
        sub = (EditText)findViewById(R.id.sub);
        msg = (EditText)findViewById(R.id.msg);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String sub1 = sub.getText().toString();
                final String msg1 = msg.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email1));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub1);
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg1);


                startActivity(Intent.createChooser(emailIntent, "FeedBack"));
            }
        });
    }
}
