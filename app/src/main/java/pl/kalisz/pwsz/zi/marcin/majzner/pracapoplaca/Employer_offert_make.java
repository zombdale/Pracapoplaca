package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ExternalDB.DBEXHelpix;
import ExternalDB.EXConnection;

import static java.util.Calendar.DATE;

public class Employer_offert_make extends AppCompatActivity {
    DBEXHelpix DBEX = new DBEXHelpix();

    EXConnection connectionClass;

    EditText phone;
    EditText address;
    EditText address_nr;
    AutoCompleteTextView city;
    EditText post_code;
    RadioButton type_once;
    RadioButton type_rep;
    RadioButton type_rep_;
    EditText sal_a;
    EditText sal_o;
    EditText hours;
    EditText salary_once;
    EditText salary_amm;
    EditText descr ;
    EditText nazwa ;
    LinearLayout week ;

    private int id=-1;
    private int acc_type;
    DBEXHelpix DB;
    boolean week_ = false;
    boolean t_sal_o =true;
    boolean[] Tweek = new boolean[7];
    boolean t_hours = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_offert_make);

        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);

        int iint =-1;  setID(-1); setAcc_type(-1);
        if((savedInstanceState !=null) && savedInstanceState.containsKey("_iduser") && savedInstanceState.containsKey("_acctype") ){
            iint = savedInstanceState.getInt("_iduser");setID(iint);
            iint = savedInstanceState.getInt("_acctype");setAcc_type(iint);

        }
        Intent intent = getIntent();
        if(intent.hasExtra("_iduser")&&intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }

        setFields();
        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.EMPRM_city);
        String[] cities = getResources().getStringArray(R.array.filtr_cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        ACTV.setAdapter(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.EMPRM_cat);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        week = findViewById(R.id.EMPR_week);
            DB = new DBEXHelpix();
    }
    public void setFields(){
        phone= (EditText)findViewById(R.id.EMPRM_phone);
        address= (EditText)findViewById(R.id.EMPRM_address);
        address_nr= (EditText)findViewById(R.id.EMPRM_address_nr);
        city= (AutoCompleteTextView)findViewById(R.id.EMPRM_city);
        post_code= (EditText)findViewById(R.id.EMPRM_postcode);
        type_once= (RadioButton)findViewById(R.id.EMPRM_type1);
        type_rep= (RadioButton)findViewById(R.id.EMPRM_type2);
        type_rep_= (RadioButton)findViewById(R.id.EMPRM_type3);
        sal_a= (EditText)findViewById(R.id.EMPRM_salary_am);
        sal_o= (EditText)findViewById(R.id.EMPRM_salary_once);
        hours= (EditText)findViewById(R.id.EMPRM_hours);
        salary_once= (EditText)findViewById(R.id.EMPRM_salary_once);
        salary_amm= (EditText)findViewById(R.id.EMPRM_salary_am);
        descr = (EditText)findViewById(R.id.EMPRM_desc);
        nazwa = (EditText)findViewById(R.id.EMPRM_nazwa);
    }

    public void setID(int i) { this.id = i;}
    public int getID() {return  this.id; }
    public int getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(int i){
        this.acc_type =i;
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public void go_to_make(View w){

        //EditText phone= (EditText)findViewById(R.id.EMPRM_phone);
       // EditText address= (EditText)findViewById(R.id.EMPRM_address);
      //  EditText address_nr= (EditText)findViewById(R.id.EMPRM_address_nr);
     //
        String s_phone = phone.getText().toString();
        Spinner cat = (Spinner)findViewById(R.id.EMPRM_cat);
        String s_nazwa= nazwa.getText().toString();
        String s_address = address.getText().toString();
        String s_address_nr = address_nr.getText().toString();
        String s_post_code = post_code.getText().toString();
        boolean puste = false;
        if(descr.length()==0){
            descr.setText(" ");
        }
        if(s_nazwa.length()==0){
            puste=true;
        }
        if(s_phone.length()==0){
            puste = true;
        }
        if(s_phone.length()!=9){
             Toast.makeText(this, getResources().getText(R.string.validate_phone_length),Toast.LENGTH_LONG).show();

        }else {
        if(s_address.length()==0){
            puste = true;
        }
        if(s_address_nr.length()==0){
            puste = true;
        }
        if(s_post_code.length()==0){
            puste=true;
        }
        if(t_sal_o){
            if(salary_once.getText().toString().length()==0){
                puste=true;
            }
        }else {
            if(salary_amm.getText().toString().length()==0){
                puste=true;
            }
        }
        if (t_hours){
            if(hours.getText().toString().length()==0){
                puste=true;
            }
        }
        if(puste){
            Toast.makeText(this, getResources().getText(R.string.validate_empty_fields),Toast.LENGTH_SHORT).show();

        }else {
            DatePicker date_from = (DatePicker) findViewById(R.id.EMPR_date_from);
            DatePicker date_to = (DatePicker) findViewById(R.id.EMPR_date_to);
            if(!datecompare(date_from, date_to)){
                Toast.makeText(this, getResources().getText(R.string.validate_date_moreless),Toast.LENGTH_SHORT).show();
            }else {
                // *
                if(week_) {
                    int i_cat = cat.getSelectedItemPosition();
                    String[] a_cat = getResources().getStringArray(R.array.category_list);
                    /*try {
                        DB.insertEmprOffert(getID(), nazwa.getText().toString(),
                                );
                    }catch (SQLException e) {

                        Toast.makeText(this, getResources().getText(R.string.db_insert_user_error), Toast.LENGTH_SHORT).show();
                    }*/
                }

        int type = -1;
    int i_cat = cat.getSelectedItemPosition();
    String[] a_cat = getResources().getStringArray(R.array.category_list);
    if (type_once.isChecked()) {
        type = 0;
    } else {
        type = 1;
    }
     //new DATE(date_from.getDayOfMonth(), date_from.getMonth(), date_from.getYear() ).get(),  new DATE(date_to.getDayOfMonth(), date_to.getMonth(), date_to.getYear()).get()
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = simpleDateFormat.format(calendar.getTime());
                    if(type==0) {
                        boolean success = DB.insertEmprOffertWithoutWeek(getID(), nazwa.getText().toString(),
                                Integer.parseInt(phone.getText().toString()),
                                city.getText().toString(), address.getText().toString(),
                                Integer.parseInt(address_nr.getText().toString()), Integer.parseInt(post_code.getText().toString()),
                                type, Integer.parseInt(salary_once.getText().toString()), date,
                                descr.getText().toString(), i_cat);
                        if (success) {
                            Intent in = new Intent(this, account.class);
                            in.putExtra("_iduser", getID());
                            in.putExtra("_acctype", getAcc_type());
                            startActivity(in);
                        } else {
                            Toast.makeText(this, getResources().getText(R.string.db_insert_user_error).toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                if(type==1) {
                    boolean success = DB.insertEmprOffertWithoutWeekSalaryPerHours(getID(), nazwa.getText().toString(),
                            Integer.parseInt(phone.getText().toString()),
                            city.getText().toString(), address.getText().toString(),
                            Integer.parseInt(address_nr.getText().toString()), Integer.parseInt(post_code.getText().toString()),
                            type, Integer.parseInt(salary_amm.getText().toString()),  Integer.parseInt(hours.getText().toString()) ,date,
                            descr.getText().toString(), i_cat);
                    if (success) {
                        Intent in = new Intent(this, account.class);
                        in.putExtra("_iduser", getID());
                        in.putExtra("_acctype", getAcc_type());
                        startActivity(in);
                    } else {
                        Toast.makeText(this, getResources().getText(R.string.db_insert_user_error).toString(), Toast.LENGTH_SHORT).show();
                    }





                }







            }
            }
        }
    }

    public boolean datecompare(DatePicker less, DatePicker more){
        if (less.getYear()>=more.getYear()){
            if (less.getMonth()>=more.getMonth()){
                if(less.getDayOfMonth()>more.getDayOfMonth()){
                    return false;
                }
            }
        }return true;
    }
    public void EMPRM_go_once(View w) {
        sal_o.setVisibility(View.VISIBLE);
        sal_a.setVisibility(View.GONE);
        hours.setVisibility(View.GONE);
        t_hours = false;   t_sal_o = true;
        week.setVisibility(View.GONE); week_ = false;
    }
    public void EMPRM_go_rep(View w) {
        sal_o.setVisibility(View.GONE);
        sal_a.setVisibility(View.VISIBLE);
        hours.setVisibility(View.VISIBLE);
        week.setVisibility(View.VISIBLE); week_ = true;
        t_hours = true;    t_sal_o = false;
    }
    public void EMPRM_go_rep_(View w) {
        sal_o.setVisibility(View.GONE);
        sal_a.setVisibility(View.VISIBLE);
        hours.setVisibility(View.VISIBLE);
        week.setVisibility(View.VISIBLE); week_ = true;
        t_hours = true;    t_sal_o = false;
    }
    public void EMPR_check(View w) {
        CheckBox mnd = (CheckBox)findViewById(R.id.EMPR_monday);
        CheckBox th = (CheckBox)findViewById(R.id.EMPR_tuesday);
        CheckBox wed = (CheckBox)findViewById(R.id.EMPR_wednesday);
        CheckBox thu = (CheckBox)findViewById(R.id.EMPR_thursday);
        CheckBox fri = (CheckBox)findViewById(R.id.EMPR_friday);
        CheckBox sat = (CheckBox)findViewById(R.id.EMPR_saturday);
        CheckBox sun = (CheckBox)findViewById(R.id.EMPR_sunday);
        if(mnd.isChecked()){Tweek[0]=true;}else {Tweek[0]=false;}
        if(th.isChecked()){Tweek[1]=true;}else {Tweek[1]=false;}
        if(wed.isChecked()){Tweek[2]=true;}else {Tweek[2]=false;}
        if(thu.isChecked()){Tweek[3]=true;}else {Tweek[3]=false;}
        if(fri.isChecked()){Tweek[4]=true;}else {Tweek[4]=false;}
        if(sat.isChecked()){Tweek[5]=true;}else {Tweek[5]=false;}
        if(sun.isChecked()){Tweek[6]=true;}else {Tweek[6]=false;}
    }

}
