package com.example.worldinscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ImageView imgbb;
    TabItem nhome,nscience,nhealth,ntech,nentertainment,nsports;
    PagerAdapter pagerAdapter;
    Toolbar ntoolbar;
    Button bb;
    static int kk=0;
    static int kkk=0;
    TextToSpeech textToSpeech, tts, ttss;

    static String a,b,c;
    ImageView but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.teal_700));

        ntoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(ntoolbar);
        nhome=findViewById(R.id.home);
        nscience=findViewById(R.id.science);
        nsports=findViewById(R.id.sports);
        ntech=findViewById(R.id.technology);
        imgbb=findViewById(R.id.imgb);
        but=findViewById(R.id.imb);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    tts.setLanguage(Locale.UK);
                }
            }
        });
        ttss = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    ttss.setLanguage(Locale.UK);
                }
            }
        });


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kkk >= 2 && kkk%2==0) {
                    kkk = 3;
                    textToSpeech.speak(a, TextToSpeech.QUEUE_FLUSH, null, "");
                    tts.speak(b, TextToSpeech.QUEUE_FLUSH, null, null);
                    ttss.speak(c,TextToSpeech.QUEUE_FLUSH, null, null);
                } else if (kkk == 3) {
                    kkk = 4;
                    textToSpeech.stop();
                    //textToSpeech.shutdown();

                    tts.stop();
                    //tts.shutdown();

                    ttss.stop();
                    //ttss.shutdown();
                    //but.setVisibility(View.GONE);
                }
            }
        });


        //nentertainment=findViewById(R.id.entertainment);
        nhealth=findViewById(R.id.health);

        ViewPager viewPager=findViewById(R.id.fragmentcontainer);
        tabLayout=findViewById(R.id.include);

        pagerAdapter=new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);

        imgbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(kk==0)
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    kk=1;
                }
                else
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    kk=0;
                }
            }
        });
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {
            Snackbar.make(findViewById(android.R.id.content), "Please check your internet connection !", Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLUE)
                    .setBackgroundTint(Color.RED)
                    .show();
            //Toast.makeText(MainActivity.this, "Please connect to internet", Toast.LENGTH_SHORT).show();
        }




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),true);
                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4 )
                {
                    if(tab.getPosition()!=0)
                    {
                        but.setVisibility(View.GONE);
                        textToSpeech.shutdown();
                    }
                    else if(tab.getPosition()==0)
                    {
                        but.setVisibility(View.VISIBLE);
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    static void get_texts(String aa, String bb, String cc) {
        a = aa;
        b = bb;
        c = cc;
        kkk = 2;
    }
}