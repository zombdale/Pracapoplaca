package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import ExternalDB.DBEXHelpix;
import ExternalDB.EXConnection;

public class Employee_offert_make extends AppCompatActivity {
    DBEXHelpix DBEX = new DBEXHelpix();

    EXConnection connectionClass;
    private int id = -1;
    private int acc_type;
    DBEXHelpix DB;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_offert_make);

        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);

        int iint = -1;
        setID(-1);
        setAcc_type(-1);
        if ((savedInstanceState != null) && savedInstanceState.containsKey("_iduser") && savedInstanceState.containsKey("_acctype")) {
            iint = savedInstanceState.getInt("_iduser");
            setID(iint);
            iint = savedInstanceState.getInt("_acctype");
            setAcc_type(iint);

        }
        Intent intent = getIntent();
        if (intent.hasExtra("_iduser") && intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }
        // AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.EMPEM_city);
        String[] cities = getResources().getStringArray(R.array.filtr_cities);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        ACTV.setAdapter(adapter);*/
        Spinner spinner = (Spinner) findViewById(R.id.EMPE_OF_M_cat);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        DB = new DBEXHelpix();
// lista
        listView = (ListView) findViewById(R.id.EMPEE_OFFERT_MAKE_list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) list);
/*        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

            }simple_list_item_activated_1
        };


        listView.setOnItemClickListener(itemClickListener);*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("Edytuj Tag");
                alert.setMessage("Dodaj dowolny tag twojej oferty ");

// Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                final int pos = position;
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        list.set(pos, input.getText().toString());

                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("Usuń", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();


            }
        });
        listView.setAdapter(adapter);
        //listView.setAdapter(adapter2);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void setID(int i) {
        this.id = i;
    }

    public int getID() {
        return this.id;
    }

    public int getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(int i) {
        this.acc_type = i;
    }

    public void EMPEE_OFFERT_MAKE_SKILLS_ADD(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

        alert.setTitle("Dodaj Tag");
        alert.setMessage("Dodaj dowolny tag twojej oferty ");

// Set an EditText view to get user input
        final EditText input = new EditText(v.getContext());

        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                list.add(input.getText().toString());

                adapter.notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();


        // listView.setAdapter(adapter);

        //getLayoutInflater().inflate(findViewById(R.id.EMPEE_OFFERT_MAKE_list_view), null, false);

    }

    public void go_to_make(View v) {

        EditText title_et = (EditText) findViewById(R.id.EMPE_OF_M_nazwa);
        String title = title_et.getText().toString();
        Spinner cat_spinner = (Spinner) findViewById(R.id.EMPE_OF_M_cat);
        int cat_int = cat_spinner.getSelectedItemPosition();
        EditText phone_et = (EditText) findViewById(R.id.EMPE_OF_M_phone);
        String phone = phone_et.getText().toString();
        EditText email_et = (EditText) findViewById(R.id.EMPE_OF_M_email);
        String email =email_et.getText().toString();
        EditText city_et = (EditText) findViewById(R.id.EMPE_OF_M_city);
        String city = city_et.getText().toString();
        EditText postcode_et = (EditText) findViewById(R.id.EMPE_OF_M_postcode);
        String postcode = postcode_et.getText().toString();
        EditText salary_et = (EditText) findViewById(R.id.EMPE_OF_M_salary_am);
        String salary = salary_et.getText().toString();
        EditText desc_et = (EditText) findViewById(R.id.EMPE_OF_M_desc);
        String desc = desc_et.getText().toString();



        boolean puste = false;
        boolean error = false;
        if (title.length() == 0) {
            puste = true;
        }
        if (phone.length() == 0) {
            puste = true;
        }
        if (city.length() == 0) {
            puste = true;
        }
        if (postcode.length() == 0) {
            puste = true;
        }
        if (salary.length() == 0) {
            puste = true;
        }

        if (!puste) {
            // check phone

            if (this.DB.isPhoneRegistered(Integer.parseInt(phone))) {
              // Toast.makeText(this, "Telefon już jest", Toast.LENGTH_LONG).show();
                if(this.DB.CheckPhoneForEmployee(Integer.parseInt(phone), getID())){
                    Toast.makeText(this, "Telefon uzytkownika to jest", Toast.LENGTH_LONG).show();

                }
                else {
                    // oferty sort phone
                    Toast.makeText(this, "Telefon jest już zarejestrowany na innym koncie", Toast.LENGTH_LONG).show();
                    error = true;
                }
            }
            else {
                if (this.DB.isPhoneRegisteredForOffer(Integer.parseInt(phone))) {

                          if(this.DB.CheckPhoneForEmployeeOffert(Integer.parseInt(phone), getID())){


                          }else {

                              error = true;
                          }
                }


            }
            //mail check
        if(this.DB.isEmailRegistered(email)){
            Toast.makeText(this, "Email jest już zarejestrowany.", Toast.LENGTH_LONG).show();
                if(this.DB.CheckEmailForACC(email, getID())){
                    Toast.makeText(this, "Email zgadza się z twoim emailem.", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(this, "Zarejestrowany email nie jest Twój.", Toast.LENGTH_LONG).show();
                    error = true;
                }
            }








            if(error){

            }else{

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(calendar.getTime());
                this.DB.insertEmpeeOffert(title, Integer.parseInt(phone), email, city, Integer.parseInt(postcode),Integer.parseInt(salary) , date, desc, cat_int, getID(), this.list);
                    Intent in = new Intent(this, account.class);
                    in.putExtra("_iduser", getID());
                    in.putExtra("_acctype", getAcc_type());
                    startActivity(in);
            }

        }else {
            Toast.makeText(this, "Żadne pole nie może być puste.", Toast.LENGTH_LONG).show();

        }


    }


}