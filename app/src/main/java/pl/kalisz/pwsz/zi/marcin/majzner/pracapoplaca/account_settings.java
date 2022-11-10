package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class account_settings extends AppCompatActivity {

    private TextView welcomemessage;
    private SQLiteDatabase db;
    private Cursor cursor;
    private DatabaseSQLiteHelper DBHELPERSETTINGS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]

        welcomemessage = (TextView) findViewById(R.id.account_settings_welcome_message);
    }
    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }



}
