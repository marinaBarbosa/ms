package com.example.marin.ms_pb_17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import android.view.WindowManager;
import android.view.Window;
import android.widget.LinearLayout;
import android.view.View;

public class IntroApp extends Activity {

    private ListView lvCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_app);

        LinearLayout fullLayoutButton = (LinearLayout) findViewById(R.id.clickLayout);

        fullLayoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(IntroApp.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

    }
}


