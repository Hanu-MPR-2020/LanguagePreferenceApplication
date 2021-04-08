package com.mpr.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView tvLanguage;
    public static final String P_LANG = "LANG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Widget references
        tvLanguage = findViewById(R.id.tvLanguage);

        //Get SharePreferences
         sharedPreferences = this.getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String language = sharedPreferences.getString(P_LANG, null);

        //Check language preference
        //if it already set
        if(language!= null){
            // update textview  to display
            tvLanguage.setText(language);
        }else {      //else
            //display dialog for language
            selectLanguage();
        }





        //Handle event
        //Some change here

    }

    /**
     * @effects
     * Display the dialog for language
     */
    public void selectLanguage(){
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("Vietnamese");

                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("English");
                    }
                })
                .show();
    }

    /**
     * @effects
     *  update language preference
     *  update textview with selected language
     * @param language
     */
    public void setLanguage(String language){
        //update language preference
        sharedPreferences.edit().putString(P_LANG, language).apply();

        //update textview with selected language
        tvLanguage.setText(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch(item.getItemId()){
            case R.id.reset_menu_item:
                sharedPreferences.edit().remove(P_LANG).apply();
                break;
            case R.id.Eng_menu_item:
                setLanguage("English");
                break;
            case R.id.viet_menu_item:
                setLanguage("Vietnamese");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
