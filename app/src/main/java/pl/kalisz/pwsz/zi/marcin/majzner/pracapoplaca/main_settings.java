package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class main_settings  extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseSQLiteHelper DBHELPERCR;
private RadioButton b_engl ;
    private RadioButton b_pol ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);
        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]

        b_engl = (RadioButton)findViewById(R.id.settings_radioButton_eng);
        b_pol= (RadioButton)findViewById(R.id.settings_radioButton_pol);
        // e_font = (EditText)findViewById(R.id.www);
        // small_font
        // mid_font
        // big font

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void settings_language_change(View w){
        String lang = "" ;
        boolean checked = false;
        if(b_pol.isChecked()){
            checked = true;
            lang ="pl";
            try {
                DBHELPERCR =  new DatabaseSQLiteHelper(this);
                DBHELPERCR.open();
                DBHELPERCR.updateAPPLanguage(1);
                DBHELPERCR.close();
                } catch (SQLException e) {
                Toast toast = Toast.makeText(this, "Baza danych jest niedostępna",
                        Toast.LENGTH_LONG);
                toast.show();
               // finish();
            }

        }
        if(b_engl.isChecked()){
            checked = true;
            lang ="en";
            try {
                DBHELPERCR =           new DatabaseSQLiteHelper(this);
                DBHELPERCR.open();
                DBHELPERCR.updateAPPLanguage(0);
                DBHELPERCR.close();
            } catch (SQLException e) {
                Toast toast = Toast.makeText(this, "Baza danych jest niedostępna",
                        Toast.LENGTH_LONG);
                toast.show();
              //  finish();
            }


        }
 /*   if(checked) {
    Locale myLocale = new Locale(lang);
    Locale.setDefault(myLocale);
    Resources res = getResources();
    DisplayMetrics dm = res.getDisplayMetrics();
    Configuration conf = res.getConfiguration();
    conf.locale = myLocale;
    res.updateConfiguration(conf, dm);
    Intent refresh = new Intent(this, MainActivity.class);
    startActivity(refresh);
   // finish();
}*/
    }

    @Override
    protected void onPause(){
        super.onPause();
        DBHELPERCR.close();



    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            DBHELPERCR = new DatabaseSQLiteHelper(this);
            DBHELPERCR.open();
        }catch (SQLException e)
        {
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
