package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ExternalDB.DBEXHelpix;
import ExternalDB.EXConnection;

public class account_login extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseSQLiteHelper DBHELPERLOGIN;
    private Cursor cursor;
    private EditText useremail;
    private EditText userpassword;
    private Button login_button;
    static final String TAG = "ACCLOG";
    private int id = -1;
    private int acc_type =-1;
        private EXConnection connectionClass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_basic);
        setSupportActionBar(toolbar);

        useremail = (EditText) findViewById(R.id.login_user);
        userpassword = (EditText) findViewById(R.id.login_password);
        login_button = (Button) findViewById(R.id.login_button_login);
        connectionClass= new EXConnection();
    }


    public void goto_login_procced2(View v){
String mail = (String)useremail.getText().toString();
        String pass= (String)userpassword.getText().toString();
        if(mail.length()==0&&pass.length()==0){
            Toast.makeText(this, "Pola nie mogą być puste", Toast.LENGTH_LONG).show();
        }

        // walidacja
        else {
            try {
                Log.d(account_login.TAG + 1, "try ");
                DBHELPERLOGIN = new DatabaseSQLiteHelper(this);
                DBHELPERLOGIN.open();

                if (DBHELPERLOGIN.tryLogin(mail, pass)) {

                    Log.d("@@#trylogin", "passed");
                    DBHELPERLOGIN.insertAPPAccStart((DBHELPERLOGIN.getAccbyMail(mail).getString(1)), 1, 2, 1);
                    Log.d("@@#tryinsertacc", "passed");
                    DBHELPERLOGIN.close();
                    Intent e = new Intent(this, MainActivity.class);
                    startActivity(e);


                } else {
                    Toast toast = Toast.makeText(this, "Niepoprawny username lub hasło.", Toast.LENGTH_LONG);

                }


            } catch (SQLException e) {
                Log.d(account_login.TAG + 2, "catch ");
                Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_LONG);
                toast.show();
                Log.v(MainActivity.TAG, e.toString() + "DB.onCreate");
                finish();
            }
        }


    }


    public void  goto_register_acc(View w){
        Intent intetn = new Intent(this, account_create.class);
        startActivity(intetn);


    }
    public void goto_login_procced(View v){

        try{
            DBEXHelpix DB = new DBEXHelpix();
            String mail = (String)useremail.getText().toString();
            String pass= (String)userpassword.getText().toString();
            if(mail.length()==0&&pass.length()==0){
                Toast.makeText(this, "Pola nie mogą być puste", Toast.LENGTH_LONG).show();
            }
        else {
            if(!isEmailValid(mail)){
                Toast.makeText(this, R.string.validate_login_email, Toast.LENGTH_LONG).show();
            }else{

                //Toast.makeText(this, "name:"+mail+" pass: "+pass, Toast.LENGTH_LONG).show();
                try{
                    if (!DB.CheckUserByEmail(mail)) {
                        Toast.makeText(this, R.string.wrong_user, Toast.LENGTH_LONG).show();
                    }else{
                if(!DB.CheckPassForEmail(mail, pass)){
                    Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_LONG).show();
                    userpassword.setText("");
                }else {

                int i=DB.getUserIdByEmailAndPass(mail, pass);
                int o =DB.getUserAccType(i);
                if(i!=-1) {
                  //  Toast.makeText(this, "id: "+result[0]+"name:"+result[1], Toast.LENGTH_LONG).show();

                   setIdUser(i); setAcc_type(o);
                   Intent intent= new Intent(this, account.class);
                   intent.putExtra("_iduser", getID());intent.putExtra("_acctype", getAcc_type());
                   startActivity(intent);
                }
                else {
                    Toast.makeText(this, "problem z baza. button in Dbhelpix", Toast.LENGTH_LONG).show();

                }

                    }}}catch(java.sql.SQLException e){
                    Log.e("#DBH2", e.toString());

                }
        } }

        }catch (SQLException e){
            Log.e("#ERROR5", " gotoligin_procced");

        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getID()!=-1){outState.putInt("_iduser", getID());}
        if(getAcc_type()!=-1){outState.putInt("_acctype", getAcc_type());}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_basic_back:
                //extras czy zalogowany czy cos ++ dodac
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("_iduser", getID());intent1.putExtra("_acctype", getAcc_type());
                startActivity(intent1);
                return true;
            default : return super.onOptionsItemSelected(item);

        }

    }

    public int getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(int i){
        this.acc_type =i;
    }

    private void setIdUser( int _id ){
        this.id = _id;

    }
    private int getID( ){
        return this.id;

    }


    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, MainActivity.class);
        in.putExtra("_iduser", getID());in.putExtra("_acctype", getAcc_type());
                startActivity(in);

        //super.onBackPressed();
        /*
        Intent in = new Intent(this, account.class);
        in.putExtra("_iduser", getID());
        startActivity(in);*/
    }


    public boolean isEmailValid(String email){

    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}