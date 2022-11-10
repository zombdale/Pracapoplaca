package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mysql.fabric.xmlrpc.base.Array;

import java.util.ArrayList;

import ExternalDB.DBEXHelpix;

public class account_create extends AppCompatActivity {

    private static final String TAG = "#caccreate";
    private EditText acc_create_username;
    private EditText acc_create_email;
    private EditText acc_create_password;
    private EditText acc_create_repassword;

    private RadioGroup acc_create_acc_type_group;
    private RadioButton acc_create_acc_type_employee;
    private RadioButton acc_create_acc_type_employer;

    private EditText acc_create_name;
    private EditText acc_create_surname;
    private EditText acc_create_phone;
    private EditText acc_create_city;
    private EditText acc_create_nip;
    private Button acc_create_register_button;
    private Button acc_create_clean;
    private LinearLayout acc_create_nip_form;
    private RadioGroup acc_create_acc_type_empr;
    private RadioButton acc_create_acc_type_pers;
    private RadioButton acc_create_acc_type_comp;
    private boolean nipcheck;
    private boolean compcheck;

    private SQLiteDatabase db;
    private DatabaseSQLiteHelper DBHELPERCR;
    private Cursor cursor;
    private ArrayList<String> temp_fields;
    private EditText[] pola;
    private DBEXHelpix DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_basic);
        setSupportActionBar(toolbar);


        DB = new DBEXHelpix();
            setPola();
        if((savedInstanceState !=null) && savedInstanceState.containsKey("temp_fields")){
            setTempFields(savedInstanceState.getStringArrayList("temp_fields"));
            OdczytTempFields();
        }
        else if( getIntent().hasExtra("temp_fields")) {
            setTempFields( getIntent().getStringArrayListExtra("temp_fields"));
            OdczytTempFields();
        }
        else{
            this.temp_fields = new ArrayList(0);
             }

        acc_create_acc_type_group=  (RadioGroup) findViewById(R.id.acc_create_acc_type_group);
        acc_create_acc_type_employee=  (RadioButton) findViewById(R.id.acc_create_acc_type_employee);
        acc_create_acc_type_employer=  (RadioButton) findViewById(R.id.acc_create_acc_type_employer);
        acc_create_register_button=  (Button) findViewById(R.id.acc_create_register_button);
        acc_create_clean=  (Button) findViewById(R.id.acc_create_clean);
        acc_create_nip = (EditText) findViewById(R.id.acc_create_nip);
        acc_create_nip_form = (LinearLayout) findViewById(R.id.acc_create_nip_form);
        acc_create_acc_type_empr = (RadioGroup)findViewById(R.id.acc_create_acc_type_empr);
        acc_create_acc_type_comp = (RadioButton)findViewById(R.id.acc_create_acc_type_comp);
        acc_create_acc_type_pers = (RadioButton)findViewById(R.id.acc_create_acc_type_pers);

        nipcheck = true;
        compcheck = true;
    }



    @Override
    protected void onPause(){
        super.onPause();
        ZapiszTempFields();
    }
    @Override
    protected void onResume(){
        super.onResume();


        try {
           // DBHELPERCR = new DatabaseSQLiteHelper(this);


          //  cursor =  DBHELPERCR.getAllAccounts();
           // cursor.moveToFirst();
            Log.d("$BAZACREATE0", "otwarto baze kont");

        }
        catch(SQLException e){
            Log.d("$BAZACREATE1", e.toString());


        }

    }

    public void setTempFields(ArrayList<String> e){
        this.temp_fields = e;
    }

    public ArrayList<String> getTempFields(){
        return this.temp_fields;

    }
    public ArrayList<String> ZapiszTempFields(){
        ArrayList<String> temp = new ArrayList();
        for(int i=0; i<this.pola.length;i++){
          /*  if(this.pola[i].getInputType()== InputType.TYPE_CLASS_TEXT) {
                temp.add(this.pola[i].getText().toString());
            }*/

                temp.add(this.pola[i].getText().toString());
            //    Log.d("#FGDF", "i:"+temp.get(i));
        }
        setTempFields(temp);
        return temp;

    }

    public void OdczytTempFields(){
        for(int i=0;i<this.pola.length;i++){
            this.pola[i].setText(getTempFields().get(i));
        }
    }

    public void goto_create_account_radio1_employee(View view) {
        if(acc_create_acc_type_employee.isChecked()){
            acc_create_acc_type_empr.setVisibility(View.GONE);
            nipcheck=false; acc_create_nip_form.setVisibility(View.GONE);

        }
        else {
            acc_create_acc_type_empr.setVisibility(View.VISIBLE);
            nipcheck = true; acc_create_nip_form.setVisibility(View.GONE);
            nip_form_update();
        }
    }

    public void goto_create_account_radio2_employer(View view) {
        if(acc_create_acc_type_employee.isChecked()){
            acc_create_acc_type_empr.setVisibility(View.GONE);
            nipcheck=false; acc_create_nip_form.setVisibility(View.GONE);
        }
        else {
            acc_create_acc_type_empr.setVisibility(View.VISIBLE);
            nipcheck = true; nip_form_update();
        }
    }

    public void goto_create_account_radio2_type_offer(View w){
        if(acc_create_acc_type_employer.isChecked()&&acc_create_acc_type_pers.isChecked()){
            acc_create_nip_form.setVisibility(View.GONE);
            compcheck = false;
        }
        else {
            acc_create_nip_form.setVisibility(View.VISIBLE);
            compcheck = true;
            }

    }

    public void goto_create_account_radio1_type_offer(View w){
        if(acc_create_acc_type_employer.isChecked()&&acc_create_acc_type_comp.isChecked()){
            acc_create_nip_form.setVisibility(View.VISIBLE);
            compcheck = true;
                    }
        else {
            acc_create_nip_form.setVisibility(View.GONE);
            compcheck = false;
        }

        }

        public void nip_form_update(){

            if(acc_create_acc_type_employer.isChecked()&&acc_create_acc_type_pers.isChecked()){
                acc_create_nip_form.setVisibility(View.GONE);
                compcheck = false;
            }
            else {
                acc_create_nip_form.setVisibility(View.VISIBLE);
                compcheck = true;
            }

        }



    public EditText[] getPola(){
        return this.pola;

}

public void setPola(){
        this.pola = new EditText[12];
        this.pola[0] = (EditText) findViewById(R.id.acc_create_username);
    this.pola[1] = (EditText) findViewById(R.id.acc_create_email);
    this.pola[2] = (EditText) findViewById(R.id.acc_create_password);
    this.pola[3] = (EditText) findViewById(R.id.acc_create_repassword);
    this.pola[4] = (EditText) findViewById(R.id.acc_create_name);
    this.pola[5] = (EditText) findViewById(R.id.acc_create_surname);
    this.pola[6] = (EditText) findViewById(R.id.acc_create_phone);
    this.pola[7] = (EditText) findViewById(R.id.acc_create_city);
    this.pola[8] = (EditText) findViewById(R.id.acc_create_nip);
    this.pola[9] = (EditText) findViewById(R.id.acc_create_c_name);
    this.pola[10] = (EditText) findViewById(R.id.acc_create_address);
    this.pola[11] = (EditText) findViewById(R.id.acc_create_address_nr);
}

    public void goto_register(View view) {

        //Log.d("#XCZ", acc_create_city.getText().toString());

        // this.temp_fields.add(acc_create_nip                         );
        ZapiszTempFields();
        boolean cospuste = false;
        boolean error = false;
        int ck = 0;
        if(nipcheck&&compcheck){
            ck = 12;
        }
        else if(!nipcheck){
            ck=8;
        }
        else if(nipcheck&&!compcheck){
            ck = 8;
        }

        for (int i=0; i< ck;i++){
            Log.d(account_create.TAG+i, this.temp_fields.get(i));


            if(this.temp_fields.get(i).length()==0)
            {
                cospuste=true;}
        }
        if(cospuste)
        {
            Toast.makeText(this,getResources().getText(R.string.validate_empty_fields),Toast.LENGTH_LONG).show();
            }
        else {

            if (!isEmailValid(temp_fields.get(1))) {
                Toast.makeText(this, getResources().getText(R.string.validate_acc_create_email), Toast.LENGTH_LONG).show();
            }

            String password1 = this.temp_fields.get(2);
            String repassword1 = this.temp_fields.get(3);

            if (!password1.equals(repassword1)) {
                //   Toast.makeText(this, "1: "+password1+"2: "+repassword1,Toast.LENGTH_LONG).show();
                Toast.makeText(this, getResources().getText(R.string.validate_acc_password_match), Toast.LENGTH_LONG).show();
            }
            //git
            else {
                //     Toast.makeText(this, "1: "+password1+"2: "+repassword1,Toast.LENGTH_LONG).show();

                if (this.temp_fields.get(6).length() != 9) {
                    Toast.makeText(this, getResources().getText(R.string.validate_phone_length), Toast.LENGTH_LONG).show();
                }
        else{

                if (DB.CheckUserByEmail(this.temp_fields.get(1))) {
                    Toast.makeText(this, getResources().getText(R.string.validate_acc_email_verify), Toast.LENGTH_LONG).show();
                } else {
                    if (DB.CheckUserByUsname(this.temp_fields.get(0))) {
                        Toast.makeText(this, getResources().getText(R.string.validate_acc_usname), Toast.LENGTH_LONG).show();
                    } else {
                        if (DB.isPhoneRegistered((Integer.parseInt(this.temp_fields.get(6))))) {
                            Toast.makeText(this, getResources().getText(R.string.validate_phone_check), Toast.LENGTH_LONG).show();
                        } else {
                            if (nipcheck && compcheck) {
                                try {
                                    boolean success = DB.insertUserEmprCompA(this.temp_fields.get(0), this.temp_fields.get(1),
                                            this.temp_fields.get(2), 1, this.temp_fields.get(4),
                                            this.temp_fields.get(5), Integer.parseInt(this.temp_fields.get(6)),
                                            this.temp_fields.get(7), Integer.parseInt(this.temp_fields.get(8)),
                                            this.temp_fields.get(9), this.temp_fields.get(10),Integer.parseInt(this.temp_fields.get(11)));
                                    if (success) {
                                        Intent in = new Intent(this, account_login.class);
                                        startActivity(in);
                                    } else {
                                        Toast.makeText(this, getResources().getText(R.string.db_insert_user_error) + "1", Toast.LENGTH_LONG).show();
                                    }
                                } catch (SQLException e) {
                                    Log.e("błąd acccreate1", e.toString());
                                    Toast.makeText(this, getResources().getText(R.string.db_insert_user_error) + "2", Toast.LENGTH_LONG).show();
                                }
                            } else if (!nipcheck) {
                                try {
                                    boolean success = DB.insertUserEmpe(this.temp_fields.get(0), this.temp_fields.get(1),
                                            this.temp_fields.get(2), 0, this.temp_fields.get(4), this.temp_fields.get(5),
                                            Integer.parseInt(this.temp_fields.get(6)), this.temp_fields.get(7));
                                    if (success) {
                                        Intent in = new Intent(this, account_login.class);
                                        startActivity(in);
                                    } else {
                                        Toast.makeText(this, getResources().getText(R.string.db_insert_user_error) + "3", Toast.LENGTH_LONG).show();
                                    }
                                } catch (SQLException e) {
                                    Log.e("błąd acccreate2", e.toString());
                                }
                            } else if (nipcheck && !compcheck) {
                                try {
                                    //(String usname, String email, String pass, int type,
                                    //                                  String name, String surname, int phone, String city)
                                    boolean success = DB.insertUserEmpr(this.temp_fields.get(0), this.temp_fields.get(1),
                                            this.temp_fields.get(2), 1, this.temp_fields.get(4), this.temp_fields.get(5),
                                            Integer.parseInt(this.temp_fields.get(6)), this.temp_fields.get(7) );
                                    if (success) {
                                        Intent in = new Intent(this, account_login.class);
                                        startActivity(in);
                                    } else {
                                        Toast.makeText(this, getResources().getText(R.string.db_insert_user_error) + "4", Toast.LENGTH_LONG).show();
                                    }
                                } catch (SQLException e) {
                                Log.e("błąd acccreate3", e.toString());
                                }
                            }

                        }

                    }

                }

            }
        }

        }




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
                Intent intent1 = new Intent(this, account_login.class);
                 startActivity(intent1);
                return true;
            default : return super.onOptionsItemSelected(item);

        }

    }

    public void goto_clean(View view) {


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ZapiszTempFields();
            outState.putStringArrayList("temp_fields", getTempFields());
    }

    @Override
    public void onBackPressed() {
        ZapiszTempFields();
        Intent in = new Intent(this, account.class);
        in.putExtra("temp_fields",getTempFields() );
        startActivity(in);
       // super.onBackPressed();

    }

        public boolean isEmailValid(String email){

            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
}
