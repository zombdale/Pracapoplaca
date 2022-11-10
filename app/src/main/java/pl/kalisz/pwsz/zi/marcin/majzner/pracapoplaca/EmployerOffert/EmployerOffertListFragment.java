/*
package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ExternalDB.DBEXHelpix;

public class EmployerOffertListFragment extends ListFragment {
    public static interface Listener {

        void itemClicked(long id);
    }


    private Listener listener;

    public EmployerOffertListFragment(){}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    String[] nazwy_ ;


          DBEXHelpix DB = new DBEXHelpix();
        nazwy_= DB.getAllEmployerOfferts();
        if(nazwy_==null){
            Log.e("#ERV", "crv");}
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
               inflater.getContext(), android.R.layout.simple_list_item_1, nazwy_);
       setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener= (Listener) context;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        if (listener != null){
            listener.itemClicked(id);
        }
        super.onListItemClick(l, v, position, id);
    }








}
*/
