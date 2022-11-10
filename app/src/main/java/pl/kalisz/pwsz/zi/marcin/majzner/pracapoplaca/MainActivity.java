package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.security.Permission;

import ExternalDB.DBEXHelpix;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "#$%ACTVMAIN";
    private TextView welcomemessage;
    private int _iduser=-1;
    private int acc_type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        int iint =-1;  setID(-1); setAcc_type(-1);
        if((savedInstanceState !=null) && savedInstanceState.containsKey("_iduser") && savedInstanceState.containsKey("_acctype") ){
            setID(savedInstanceState.getInt("_iduser"));setID(iint);
            setAcc_type(savedInstanceState.getInt("_acctype"));setAcc_type(iint);

        }
        Intent intent = getIntent();
        if(intent.hasExtra("_iduser") && intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }


        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        welcomemessage = (TextView) findViewById(R.id.activity_main_welcome_message);
        if(_iduser>=0){
        DBEXHelpix DB = new DBEXHelpix();
        welcomemessage.setText(DB.getUserById(_iduser)[1]);}else{
            welcomemessage.setText(R.string.welcome_unlogged);
            }
//validateRequestPermissionsRequestCode(Request.);
//        requestPermissions();
    }

    @Override
    protected void onPause(){
        super.onPause();
           }

    @Override
    protected void onResume() {

        Intent intent = getIntent();
        if(intent.hasExtra("_iduser")&&intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }


        super.onResume();

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


    @Override
    public void onBackPressed() {
       super.onBackPressed();


    }

    public void goto_pracownik_main(View view ){

        Intent intent1 = new Intent(this, Pracownik_main.class);
        intent1.putExtra("_iduser", getID());
        intent1.putExtra("_acctype", getAcc_type());
        startActivity(intent1);

    }

    public void goto_pracodawca_main(View view ){

        Intent intent2 = new Intent(this, Pracodawca_main.class);
        intent2.putExtra("_iduser", getID());
        intent2.putExtra("_acctype", getAcc_type());
        startActivity(intent2);

    }
    public void goto_create_account_intro(View view ){

        Intent intent3 = new Intent(this, account_create.class);
        startActivity(intent3);

    }
    public void goto_login_account_intro(View view ){

        Intent intent3 = new Intent(this, account_login.class);
        startActivity(intent3);

    }
    public void goto_guest_create_intro(View view ){
        Intent intent4 = new Intent(this, MainActivity.class);
        startActivity(intent4);
    }
    public int getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(int i){
        this.acc_type =i;
    }
    public void setID(int i){
        this._iduser =i ;

    }
    public int getID(){return this._iduser;}
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
 }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.main_menu_settings1 :
                Intent intent = new Intent(this, main_settings.class);
                startActivity(intent);
                return true;
            case R.id.main_menu_account1 :
                //extras czy zalogowany czy cos ++ dodac
                Intent intent1 = new Intent(this, account.class);
                intent1.putExtra("_iduser", getID());
                intent1.putExtra("_acctype", getAcc_type());
                startActivity(intent1);
                return true;
            default : return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getID()!=-1){outState.putInt("_iduser", getID());}
        if(getAcc_type()!=-1){outState.putInt("_acctype", getAcc_type());}
    }


}
