package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ExternalDB.DBEXHelpix;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.account;

public class EmployeeOffert extends AppCompatActivity {

    private DBEXHelpix DB;
    private boolean CRUD= false;
    private ArrayList<TextView> TV_pola;
    private int _idoffert;
    private int _iduser;
    private int _acctype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        if(in.hasExtra("_idoffert")) {
            Log.d("#EO1", "id odebrano"+in.getIntExtra("_idoffert", -1));
            setIdOffert(in.getIntExtra("_idoffert", -1));
            setContentView(R.layout.activity_employee_offert);
            TextView title = (TextView) findViewById(R.id.employee_offert_title);
            TextView tags = (TextView) findViewById(R.id.employee_offert_tags);
            this.DB = new DBEXHelpix();
            String[] tagi = this.DB.getTagsByEmpeeOffertId(getIdOffert());
            String tagii ="";
            if(tagi!=null){
            for(int i=0;i<tagi.length;i++){
                tagii+=tagi[i];
                tagii+=" ; ";
            }
            tags.setText(tagii);}
            else {
                tags.setText("Brak tagów");}
            TV_pola=new ArrayList();
            String[] records = DB.getEmployeeOffertById(getIdOffert());
            setPola();
            fillPola(records);
          TextView person_name =  (TextView)findViewById(R.id.employee_offert_person_name);
            TextView peson_surname =(TextView)findViewById(R.id.employee_offert_person_surname);
                    String[] person_data = DB.getEmployeeByAccID(Integer.parseInt(records[10]));
            //title.setText(DB.getEmployeeOffertById(getIdOffert())[1]);
            person_name.setText(person_data[1]);
            peson_surname.setText(person_data[2]);
        }
        else {


        }
        if(in.hasExtra("CRUD")) {

            this.CRUD = true;
        }
        if(in.hasExtra("_iduser")&&in.hasExtra("_acctype")) {
            setIdUser(in.getIntExtra("_iduser", -1));
            set_acctype(in.getIntExtra("_acctype", -1));
        }
        if(CRUD){
            /*Button edit = (Button) findViewById(R.id.empee_offert_crud_edit_button);
            edit.setVisibility(View.VISIBLE);*/
            Button delete = (Button) findViewById(R.id.empee_offert_crud_delete_button);
            delete.setVisibility(View.VISIBLE);

        }else {

            Button follow = (Button)findViewById(R.id.empee_offert_follow_button);
            if(get_acctype()==0){
                follow.setVisibility(View.GONE);

            }else if(get_acctype()==1){
                follow.setVisibility(View.VISIBLE);
            }

        }


    }

    public void setPola(){
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_title));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_pay));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_city));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_postcode));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_phone));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_email));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_date));
        this.TV_pola.add((TextView)findViewById(R.id.employee_offert_desc));
    }

    public void fillPola(String[] data){
        this.TV_pola.get(0).setText(data[1]);
        this.TV_pola.get(1).setText(data[6]);
        this.TV_pola.get(2).setText(data[4]);
        this.TV_pola.get(3).setText(data[5]);
        this.TV_pola.get(4).setText(data[2]);
        this.TV_pola.get(5).setText(data[3]);
        this.TV_pola.get(6).setText(data[7]);
        this.TV_pola.get(7).setText(data[8]);


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


    public void go_call(View v){

        TextView phone_call =   (TextView)findViewById(R.id.employee_offert_phone);
        int phone_call_number = Integer.parseInt(phone_call.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone_call_number));

        startActivity(intent);
    }
    public void goto_follow_offert(View v){

      boolean isit = this.DB.checkFollowedByEMPRId(getIdUser(), getIdOffert());
                if(isit){
                    this.DB.unfollowEMPEEOffert(getIdUser(), getIdOffert());
                    Toast toast = Toast.makeText(this, "Przestano obserwować ofertę.", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    this.DB.followEMPEEOffert(getIdUser(), getIdOffert());
                    Toast toast = Toast.makeText(this, "Obserwujesz ofertę.", Toast.LENGTH_LONG);
                    toast.show();
                }

    }

    public void go_email(View v){

    }

    public void goto_edit_empe_offert(View v){

    }

    public void goto_delete_empe_offert(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

        alert.setTitle("Czy na pewno usunąć tę propozycję?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            sure_delete_empe_offert();

            }
        });

        alert.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();




    }

    public void sure_delete_empe_offert(){
        this.DB.deleteEmployeeOffertById(getIdOffert());
        Intent in = new Intent(this, account.class);
        in.putExtra("_iduser", getIdUser());
        in.putExtra("_acctype", get_acctype());
        startActivity(in);


    }





}







