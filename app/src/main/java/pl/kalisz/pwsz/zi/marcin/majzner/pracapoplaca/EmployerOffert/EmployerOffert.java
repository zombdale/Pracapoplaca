package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;

import java.util.ArrayList;

import ExternalDB.DBEXHelpix;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.Employer_offert_make;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.account;

public class EmployerOffert extends AppCompatActivity {

    private DBEXHelpix DB;
    private int _idoffert;
    private boolean CRUD= false;
    private ArrayList<TextView> TV_pola;
    private int _iduser;
    private int _acctype;
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        if(in.hasExtra("_idoffert")) {
            Log.d("#EMPROFFERT", "id odebrano"+in.getIntExtra("_idoffert", -1));
            setIdOffert(in.getIntExtra("_idoffert", -1));
            setContentView(R.layout.activity_employer_offert);


            DB = new DBEXHelpix();
            TV_pola=new ArrayList();
            String[] records=DB.getEmployerOffertById(getIdOffert());
Log.d("EMPROFFERT", "0:"+records[0]+"1:"+records[1]+"2:"+records[2]+"3:"+records[3]+"4:"+records[4]+
        "5:"+records[5]+"6:"+records[6]+"7:"+records[7]+"8:"+records[8]+"9:"+records[9]+"10:"+records[10]+"11:"+records[11]+"12:"+records[12]);
if(records!=null) {
    if (Integer.parseInt(records[14]) == 0) {
        //oferta typ - dzielo
        String[] employer_data = DB.getEmployerInfoForEmployerOffertByAccID(Integer.parseInt(records[13]), getIdOffert());
        if (employer_data != null) {
            if (employer_data.length == 7) { // company
                setPola(0);
                fill_pola(1, records, employer_data);
            } else if (employer_data.length == 5) { //company person
                setPola(1);
                fill_pola(2, records, employer_data);
            }
        }
    } else {
        String[] employer_data = DB.getEmployerInfoForEmployerOffertByAccID(Integer.parseInt(records[13]), getIdOffert());

        if (employer_data != null) {
            if (employer_data.length == 7) { // company
                setPola(0);
                fill_pola(3, records, employer_data);
            } else if (employer_data.length == 5) { //company person
                setPola(1);
                fill_pola(4, records, employer_data);
            }
        }

    }
}
            //   name.setText(records[1]);

        }
        else {

        }
        if(in.hasExtra("CRUD")) {
           /* Toast toast = Toast.makeText(this, "jest crud ", Toast.LENGTH_LONG);
            toast.show();*/
            this.CRUD = true;
        }
        if(in.hasExtra("_iduser")&&in.hasExtra("_acctype")) {

            setIdUser(in.getIntExtra("_iduser", -1));
            set_acctype(in.getIntExtra("_acctype", -1));

        }
        if(CRUD){
           /* Button edit = (Button) findViewById(R.id.empr_offert_crud_edit_button);
            edit.setVisibility(View.VISIBLE);*/
            Button delete = (Button) findViewById(R.id.empr_offert_crud_delete_button);
            delete.setVisibility(View.VISIBLE);

        }
        else {
            Button follow = (Button)findViewById(R.id.empr_offert_follow_button);
            if(get_acctype()==0){
                follow.setVisibility(View.VISIBLE);

            }else if(get_acctype()==1){
                follow.setVisibility(View.GONE);
            }
        }


    }
        public int getIdOffert() {
        return this._idoffert;
        }

        public void setIdOffert(int s) {
            this._idoffert =s;
           }

           public int getIdUser(){
        return this._iduser;
           }
           public void setIdUser(int i){
        this._iduser  = i;
           }
    public int get_acctype(){
        return this._acctype;
    }
    public void set_acctype(int i){
        this._acctype  = i;
    }


           private void fill_pola(int i, String[] dta, String[] empr_dta){
        switch(i){
            case 1: { // dzielo
                this.TV_pola.get(0).setText(dta[1]);
                this.TV_pola.get(1).setText(dta[2]);
                this.TV_pola.get(2).setText(dta[4]);
                this.TV_pola.get(3).setText(dta[7]);
                this.TV_pola.get(4).setText(dta[5]);
                this.TV_pola.get(5).setText(dta[6]);

                this.TV_pola.get(6).setText(dta[8]+" PLN");
                this.TV_pola.get(7).setText("N/A");
                this.TV_pola.get(8).setText("O dzieło");
                this.TV_pola.get(9).setText(dta[10]);
                this.TV_pola.get(10).setText(dta[11]);

                this.TV_pola.get(11).setText(empr_dta[0]);
                this.TV_pola.get(12).setText(empr_dta[1]);
                this.TV_pola.get(13).setText(empr_dta[2]);
                this.TV_pola.get(14).setText(empr_dta[3]);
                this.TV_pola.get(15).setText(empr_dta[4]);
                this.TV_pola.get(16).setText(empr_dta[5]);
                this.TV_pola.get(17).setText(empr_dta[6]);

                break;
            }
            case 2: { // dzielo
                this.TV_pola.get(0).setText(dta[1]);
                this.TV_pola.get(1).setText(dta[2]);
                this.TV_pola.get(2).setText(dta[4]);
                this.TV_pola.get(3).setText(dta[7]);
                this.TV_pola.get(4).setText(dta[5]);
                this.TV_pola.get(5).setText(dta[6]);

                this.TV_pola.get(6).setText(dta[8]+" PLN");
                this.TV_pola.get(7).setText("N/A");
                this.TV_pola.get(8).setText("O dzieło");
                this.TV_pola.get(9).setText(dta[10]);
                this.TV_pola.get(10).setText(dta[11]);

                this.TV_pola.get(11).setText(empr_dta[0]);
                this.TV_pola.get(12).setText(empr_dta[1]);
                this.TV_pola.get(13).setText(empr_dta[2]);
                this.TV_pola.get(14).setText(empr_dta[3]);
                this.TV_pola.get(15).setText(empr_dta[4]);

                break;
            }

            case 3 :{

                this.TV_pola.get(0).setText(dta[1]);
                this.TV_pola.get(1).setText(dta[2]);
                this.TV_pola.get(2).setText(dta[4]);
                this.TV_pola.get(3).setText(dta[7]);
                this.TV_pola.get(4).setText(dta[5]);
                this.TV_pola.get(5).setText(dta[6]);

                this.TV_pola.get(6).setText(dta[8]+" PLN/h");
                this.TV_pola.get(7).setText(dta[9]+" h");
                this.TV_pola.get(8).setText("Umowa zlecenie/o pracę");
                this.TV_pola.get(9).setText(dta[10]);
                this.TV_pola.get(10).setText(dta[11]);

                this.TV_pola.get(11).setText(empr_dta[0]);
                this.TV_pola.get(12).setText(empr_dta[1]);
                this.TV_pola.get(13).setText(empr_dta[2]);
                this.TV_pola.get(14).setText(empr_dta[3]);
                this.TV_pola.get(15).setText(empr_dta[4]);
                this.TV_pola.get(16).setText(empr_dta[5]);
                this.TV_pola.get(17).setText(empr_dta[6]);


                break;
            }


            case 4 : {

                this.TV_pola.get(0).setText(dta[1]);
                this.TV_pola.get(1).setText(dta[2]);
                this.TV_pola.get(2).setText(dta[4]);
                this.TV_pola.get(3).setText(dta[7]);
                this.TV_pola.get(4).setText(dta[5]);
                this.TV_pola.get(5).setText(dta[6]);

                this.TV_pola.get(6).setText(dta[8]+" PLN/h");
                this.TV_pola.get(7).setText(dta[9]+" h");
                this.TV_pola.get(8).setText("Umowa zlecenie/o pracę");
                this.TV_pola.get(9).setText(dta[10]);
                this.TV_pola.get(10).setText(dta[11]);


                this.TV_pola.get(11).setText(empr_dta[0]);
                this.TV_pola.get(12).setText(empr_dta[1]);
                this.TV_pola.get(13).setText(empr_dta[2]);
                this.TV_pola.get(14).setText(empr_dta[3]);
                this.TV_pola.get(15).setText(empr_dta[4]);


                break;}



        }






           }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void setPola(int i){
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_title));
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_phone));
        this.TV_pola.add((TextView)findViewById(R.id.employer_city));
        this.TV_pola.add((TextView)findViewById(R.id.employer_postcode));
        this.TV_pola.add((TextView)findViewById(R.id.employer_address));
        this.TV_pola.add((TextView)findViewById(R.id.employer_address_nr));

        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_pay));
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_hours));
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_type));
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_data_from));
        this.TV_pola.add((TextView)findViewById(R.id.employer_offert_desc));

        if(i==0) {
            LinearLayout companytype = (LinearLayout)findViewById(R.id.empr_offert_company_type);
            companytype.setVisibility(View.VISIBLE);
            LinearLayout company_person_type = (LinearLayout)findViewById(R.id.empr_offert_person_company_type);
            company_person_type.setVisibility(View.GONE);

            this.TV_pola.add((TextView) findViewById(R.id.employer_company_name));
            this.TV_pola.add((TextView) findViewById(R.id.employer_company_city));
            this.TV_pola.add((TextView) findViewById(R.id.employer_company_address));
            this.TV_pola.add((TextView) findViewById(R.id.employer_company_address_nr));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_nip));
            this.TV_pola.add((TextView) findViewById(R.id.employer_phone));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_email));
        }
        if(i==1){
            LinearLayout companytype = (LinearLayout)findViewById(R.id.empr_offert_company_type);
            companytype.setVisibility(View.GONE);
            LinearLayout company_person_type = (LinearLayout)findViewById(R.id.empr_offert_person_company_type);
            company_person_type.setVisibility(View.VISIBLE);

            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_company_person_name));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_company_person_surname));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_company_person_city));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_company_person_phone));
            this.TV_pola.add((TextView) findViewById(R.id.employer_offert_company_person_email));

        }

    }


        public void go_call(View view) {
            TextView phone_call = (TextView) findViewById(R.id.employer_phone);
            int phone_call_number = Integer.parseInt(phone_call.getText().toString());
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone_call_number));

                startActivity(intent);


        }
    public void go_call2(View view){
        TextView phone_call =   (TextView)findViewById(R.id.employer_offert_phone);
        int phone_call_number = Integer.parseInt(phone_call.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone_call_number));

        startActivity(intent);

    }

    public void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);

    }
    public void goto_edit_empr_offert(View view){
            Intent in = new Intent(this, Employer_offert_make.class);
            in.putExtra("_idoffert", getIdOffert());
            in.putExtra("_iduser", getIdUser());
            in.putExtra("_acctype", get_acctype());
            in.putExtra("CRUD", true);
            startActivity(in);


    }


    public void goto_delete_empr_offert(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

        alert.setTitle("Czy na pewno usunąć tę ofertę?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                sure_delete_empr_offert();

            }
        });

        alert.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();


    }

        public void goto_follow_offert(View v){


            boolean isit = this.DB.checkFollowedByEMPEEId(getIdUser(), getIdOffert());

            if(isit){
                this.DB.unfollowEMPROffert(getIdUser(), getIdOffert());
                Toast toast = Toast.makeText(this, "Przestano obserwować ofertę.", Toast.LENGTH_LONG);
                toast.show();
            }else {
                this.DB.followEMPROffert(getIdUser(), getIdOffert());
                Toast toast = Toast.makeText(this, "Obserwujesz ofertę.", Toast.LENGTH_LONG);
                toast.show();
            }


        }
    public void sure_delete_empr_offert(){

        this.DB.deleteEmployerOffertById(getIdOffert());
        Intent in = new Intent(this, account.class);
        in.putExtra("_iduser", getIdUser());
        in.putExtra("_acctype", get_acctype());
        startActivity(in);

    }


    public void goto_maps_address(View view){

    }

    public void goto_maps_address_employers(View view){

    }

    public void go_email(View view){

    }
    public void go_email2(View view){

    }


}




