/*
package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;

public class EmployerOffertInfo extends AppCompatActivity {
    public static final String EXTRA_EMPLOYEROFFERT_ID = "_id";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_offert_info);

        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]

//        EmployerOffertInfoFragment frag =(EmployerOffertInfoFragment)
//        getSupportFragmentManager().findFragmentById(R.id.employer_offert_fragment);

        int employeroffert_id = (int) getIntent().getExtras().get(EXTRA_EMPLOYEROFFERT_ID);
            //frag.set

    }



}
*/
