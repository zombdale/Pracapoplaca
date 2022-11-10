package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;

import ExternalDB.DBEXHelpix;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert.EmployeeOffert;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert.EmployerOffert;


public class Pracodawca_main extends AppCompatActivity  {

    private Button b_filtr_employer_offert_category;
    private Button[] buttony_bar ;
    private LinearLayout[] boxy;
    private boolean[] buttony_bar_ch;
    private boolean[] filters_on;
    private Connection DB;
    private DBEXHelpix DBE;
    private String Category_filtr; // category
    private String City_filtr; //city
    private String Date_filtr; // 1 date
    private String Date_from_filtr;
    private String Date_to_filtr;
    private String Ho;
    private String Ha;
    private String Sal;
    private boolean Sal_mx;
    private boolean Sal_;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter RVadapter;
    private boolean filters_bar= false;
    private int _iduser=-1;
    private int acc_type = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracodawca_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_employer_offerts);
        setSupportActionBar(toolbar);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout., COUNTRIES);
//        AutoCompleteTextView textView = (AutoCompleteTextView)
//                findViewById(R.id.countries_list);
//        textView.setAdapter(adapter);


        b_filtr_employer_offert_category = (Button) findViewById(R.id.b_employer_category_f);

        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.filtr_box_city_auto_complete);
        String[] cities = getResources().getStringArray(R.array.filtr_cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        ACTV.setAdapter(adapter);
        setButtons();
        setBoxy();
        setBoxyCh();
        setupFil_on();
        Spinner spinner = (Spinner) findViewById(R.id.filtr_box_hours_set_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.filtr_box_hours_spinner, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        Spinner spinner1 = (Spinner) findViewById(R.id.filtr_box_category_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter3);

        Intent intent = getIntent();
        if(intent.hasExtra("_iduser") && intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));

        }


        this.DBE = new DBEXHelpix();

        if(intent.hasExtra("SEARCH")){
            String search_query = intent.getStringExtra("SEARCH");
          //  Toast.makeText(this, "Przeslano kolejke", Toast.LENGTH_SHORT).show();
            TextView TV = (TextView) findViewById(R.id.message_of_offerts);
            TV.setText(getResources().getText(R.string.wassearching) + search_query);

            String[][] dane = DBE.getEmprOffertsBySearch(search_query);
            if(dane!=null){
            rv = (RecyclerView) findViewById(R.id.RV_EMPRO);
            rv.setHasFixedSize(true);
            // layoutManager = new LinearLayoutManager(this);
            layoutManager = new GridLayoutManager(this, 1);
            rv.setLayoutManager(layoutManager);
            rv.setItemAnimator(new DefaultItemAnimator());
            RVadapter = new RVAdapter_empr_offerts(dane, this);
            rv.setAdapter(RVadapter);}
            else {
                TV.setText(getResources().getText(R.string.wassearching) + search_query+ ". Nic nie znaleziono.");

            }

        }else {
            int _count = this.DBE.countEmployerOfferts();
            Log.d("P.main count off", Integer.toString(_count));
            if (_count != 0) {
                TextView TV = (TextView) findViewById(R.id.message_of_offerts);
                TV.setText(getResources().getText(R.string.how_many_empr_offerts) + Integer.toString(_count));
                String[][] nazwa = this.DBE.getAllEmployerOfferts(_count);
                rv = (RecyclerView) findViewById(R.id.RV_EMPRO);
                rv.setHasFixedSize(true);
                // layoutManager = new LinearLayoutManager(this);
                layoutManager = new GridLayoutManager(this, 1);
                rv.setLayoutManager(layoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                RVadapter = new RVAdapter_empr_offerts(nazwa, this);
                rv.setAdapter(RVadapter);
            } else {
                TextView TV = (TextView) findViewById(R.id.message_of_offerts);
                TV.setText(getResources().getText(R.string.how_many_empr_offerts) + Integer.toString(0));
            }
       /* Toast toast = Toast.makeText(this, "ID: w/e count :"+nazwa.length+" .", Toast.LENGTH_LONG);
        toast.show();*/
        }



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

public void EMPR_OFFERT_MINI_GO(View v){

        Log.d("EMPROFFERT", "CLICKEVENT TAG:"+v.findViewById(R.id.empr_offert_b).getTag());
    Intent in = new Intent(this, EmployerOffert.class);
    in.putExtra("_idoffert",  Integer.parseInt(Long.toString((Long)v.findViewById(R.id.empr_offert_b).getTag()))    );
    in.putExtra("_iduser", getID());
    in.putExtra("_acctype", getAcc_type());
        startActivity(in);

}
    public void setButtons(){
        this.buttony_bar = new Button[5];
        buttony_bar[0] = (Button) findViewById(R.id.b_employer_category_f);
        buttony_bar[1] = (Button) findViewById(R.id.b_employer_city_f);
        buttony_bar[2] = (Button) findViewById(R.id.b_employer_date_f);
        buttony_bar[3] = (Button) findViewById(R.id.b_employer_hours_f);
        buttony_bar[4] = (Button) findViewById(R.id.b_employer_salary_f);
    }
    public void setBoxy(){
        this.boxy= new LinearLayout[5];
        this.boxy[0]= (LinearLayout) findViewById(R.id.filtr_box_category);
        this.boxy[1]= (LinearLayout) findViewById(R.id.filtr_box_city);
        this.boxy[2]= (LinearLayout) findViewById(R.id.filtr_box_date);
        this.boxy[3]=  (LinearLayout) findViewById(R.id.filtr_box_hours);
        this.boxy[4]= (LinearLayout) findViewById(R.id.filtr_box_salary);
    }
    public void setupFil_on(){
        filters_on= new boolean[5];
        filters_on[0]=false;filters_on[1]=false;filters_on[4]=false;
        filters_on[3]=false;filters_on[2]=false;
    }
    public void fil_on(int e){
        this.filters_on[e]=true;
    }
    public void fil_off(int e){
        this.filters_on[e]=false;
    }
    public void fil_cln(int e){
        switch(e){
            case 0 : { Category_filtr=null;  break; }
            case 1 : { City_filtr=null;     break; }
            case 2 : { Date_filtr=null; Date_from_filtr=null; Date_to_filtr=null; break; }
            case 3 : { Ha=null; Ho=null; break; }
            case 4 : { Sal=null; break; }
            default : {break;}
        }
    }

    public void setBoxyCh(){
        buttony_bar_ch= new boolean[5];
        buttony_bar_ch[0]=false;
        buttony_bar_ch[1]=false;buttony_bar_ch[3]=false;
        buttony_bar_ch[2]=false;buttony_bar_ch[4]=false;
    }
    public void zaznaczBox(int Iterator, boolean Freq){
        if(Freq){
        this.boxy[Iterator].setVisibility(View.VISIBLE);
        for(int Iteratori=0;Iteratori<boxy.length;Iteratori++){
            if(Iteratori!=Iterator){
        this.boxy[Iteratori].setVisibility(View.GONE);
            }
        }} else {
            for(int Iteratori=0;Iteratori<boxy.length;Iteratori++) {
                this.boxy[Iteratori].setVisibility(View.GONE);
            } }
    }

    public void odznaczButton(int Iterator){
        for (int Iteratori = 0; Iteratori < buttony_bar.length; Iteratori++) {
            if (Iteratori != Iterator) {
                buttony_bar[Iteratori].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                buttony_bar_ch[Iterator] = false;
            }
        }
    }

    public void zaznaczButton(int Iterator) {

        for (int Iteratori = 0; Iteratori < buttony_bar.length; Iteratori++) {
                if(Iteratori !=Iterator) {
                    buttony_bar[Iteratori].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    buttony_bar_ch[Iteratori] = false;
                }else{
                    if (!buttony_bar_ch[Iteratori]) {
                        buttony_bar[Iteratori].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        buttony_bar_ch[Iteratori] = true;
                }
                else {
                        buttony_bar[Iteratori].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        buttony_bar_ch[Iteratori] = false;
                    }
                }}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_empr_offerts, menu);
        SearchView SV = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        SearchManager SM = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
      //  SM.startSearch();
        SV.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView SV = (SearchView)v.findViewById(R.id.app_bar_search);
            //    Toast.makeText(v.getContext(), SV.getQuery().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        SV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent in = new Intent(getBaseContext(), Pracodawca_main.class);
                in.putExtra("SEARCH", query);
                startActivity(in);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        SV.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

          //      Toast.makeText(getBaseContext(), "zamknieto", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
           /* case R.id.app_bar_search : {
               // Intent intent = new Intent(this, main_settings.class);
               // startActivity(intent);
                return true;}*/
            case R.id.app_bar_filters : {
            HorizontalScrollView filters = (HorizontalScrollView)findViewById(R.id.a1);
            if(!filters_bar){
                filters.setVisibility(View.VISIBLE);filters_bar=true;
            }else {
                filters.setVisibility(View.GONE);filters_bar=false;
            }
return true;
            }
            default : return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getID()!=-1){outState.putInt("_iduser", getID());}
        if(getAcc_type()!=-1){outState.putInt("_acctype", getAcc_type());}

    }

    @Override
    protected void onResume() {

        Intent intent = getIntent();
        if(intent.hasExtra("_iduser")&&intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }
        super.onResume();
        /*try {
        DBE.resum();
        }catch (SQLException e)
        {
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_LONG);
            toast.show();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
            Intent in = new Intent(this, MainActivity.class);
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());

            startActivity(in);

    }

    public LinearLayout[] getBoxy(){
        return this.boxy;
    }

    public Button[] getButtons(){
        return buttony_bar;
    }

    public boolean czyBoxOn(int Iterator){
        if(getBoxy()[Iterator].getVisibility()==View.VISIBLE){
            return true;
        }
        else { return false;}
    }
    public boolean czyButtonOn(int Iterator){
        return buttony_bar_ch[Iterator];
    }

    public void filtr_employer_category(View w){
   // filtr_box_category.setBackgroundColor("#");

        zaznaczButton(0);
        if(czyBoxOn(0)){zaznaczBox(0, false);}else {zaznaczBox(0, true); }

    }
    public void filtr_employer_city(View w){
        zaznaczButton(1);
        if(czyBoxOn(1)){zaznaczBox(1, false);}else {zaznaczBox(1, true); }
    }
    public void filtr_employer_date(View w){
        zaznaczButton(2);
        if(czyBoxOn(2)){zaznaczBox(2, false);}else {zaznaczBox(2, true); }
    }
    public void filtr_employer_hours(View w){
        zaznaczButton(3);
        if(czyBoxOn(3)){zaznaczBox(3, false);}else {zaznaczBox(3, true); }



    }

    public void filtr_employer_salary(View w){
        zaznaczButton(4);
        if(czyBoxOn(4)){zaznaczBox(4, false);}else {zaznaczBox(4, true); }
    }


    public void  filtr_radio_date_fromto(View v){
        LinearLayout dateat= (LinearLayout) findViewById(R.id.filtr_box_date_linbox_at);
        LinearLayout fromto = (LinearLayout) findViewById(R.id.filtr_box_date_linbox_fromto);
        fromto.setVisibility(View.VISIBLE);
        dateat.setVisibility(View.GONE);


    }

    public void  filtr_radio_date_at(View v){
        LinearLayout dateat= (LinearLayout) findViewById(R.id.filtr_box_date_linbox_at);
        LinearLayout fromto = (LinearLayout) findViewById(R.id.filtr_box_date_linbox_fromto);
        fromto.setVisibility(View.GONE);
        dateat.setVisibility(View.VISIBLE);
    }

    public void  filtr_radio_hours_often(View v){
        LinearLayout ha= (LinearLayout) findViewById(R.id.filtr_box_hours_for_ammount);
        LinearLayout ho= (LinearLayout) findViewById(R.id.filtr_box_hours_for_often);
        ha.setVisibility(View.GONE);
        ho.setVisibility(View.VISIBLE);
    }
    public void  filtr_radio_hours_once(View v){
        LinearLayout ha= (LinearLayout) findViewById(R.id.filtr_box_hours_for_ammount);
        LinearLayout ho= (LinearLayout) findViewById(R.id.filtr_box_hours_for_often);
        ho.setVisibility(View.GONE);
        ha.setVisibility(View.VISIBLE);
    }


    // apppppppplyyyy  apppppppplyyyy  apppppppplyyyy  apppppppplyyyy  apppppppplyyyy  apppppppplyyyy

        public void filtrapply(  ){


        }


    public void filtr_apply_category(View w){
        Spinner spinner = (Spinner) findViewById(R.id.filtr_box_category_spinner);
    int SelectedItem = (int)spinner.getSelectedItemId();
            String[] catlist = getResources().getStringArray(R.array.category_list);
Log.d("EMPRFILTERS", "selected cat nr: "+SelectedItem+";;; name:"+catlist[SelectedItem]);
           //createview_for_category(SelectedItem );
        this.Category_filtr = Integer.toString(SelectedItem);
        zaznaczButton(0);
        zaznaczBox(0,false);
        fil_on(0);
        //String[][] nazwa = DBE.getAllEmployerOffertsASC();
        String[][] nazwa = DBE.SelectEMPROffertsByFilters(this.filters_on, Category_filtr,City_filtr,  Ho, Ha);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empr_offerts(nazwa, this);
        rv.setAdapter(RVadapter);
    }


    public void filtr_apply_city(View w){
        AutoCompleteTextView ACTV = (AutoCompleteTextView)findViewById(R.id.filtr_box_city_auto_complete);
        zaznaczButton(1);
        zaznaczBox(1,false);
        fil_on(1);
        this.City_filtr = ACTV.getText().toString();
        String[][] nazwa = DBE.SelectEMPROffertsByFilters(this.filters_on, Category_filtr,City_filtr,  Ho, Ha);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empr_offerts(nazwa, this);
        rv.setAdapter(RVadapter);
    }

    public void filtr_apply_city2(View w){
        AutoCompleteTextView ACTV = (AutoCompleteTextView)findViewById(R.id.filtr_box_city_auto_complete);
        zaznaczButton(1);
        zaznaczBox(1,false);
        fil_on(1);
        String[][] data = DBE.SelectEMPROffertsByCity(ACTV.getText().toString());
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+Integer.toString(data.length));
        RVadapter = new RVAdapter_empr_offerts(data, this);
        rv.setAdapter(RVadapter);

    }

    public void filtr_apply_date(View w){

        zaznaczButton(2);
        zaznaczBox(2,false);
        fil_on(2);
    }
    public void filtr_apply_hours(View w){
        EditText EDTX = (EditText) findViewById(R.id.filtr_box_hours_set);
        boolean of=true;
        if(findViewById(R.id.filtr_box_hours_often).isSelected())
        {of=true;}else { of=false;}
        if(EDTX.getText().toString().length()>0) {
            int HRS = Integer.parseInt(EDTX.getText().toString());
            if (HRS > 0) {
                Spinner spinner = (Spinner) findViewById(R.id.filtr_box_hours_set_spinner);
                boolean MOL = false;
                if (spinner.getSelectedItemId() == 0) {
                    MOL = true;
                } else {
                    MOL = false;
                }
                if (of) {
                    Sal = EDTX.getText().toString();
                    Sal_ = true;
                }
                if (!of) {
                    Sal = EDTX.getText().toString();
                    Sal_ = false;
                }
                fil_on(3);
                zaznaczButton(3);
                zaznaczBox(3, false);
                querybuild();
                createview_for_hours_filter(MOL, HRS);
            } else {
                Toast toast = Toast.makeText(this, R.string.EVH,
                        Toast.LENGTH_LONG);
                toast.show();

            }
        }else {

            Toast toast = Toast.makeText(this, R.string.err_empty_hours_field,
                    Toast.LENGTH_LONG);
            toast.show();
        }

        //Log.d("#", spinner.getAdapter().);
               //if(findViewById(R.id.filtr_box_hours_set_spinner)){}
    }


    public void filtr_apply_salary(View w){
        RadioButton RB1_LESS = (RadioButton) findViewById(R.id.filtr_box_salary_radio_less);
           EditText EDTX = (EditText) findViewById(R.id.filtr_box_salary_set);
        boolean MOL = false;
        if(RB1_LESS.isChecked()){
            MOL= true;}
        else { MOL = false;}
        if(EDTX.getText().length()!=0){


            fil_on(4);
            zaznaczButton(4);
            zaznaczBox(4, false);
            querybuild();
    //createview_for_salary_filter(MOL, Integer.parseInt(EDTX.getText().toString()));

        }
        else {
            Toast toast = Toast.makeText(this, R.string.err_empty_salary_field,
                    Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void filtr_cancel_category(View v){
        this.Category_filtr=null;
        fil_off(0); fil_cln(0);
        zaznaczButton(0);
        zaznaczBox(0,false);
        String[][] nazwa = DBE.SelectEMPROffertsByFilters(this.filters_on, Category_filtr,City_filtr,  Ho, Ha);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empr_offerts(nazwa, this);
        rv.setAdapter(RVadapter);
    }
    public void filtr_cancel_city(View v){
this.City_filtr = null;
        fil_off(1);fil_cln(1);
        zaznaczButton(1);
        zaznaczBox(1,false);
        String[][] nazwa = DBE.SelectEMPROffertsByFilters(this.filters_on, Category_filtr,City_filtr,  Ho, Ha);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empr_offerts(nazwa, this);
        rv.setAdapter(RVadapter);
    }
    public void filtr_cancel_date(View v){

        fil_off(2);fil_cln(2);
        zaznaczButton(2);
        zaznaczBox(2,false);
        querybuild();
    }
    public void filtr_cancel_hours(View v){

        fil_off(3); fil_cln(3);
        zaznaczButton(3);
        zaznaczBox(3,false);
        querybuild();
    }
    public void filtr_cancel_salary(View v){

        fil_off(4); fil_cln(4);
        zaznaczButton(4);
        zaznaczBox(4,false);
        querybuild();
    }


    public void querybuild(){
        String C="";
        for(int l=0;l<filters_on.length;l++){
        if(filters_on[l]){C+=l;}
        }
            if(C.length()==0){

                updateView(1);
            }
}
public void updateView(int C){

        switch(C){
            case 1: {
                break; }
            default: {break;}
        }
}

    public void createview_for_salary_filter(boolean MOL, int SALR){
    }

    public void createview_for_hours_filter(boolean mol, int hours){
        if(mol){
try {
    //  Cursor crs = DBHELPERACOUNT.getAllEmployersByHoursRaise();

}catch (SQLException e){
    Toast toast = Toast.makeText(this, "Baza danych jest niedostępna",
            Toast.LENGTH_LONG);
    toast.show();

}
        }
        else {
          //  Cursor crs = DBHELPERACOUNT.getAllEmployersByHoursLower();

        }



    }

    public void createview_for_category(int CATNR){




    }






    /*@Override
    public void itemClicked(long id){
        View fragmentContainer = findViewById(R.id.employer_offert_fragment);
        if(fragmentContainer!=null){
            EmployerOffertInfoFragment info = new EmployerOffertInfoFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            info.setObiekt(id);
            ft.replace(R.id.employer_offert_fragment, info);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
        else{
            Intent intent = new Intent(this, EmployerOffertInfo.class);
            intent.putExtra(EmployerOffertInfo.EXTRA_EMPLOYEROFFERT_ID, (int) id );
            startActivity(intent);
        }

    }*/




}
