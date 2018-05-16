package com.example.android_srinivas.languagechange;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
       ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        Button changeLang=(Button)findViewById(R.id.changelanguage);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });
        // Modified By Santosh
        //data updated by srinivas
        int x=10;
        System.out.println("data in the x"+x);
    }

    private void showChangeLanguageDialog() {
        final String[] listitems={"English","తెలుగు"};
        AlertDialog.Builder DialogBuilder=new AlertDialog.Builder(MainActivity.this);
        DialogBuilder.setTitle("Choose Language...");
        DialogBuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
                else if(i==1){
                    setLocale("te");
                    recreate();
                }
                dialog.dismiss();
            }
        });

        AlertDialog mDialog=DialogBuilder.create();
        mDialog.show();
    }

    private void setLocale(String Lang) {
        Locale locale=new Locale(Lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("my_lang",Lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("my_lang","");
        setLocale(language);
    }
}
