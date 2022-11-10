package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ExternalDB.EXConnection;

public class dbtester_full extends AppCompatActivity {

    private EditText db_name, db_pass, db_whatever, db_id;
        //private Button db_check_login, db_insert, db_update, dp_delete;

    ProgressDialog progressDialog;
    EXConnection connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtester_full);
        ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]

        db_name = (EditText) findViewById(R.id.db_name_field);
        db_pass= (EditText) findViewById(R.id.db_pass_field);
        db_whatever= (EditText) findViewById(R.id.db_whatever_field);
        db_id= (EditText) findViewById(R.id.db_id);

        connectionClass = new EXConnection();


    }


    public void goto_checkDB(View w ){
        // progressDialog=new ProgressDialog(this);
    }

    public void DB_check_counter(View w ){
        try{
          //  String result =Select_whateverFrom_users();

            String result= Integer.toString(getAllEmployerOfferts().length);

            if(result !=null) {
                Toast toast = Toast.makeText(this, "ID: w/e count :"+result+" .", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(this, "Problem ze znalezieniem rekordu.", Toast.LENGTH_LONG);
                toast.show();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Problem", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void DB_check_login(View w){
        try{
          String[] dane =selectUserByNameAndPassword(db_name.getText().toString(), db_pass.getText().toString());
            if(dane!=null) {
                Toast toast = Toast.makeText(this, "ID:"+dane[0]+" w/e :"+dane[3]+" .", Toast.LENGTH_LONG);
                toast.show();

            }
            else {
                Toast toast = Toast.makeText(this, "Problem ze znalezieniem rekordu.", Toast.LENGTH_LONG);
                toast.show();

            }
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Problem", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void DB_insert(View w){
try {
    if(insertUser(db_name.getText().toString(), db_pass.getText().toString(), db_whatever.getText().toString())) {
        Toast toast = Toast.makeText(this, "Dodano rekord", Toast.LENGTH_LONG);
        toast.show();
    }}
catch (Exception ex){
    Toast toast = Toast.makeText(this, "Problem", Toast.LENGTH_LONG);
    toast.show();
}
    }




    public void DB_update(View w){

    }


    public void DB_Delete(View w){

    try {
        if(deleteUser(db_id.getText().toString())){
            Toast toast = Toast.makeText(this, "Usunięto rekord.", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "Problem", Toast.LENGTH_LONG);
            toast.show();

        }


    }catch (Exception e){
        Toast toast = Toast.makeText(this, "Problem", Toast.LENGTH_LONG);
        toast.show();
    }


    }

    public boolean insertUser(String name, String pass, String whatever){
        String table_name = "users";
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                return false;
            } else {
                String query = " insert into " + table_name +" (usname, pass, whatever) VALUES ('"+name+"', '"+pass+"','"+whatever+"');";

                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);

                 int rs = stmt.executeUpdate(query);
                if (rs>=1) {


                    return true;


                }
                else{ return false;}
            }
        }
        catch (Exception ex)
        {

            return false;
        } }

    public String[] getAllEmployerOfferts(){
        boolean isSuccess= false;
        String z ="";
        String table_name = "employer_offerts";

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query="select * from "+table_name+";";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);
                ArrayList<String> e= new ArrayList();
                while (rs.next())
                {
                    isSuccess=true;
                    //  z = "Login successfull";
                    e.add(rs.getString(1));

                }

                return ArrayTransfer(e);
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions"+ex;
        }

        return null;
    }
    public boolean deleteUser(String id ){
      //  int _id = Integer.parseInt(id);

         String table_name = "users";

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                return false;
            } else {
                String query = " delete from " + table_name + " where _id='" + id + "'";

                Statement stmt = con.createStatement();
                PreparedStatement smts = con.prepareStatement(query);

                // stmt.executeUpdate(query);

                int rs = smts.executeUpdate(query);
                if (rs>=1) {
                        return true;
                }
                else{ return false;}
            }
        }
        catch (Exception ex)
        {

            return false;
        }   }

    public String[] selectUserByNameAndPassword(String name, String pass){
boolean isSuccess= false;
String z ="";
String table_name = "users";
String whatever = "";
String data[] = new String[4];
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query="select * from "+table_name+" where usname='"+name+"' and pass= '"+pass+"';";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next())
                {
                    isSuccess=true;
                    //  z = "Login successfull";
                    if(rs.getString(2).equals(name)){
                        data[0] = Integer.toString(rs.getInt(1));
                        data[1] = rs.getString(2);
                        data[2] = rs.getString(3);
                        data[3] = Integer.toString(rs.getInt(4));
                        isSuccess=true;
                        //  z = "Login successfull";
                        return data;
                    }
                    else{
                        isSuccess=false;
                        break;
                    } }  }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions"+ex;
        }
        if(data[0]!=null){
            return data;
        }
       return null;
    }


    public String Select_whateverFrom_users() {
        try {
            Connection con= connectionClass.CONN();
            if(con==null){throw new SQLException();}
            else{String queryl="SELECT COUNT(whatever) FROM users;";
                Statement stmtl = con.createStatement();
                //con.prepareStatement()
                ResultSet rsl=stmtl.executeQuery(queryl);
                ArrayList<String> whatever = new ArrayList();
                //int na string !
                //con.commit();
                String e= "";
                if(rsl.first()) {
                     e = rsl.getString(1);
                }
            return e;
                }

//
//                String query="SELECT whatever FROM users;";
//                Statement stmt = con.createStatement();
//                ResultSet rs=stmt.executeQuery(query);
//
//                while(rs.next()){
//                    whatever.add(rs.getString(3));}
//


        }catch(SQLException e){
            Log.e("ERROR1",e.toString());
            return null;}
    }



    public String[] ArrayTransfer(ArrayList<String> Queed){
        String[] temp = new String[Queed.size()];
        for(int ITERATOR=0;ITERATOR<temp.length;ITERATOR++){
            temp[ITERATOR]= Queed.get(ITERATOR);
        }
        return temp;
    }
} // end class

