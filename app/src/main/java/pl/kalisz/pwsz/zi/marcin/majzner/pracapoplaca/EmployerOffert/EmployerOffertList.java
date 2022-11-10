/*
package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployerOffertList extends AppCompatActivity implements EmployerOffertListFragment.Listener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracodawca_main);


        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
         actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]
    }

    */
/*@Override
    public void itemClicked(long id){
        Intent intent = new Intent(this, ObiektyInfo.class);
        intent.putExtra(ObiektyInfo.EXTRA_OBIEKT_ID, (int) id);
        startActivity(intent);

    }*//*

    @Override
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
            Log.e("#EOL" , "int");
           */
/* Intent intent = new Intent(this, EmployerOffertInfo.class);
            intent.putExtra(EmployerOffertInfo.EXTRA_EMPLOYEROFFERT_ID, (int) id );
            startActivity(intent);*//*

        }

    }


}
*/
