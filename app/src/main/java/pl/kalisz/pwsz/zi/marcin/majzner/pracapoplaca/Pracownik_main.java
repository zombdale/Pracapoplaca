package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ExternalDB.DBEXHelpix;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert.EmployeeOffert;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert.EmployerOffert;


public class Pracownik_main extends AppCompatActivity {



    private Button b_filtr_employer_offert_category;
    private Button[] buttony_bar ;
    private LinearLayout[] boxy;
    private boolean[] buttony_bar_ch;
    private boolean[] filters_on;
    private DBEXHelpix DBE;
    private String Category_filtr; // category
    private String City_filtr; //city
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter RVadapter;
    private boolean filters_bar= false;

    private int _iduser=-1;
    private int acc_type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracownik_main);
     /*   ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_employee_offerts);
        setSupportActionBar(toolbar);

        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.filtr_box_city_auto_complete);
        String[] cities = getResources().getStringArray(R.array.filtr_cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        ACTV.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.filtr_box_category_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter3);

        setButtons();
        setBoxy();
        setBoxyCh();
        setupFil_on();

        this.DBE = new DBEXHelpix();
        Intent intent = getIntent();
        if(intent.hasExtra("_iduser") && intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }
        if(intent.hasExtra("SEARCH")){
            String search_query = intent.getStringExtra("SEARCH");
            //  Toast.makeText(this, "Przeslano kolejke", Toast.LENGTH_SHORT).show();
            TextView TV = (TextView) findViewById(R.id.message_of_offerts);
            TV.setText(getResources().getText(R.string.wassearching) + search_query);

            String[][] dane = DBE.getEmpeeOffertsBySearch(search_query);
            String[][] dane_tags = DBE.getEmpeeOffertsTagsBySearch(search_query);
            String[][] data= new String[0][0];
            if(dane_tags!=null&&dane!=null){
                ArrayList<ArrayList<String>> dat = new ArrayList();
                for(int i=0;i<dane_tags.length;i++){
                    dat.add(new ArrayList());
                    for(int k=0;k<dane_tags[i].length;k++){
                        dat.get(i).add(dane_tags[i][k]);
                        }

                }
                int ii=dat.size();

                for(int i=0;i<dane.length;i++){
                    dat.add(new ArrayList());
                    for(int k=0;k<dane[i].length;k++){
                        dat.get(ii).add(dane[i][k]);

                    }
                    ii++;
                }
                ArrayList<Integer> dat_numbers = new ArrayList();
                ArrayList<ArrayList<String>> dat_uniq = new ArrayList();
                boolean dodano = false;
                for(int i=0;i<dat.size();i++){
                    if(!unique_sort(dat_numbers, Integer.parseInt(dat.get(i).get(0)))){
                        dat_uniq.add(new ArrayList()); dat_numbers.add(Integer.parseInt(dat.get(i).get(0)));
                        dodano = true;
                    }
                    for(int k=0;k<dat.get(i).size();k++){
                        if(dodano){   dat_uniq.get(dat_uniq.size()-1).add(dat.get(i).get(k));}
                    }
                    dodano = false;

                }

                data = new String[dat_uniq.size()][11];
                for(int i=0;i<dat_uniq.size();i++){
                    for(int k=0;k<11;k++){
                        data[i][k]= dat_uniq.get(i).get(k);

                    }

                }


//unique selector !

            }else {
                ArrayList<ArrayList<String>> dat = new ArrayList();
                if(dane_tags!=null){
                    for(int i=0;i<dane_tags.length;i++) {
                        dat.add(new ArrayList());
                    for(int ii=0;ii<dane_tags[i].length;ii++) {
                        dat.get(i).add(dane_tags[i][ii]);
                    }
                    }
                }
                if(dane!=null){
                    int ik = dat.size();
                    for(int i=0;i<dane.length;i++) {
                        dat.add(new ArrayList());
                        for(int ii=0;ii<dane[i].length;ii++) {
                            dat.get(dat.size()-1).add(dane[i][ii]);
                        }

                    }


                }
                ArrayList<Integer> dat_numbers = new ArrayList();
                ArrayList<ArrayList<String>> dat_uniq = new ArrayList();
                boolean dodano = false;
                for(int i=0;i<dat.size();i++){
                    if(!unique_sort(dat_numbers, Integer.parseInt(dat.get(i).get(0)))){
                    dat_uniq.add(new ArrayList()); dat_numbers.add(Integer.parseInt(dat.get(i).get(0)));
                    dodano = true;
                    }
                    for(int k=0;k<dat.get(i).size();k++){
                    if(dodano){   dat_uniq.get(dat_uniq.size()-1).add(dat.get(i).get(k));}
                    }
                    dodano = false;

                }

                data = new String[dat_uniq.size()][11];
                for(int i=0;i<dat_uniq.size();i++){
                    for(int k=0;k<11;k++){
                        data[i][k]= dat_uniq.get(i).get(k);

                    }

                }



            }
            if(data.length>0){
                rv = (RecyclerView) findViewById(R.id.RV_EMPEE);
                rv.setHasFixedSize(true);
                // layoutManager = new LinearLayoutManager(this);
                layoutManager = new GridLayoutManager(this, 1);
                rv.setLayoutManager(layoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                RVadapter = new RVAdapter_empee_offerts(data);
                rv.setAdapter(RVadapter);}
            else {
                TV.setText(getResources().getText(R.string.wassearching) + search_query+ ". Nic nie znaleziono.");

            }

        }else {
            int _count = this.DBE.countEmployeeOfferts();
            Log.d("P.main count off", Integer.toString(_count));
            if (_count != 0) {
                TextView TV = (TextView) findViewById(R.id.message_of_offerts);
                TV.setText(getResources().getText(R.string.how_many_empr_offerts) + Integer.toString(_count));
                String[][] nazwa = this.DBE.getAllEmployeeOffertsWithTags(_count);
                rv = (RecyclerView) findViewById(R.id.RV_EMPEE);
                rv.setHasFixedSize(true);
                // layoutManager = new LinearLayoutManager(this);
                layoutManager = new GridLayoutManager(this, 1);
                rv.setLayoutManager(layoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                RVadapter = new RVAdapter_empee_offerts(nazwa);
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
    private boolean unique_sort(ArrayList<Integer> list, int search){

        for(int i=0;i<list.size();i++){
            if(list.get(i)==search){return true;}

        }

        return false;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getID()!=-1){outState.putInt("_iduser", getID());}
        if(getAcc_type()!=-1){outState.putInt("_acctype", getAcc_type());}

    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id =menuItem.getItemId();
        switch (id) {
          *//*  case R.id.nav_16:
                komunikat = "Lab. 6";
                Toast.makeText(this,komunikat,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Kierunki.class);
                startActivity(intent);
                break;
          *//*
            default : break;
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_employee_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_empee_offerts, menu);
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
                Intent in = new Intent(getBaseContext(), Pracownik_main.class);
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
    public void onBackPressed() {

                Intent in = new Intent(this, MainActivity.class);
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());
                startActivity(in);
            super.onBackPressed();

    }

    public void EMPEE_OFFERT_MINI_GO(View v){

        Log.d("EMPEEOFFERT", "CLICKEVENT TAG:"+v.findViewById(R.id.empee_offert_b).getTag());
        Intent in = new Intent(this, EmployeeOffert.class);
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());
        in.putExtra("_idoffert",  Integer.parseInt(Long.toString((Long)v.findViewById(R.id.empee_offert_b).getTag()))    );
        startActivity(in);

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
    protected void onPause() {
        super.onPause();

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

    public void setButtons(){
        this.buttony_bar = new Button[2];
        buttony_bar[0] = (Button) findViewById(R.id.b_employee_category_f);
        buttony_bar[1] = (Button) findViewById(R.id.b_employee_city_f);

    }
    public boolean czyButtonOn(int Iterator){
        return buttony_bar_ch[Iterator];
    }

    public void setBoxy(){
        this.boxy= new LinearLayout[2];
        this.boxy[0]= (LinearLayout) findViewById(R.id.filtr_box_category);
        this.boxy[1]= (LinearLayout) findViewById(R.id.filtr_box_city);

    }

    public void setupFil_on(){
        filters_on= new boolean[2];
        filters_on[0]=false;filters_on[1]=false;
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
            default : {break;}
        }
    }

    public void setBoxyCh(){
        buttony_bar_ch= new boolean[2];
        buttony_bar_ch[0]=false;
        buttony_bar_ch[1]=false;
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



    public void filtr_employee_category(View w){
        // filtr_box_category.setBackgroundColor("#");

        zaznaczButton(0);
        if(czyBoxOn(0)){zaznaczBox(0, false);}else {zaznaczBox(0, true); }

    }
    public void filtr_employee_city(View w){
        zaznaczButton(1);
        if(czyBoxOn(1)){zaznaczBox(1, false);}else {zaznaczBox(1, true); }
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
        String[][] nazwa = DBE.SelectEMPEEOffertsByFilters(this.filters_on, Category_filtr,City_filtr);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empee_offerts(nazwa);
        rv.setAdapter(RVadapter);
    }


    public void filtr_apply_city(View w){
        AutoCompleteTextView ACTV = (AutoCompleteTextView)findViewById(R.id.filtr_box_city_auto_complete);
        zaznaczButton(1);
        zaznaczBox(1,false);
        fil_on(1);
        this.City_filtr = ACTV.getText().toString();
        String[][] nazwa = DBE.SelectEMPEEOffertsByFilters(this.filters_on, Category_filtr,City_filtr);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empee_offerts(nazwa);
        rv.setAdapter(RVadapter);
    }

    public void filtr_cancel_category(View v){

        fil_off(0); fil_cln(0);
        this.Category_filtr= null;
        zaznaczButton(0);
        zaznaczBox(0,false);
        String[][] nazwa = DBE.SelectEMPEEOffertsByFilters(this.filters_on, Category_filtr,City_filtr);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empee_offerts(nazwa);
        rv.setAdapter(RVadapter);
    }
    public void filtr_cancel_city(View v){
this.City_filtr=null;
        fil_off(1);fil_cln(1);
        zaznaczButton(1);
        zaznaczBox(1,false);
        String[][] nazwa = DBE.SelectEMPEEOffertsByFilters(this.filters_on, Category_filtr,City_filtr);
        TextView TV = (TextView) findViewById(R.id.message_of_offerts);
        TV.setText(getResources().getText(R.string.how_many_empr_offerts)+""+nazwa.length);
        RVadapter = new RVAdapter_empee_offerts(nazwa);
        rv.setAdapter(RVadapter);
    }



} // end class
