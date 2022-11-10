package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ExternalDB.DBEXHelpix;
import ExternalDB.EXConnection;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert.EmployeeOffert;
import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert.EmployerOffert;


public class account extends AppCompatActivity {
    private DatabaseSQLiteHelper DBHELPERACOUNT;
    private Cursor cursor;
    static final String TAG = "ACCOUNT";

    /*LAYOUT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    private TextView accusername;
    private TextView accemail;
    private LinearLayout data_layout_linear;
    private LinearLayout account_linear_box_more ;
    private LinearLayout account_isguest_layout_linear;
    private LinearLayout account_followed_box;
    private Button checkDB ;
    ProgressDialog progressDialog;
    EXConnection connectionClass;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter RVadapter;
    private DBEXHelpix DBE;
    private int id;
    private int acc_type;
    private TextView welcomemessage ;
    /*LAYOUT END >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
       // ActionBar actionBar = getSupportActionBar();            // pobranie action bara
        //actionBar.setDisplayHomeAsUpEnabled(true);              // ustawienie strzalki na action barze [wstecz]

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_basic);
        setSupportActionBar(toolbar);
        welcomemessage = (TextView) findViewById(R.id.account_welcome_message);

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
        if(getID()!=-1){

            //acc cursor
            this.DBE = new DBEXHelpix();
            String[] data = this.DBE.getUserById(getID());
            if(data!=null){
            int acctype= Integer.parseInt(data[3]);
            if(acctype==0) {
                welcomemessage.setText(getResources().getText(R.string.welcome) + " " + data[1]+". "+getResources().getText(R.string.acc_employee)+".");
            }
            if(acctype==1){
                welcomemessage.setText(getResources().getText(R.string.welcome) + " " + data[1]+". "+getResources().getText(R.string.acc_employer)+".");

            }
            }else {
                Intent intent2 = new Intent(this, account_create.class);
                startActivity(intent2);
                    /*Toast toast = Toast.makeText(this, "Nie ma takiego konta, zarejestruj się.", Toast.LENGTH_LONG);
                    toast.show();*/
            }

            data_layout_linear = findViewById(R.id.account_linear_box_data);
            account_linear_box_more = findViewById(R.id.account_linear_box_more);
            account_isguest_layout_linear = findViewById(R.id.account_isguest_linear_box);
            account_followed_box = findViewById(R.id.account_followed_box);
            account_isguest_layout_linear.setVisibility(View.VISIBLE);
            accusername = findViewById(R.id.account_username);
            accemail = findViewById(R.id.account_email);
       //     checkDB = findViewById(R.id.checkDBButton);
            accusername.setText(data[1]);
            accemail.setText(data[2]);
            Toast.makeText(this, getResources().getText(R.string.acc_login_succes), Toast.LENGTH_SHORT).show();
            //setAdapterOfert();
            //load all data ?

        }else {
            TextView T = (TextView) findViewById(R.id.account_welcome_message);
            T.setText(getResources().getText(R.string.not_logged));
            Button b = (Button) findViewById(R.id.login_button);
            b.setVisibility(View.VISIBLE);
            Intent intent3 = new Intent(this, account_login.class);
            startActivity(intent3);
        }

        try{


            //gosc
   /* if(Integer.parseInt(cursor.getString(3))==1){
        TextView welcomemessage = (TextView) findViewById(R.id.account_welcome_message);
        String a = "Nie masz konta, zarejestruj się lub zaloguj, aby uzyskać dostęp do wszystkich możliwości aplikacji.";
        //String b = R.string.guest_acsascount_intro_message.;
        welcomemessage.setText(a.toCharArray(),0,a.length());
        Button bt= (Button)findViewById(R.id.account_create_for_guest_button);
        Button bt1= (Button)findViewById(R.id.account_login_for_guest_button);
        bt.setVisibility(View.VISIBLE);
        bt1.setVisibility(View.VISIBLE);

    }

    if(Integer.parseInt(cursor.getString(3))==2){
        account_isguest_layout_linear.setVisibility(View.VISIBLE);
        //acc cursor
        Cursor cursor2 = DBHELPERACOUNT.getAccbyUsername(cursor.getString(1));
        String acc_username = cursor2.getString(1);
        String acc_email = cursor2.getString(2);
        char[] acc_username_charsq = acc_username.toCharArray();
        char[] acc_email_charsq = acc_email.toCharArray();
        accusername.setText(acc_username_charsq,0,acc_username.length());
        accemail.setText(acc_email_charsq,0,acc_email.length());

    }*/

        }catch (SQLException e)

        {
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_LONG);
            toast.show();

        }


    }


     public void EMPR_OFFERT_CRUD_MINI_GO(View v){

         Log.d("EMPROFFERT", "CLICKEVENT TAG:"+v.findViewById(R.id.empr_offert_b).getTag());
         Intent in = new Intent(this, EmployerOffert.class);
         in.putExtra("_idoffert",  (int)v.findViewById(R.id.empr_offert_b).getTag()    );
         in.putExtra("CRUD", true);
         in.putExtra("_iduser", getID());
         in.putExtra("_acctype", getAcc_type());
         startActivity(in);


     }
    public void EMPEE_OFFERT_CRUD_MINI_GO(View v){

        Log.d("EMPEEOFFERT", "CLICKEVENT TAG:"+v.findViewById(R.id.empee_offert_b).getTag());
        Intent in = new Intent(this, EmployeeOffert.class);
        in.putExtra("_idoffert",  (int)v.findViewById(R.id.empee_offert_b).getTag()    );
        in.putExtra("CRUD", true);
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());
        startActivity(in);


    }

    public void EMPEE_FOLLOWED_OFFERT_MINI_GO(View v){
        Log.d("EMPEEOFFERT", "CLICKEVENT TAG:"+v.findViewById(R.id.f_empr_offert_b).getTag());
        Intent in = new Intent(this, EmployerOffert.class);
        in.putExtra("_idoffert",  (int)v.findViewById(R.id.f_empr_offert_b).getTag()    );
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());
        startActivity(in);

    }

    public void EMPR_FOLLOWED_OFFERT_MINI_GO(View v){
        Intent in = new Intent(this, EmployeeOffert.class);
        in.putExtra("_idoffert",  (int)v.findViewById(R.id.f_empe_offert_b).getTag()    );
        in.putExtra("_iduser", getID());
        in.putExtra("_acctype", getAcc_type());
        startActivity(in);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_basic_back:
                //extras czy zalogowany czy cos ++ dodac
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("_iduser", getID());intent1.putExtra("_acctype", getAcc_type());
                startActivity(intent1);
                return true;
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
    protected void onPause(){
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, MainActivity.class);
        if(getID()!=-1){in.putExtra("_iduser", getID());}
        if(getAcc_type()!=-1){in.putExtra("_acctype", getAcc_type());}
      startActivity(in);
       // super.onBackPressed();
    }


    @Override
    protected void onResume(){

       Intent intent = getIntent();
        if(intent.hasExtra("_iduser")&&intent.hasExtra("_acctype")) {
            setID(intent.getIntExtra("_iduser", -1));
            setAcc_type(intent.getIntExtra("_acctype", -1));
        }
        super.onResume();
    }
    public void setID(int id){
        this.id= id;
    }

    public int getID(){
        return this.id;
    }

    public int getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(int i){
        this.acc_type =i;
    }

    public void goto_edit(View v){


    }

     public void goto_login(View w){
         Intent gotologin= new Intent(this, account_login.class);
        startActivity(gotologin);

     }
    public void goto_logout(View v){
        try {
            Intent gotomain = new Intent(this, MainActivity.class);
            gotomain.putExtra("_iduser", -1);gotomain.putExtra("_acctype", -1);
            startActivity(gotomain);
        }catch (SQLException e)
        {
            Toast toast = Toast.makeText(this, "Baza danych jest niedostępna", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void goto_edit_password(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle(getResources().getText(R.string.account_edit_password_data_button).toString());
       // alert.setMessage(getResources().getText(R.string.edit_password_popup_title).toString());
       // final EditText oldpassword= new EditText(v.getContext());
        final EditText newpassword= new EditText(v.getContext());
        final EditText newpassword2= new EditText(v.getContext());
        LinearLayout ll=new LinearLayout(v.getContext());
        ll.setOrientation(LinearLayout.VERTICAL);

       // oldpassword.setHint("Stare hasło");
        newpassword.setHint("Nowe hasło");
        newpassword2.setHint("Powtórz nowe hasło");
       // oldpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newpassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
       // ll.addView(oldpassword);
        ll.addView(newpassword);
        ll.addView(newpassword2);

        alert.setView(ll);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                try_change_password(newpassword.getText().toString(), newpassword2.getText().toString());

            }
        });
        alert.setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
dialog.cancel();
            }
        });
        alert.show();

        /*final View w=
        alert.setView(w);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            String oldpass = w.findViewById(R.id.old_password);
            }});*/
    }

    public void try_change_password(String newpass, String newpass2){
        if(newpass.length()<5){
            Toast toast = Toast.makeText(this, "Hasło powinno mieć przynajmniej 5 znaków.", Toast.LENGTH_LONG);
            toast.show();
        }else {

            if (!newpass.equals(newpass2)) {
                Toast toast = Toast.makeText(this, "Nowe hasło nie jest takie same", Toast.LENGTH_LONG);
                toast.show();
            } else {
                  boolean changed =   this.DBE.changePasswordforUserId(getID(), newpass);
                    if(!changed){
                        Toast toast = Toast.makeText(this, "" +
                                "Nie udało się zmienić hasła.", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(this, "" +
                                "Hasło zostało zmienione.", Toast.LENGTH_LONG);
                        toast.show();
                    }

            }
        }

    }
    public void goto_radio_follow(View v){
        data_layout_linear.setVisibility(View.GONE);
        account_linear_box_more.setVisibility(View.GONE);
        account_followed_box.setVisibility(View.VISIBLE);

        switch (getAcc_type()){

            case 0 : {
                int _count = this.DBE.countUserFollowedOffertsEMPEWhereId(getID());

                if(_count!=0){
                    rv = (RecyclerView) findViewById(R.id.RV_USRF);
                    // rv.setOnTouchListener(this);
                    rv.setHasFixedSize(true);
                    // layoutManager = new LinearLayoutManager(this);
                    layoutManager = new GridLayoutManager(this, 1);
                    rv.setLayoutManager(layoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    String[][]  nazwa = this.DBE.getSmallEmpeFollowedOffertsByUserId(getID());
                        /*Toast toast = Toast.makeText(this, "ID: w/e count :"+nazwa.length+" .", Toast.LENGTH_LONG);
                        toast.show();*/
                    RVadapter = new RVAdapter_user_offerts_for_employee_fol(nazwa);
                    rv.setAdapter(RVadapter);

                }else{
                    TextView T = (TextView) findViewById(R.id.account_followed_offerts_count);
                    T.setVisibility(View.VISIBLE);
                    T.setText(getResources().getString(R.string.no_followed_offerts));
                }
                break;}
            case 1 : {
                int _count = this.DBE.countUserFollowedOffertsEMPRWhereId(getID());

                if(_count!=0){
                    rv = (RecyclerView) findViewById(R.id.RV_USRF);
                    // rv.setOnTouchListener(this);
                    rv.setHasFixedSize(true);
                    // layoutManager = new LinearLayoutManager(this);
                    layoutManager = new GridLayoutManager(this, 1);
                    rv.setLayoutManager(layoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    String[][]  nazwa = this.DBE.getSmallEmprFollowedOffertsByUserId(getID());
                    RVadapter = new RVAdapter_user_offerts_for_employer_fol(nazwa);
                    rv.setAdapter(RVadapter);
                }else{
                    TextView T = (TextView) findViewById(R.id.account_followed_offerts_count);
                    T.setVisibility(View.VISIBLE);
                    T.setText(getResources().getString(R.string.no_followed_offerts));
                }




                break;}
            default: {break;}
        }
       /* rv = (RecyclerView) findViewById(R.id.RV_USRO);
        // rv.setOnTouchListener(this);
        rv.setHasFixedSize(true);
        // layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        String[][] nazwa = new String[1][1];
        int[] rows = new int[1];*/
       /* switch (this.DBE.getUserAccType(getID())){
            case 0 :{
                //rows = new int[this.DBE.getCountUserEmprFolOfferts(getID())];
                rows=this.DBE.getIDsSmallEmpeOffertsFolByUserEmprID(getID()); // user.empr_id/empe_id
                nazwa = this.DBE.getSmallEmpeOffertsFolForUser(rows);
                Toast toast = Toast.makeText(this, "ID: w/e count :"+nazwa.length+" .", Toast.LENGTH_LONG);
                toast.show();
                RVadapter = new RVAdapter_user_offerts_for_employee_fol(nazwa);
                rv.setAdapter(RVadapter);
                break;
            }
            case 1: {
                nazwa = this.DBE.getSmallFolOffertsForEmprByUserID(getID());
                RVadapter = new RVAdapter_user_offerts_for_employer_fol(nazwa);
                rv.setAdapter(RVadapter);
                Button B = (Button)findViewById(R.id.account_more_button);
                B.setText(R.string.acc_offerts_add);
                B.setVisibility(View.VISIBLE);
                B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(v.getContext(), Employer_offert_make.class);
                        in.putExtra("_iduser", getID());
                        startActivity(in);
                    }
                });
                break;
            }
            default: {
                Toast toast = Toast.makeText(this, getResources().getString(R.string.db_insert_user_error), Toast.LENGTH_LONG);
                break;}
        }*/
    }

    public void goto_radio_data(View v){
        data_layout_linear.setVisibility(View.VISIBLE);
        account_linear_box_more.setVisibility(View.GONE);
        account_followed_box.setVisibility(View.GONE);
        Button B = (Button)findViewById(R.id.account_more_button);
        B.setText(R.string.acc_offerts_add);
        B.setVisibility(View.GONE);

    }
    public void goto_radio_offerts(View v){
        data_layout_linear.setVisibility(View.GONE);
        account_linear_box_more.setVisibility(View.VISIBLE);
        account_followed_box.setVisibility(View.GONE);

        switch (getAcc_type()){

            case 0 : {
                Button B = (Button)findViewById(R.id.account_more_button);
                B.setText(R.string.acc_offerts_add);
                B.setVisibility(View.VISIBLE);
                B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(v.getContext(), Employee_offert_make.class);
                        in.putExtra("_iduser", getID()); in.putExtra("_acctype", getAcc_type());
                        startActivity(in);
                    }
                });
                int _count = this.DBE.countUserOffertsEMPEWhereId(getID());

                if(_count!=0){
                    rv = (RecyclerView) findViewById(R.id.RV_USRO);
                    // rv.setOnTouchListener(this);
                    rv.setHasFixedSize(true);
                    // layoutManager = new LinearLayoutManager(this);
                    layoutManager = new GridLayoutManager(this, 1);
                    rv.setLayoutManager(layoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    String[][]  nazwa = this.DBE.getSmallEmpeOffertsByUserId(getID());
                   /* Toast toast = Toast.makeText(this, "ID: w/e count :"+nazwa.length+" .", Toast.LENGTH_LONG);
                    toast.show();*/
                    RVadapter = new RVAdapter_user_offerts_employee(nazwa);
                    rv.setAdapter(RVadapter);

                }else{
                    TextView T = (TextView) findViewById(R.id.account_offerts_count);
                    T.setText(getResources().getString(R.string.how_many_empr_offerts)+_count);
                }
                break;
            }
            case 1: {
                Button B = (Button)findViewById(R.id.account_more_button);
                B.setText(R.string.acc_offerts_add);
                B.setVisibility(View.VISIBLE);
                B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(v.getContext(), Employer_offert_make.class);
                        in.putExtra("_iduser", getID()); in.putExtra("_acctype", getAcc_type());
                        startActivity(in);
                    }
                });

                int _count = this.DBE.countUserOffertsEMPRWhereId(getID());

                if(_count!=0){
                    rv = (RecyclerView) findViewById(R.id.RV_USRO);
                    // rv.setOnTouchListener(this);
                    rv.setHasFixedSize(true);
                    // layoutManager = new LinearLayoutManager(this);
                    layoutManager = new GridLayoutManager(this, 1);
                    rv.setLayoutManager(layoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    String[][]  nazwa = this.DBE.getSmallEmprOffertsByUserId(getID());
                    RVadapter = new RVAdapter_user_offerts_employer(nazwa);
                    rv.setAdapter(RVadapter);
                }else{
                    TextView T = (TextView) findViewById(R.id.account_offerts_count);
                    T.setText(getResources().getString(R.string.how_many_empr_offerts)+_count);
                  }
                break;
            }
            default: {break;}
        }

            }


    public void goto_checkDB(View w ){

        Intent intent1 = new Intent(this, dbtester_full.class);
        startActivity(intent1);


    }

    public void goto_just_check_db(View w){
 /*       connectionClass = new EXConnection();

        progressDialog=new ProgressDialog(this);
        DoDB dologin=new DoDB();
        dologin.execute();*/

                    String date = "01/12/2020";
                    try{
                        Date dat = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                        java.sql.Date dat1 = new java.sql.Date(dat.getTime());
                        Calendar calendar = Calendar.getInstance();
                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       String date2 = simpleDateFormat.format(calendar.getTime());
                        Toast toast2 = Toast.makeText(getBaseContext(), "Dzisiaj : "+date2, Toast.LENGTH_LONG);
                        toast2.show();
            date2= "2020-1-1";

 boolean insert = this.DBE.insertDate(date2);

            if(insert){
                Toast toast = Toast.makeText(getBaseContext(), "Sukces, dodano date", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(getBaseContext(), "Niepowodzenie, nie dodano daty", Toast.LENGTH_LONG);
                toast.show();
            }
                    }
 catch (ParseException e) {
     Toast toast = Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG);
     toast.show();
 }


    }
    private class DoDB extends AsyncTask<String,String,String> {
        String username = "tester";
        String pass = "1234";
        String table_name = "users" ;
        String whatever ="" ;
        boolean isSuccess=false;
        String z = "";

        //      public DoDB(){
//          super();
//      }
        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    z = "Please check your internet connection";
                } else {
                    String query=" select * from "+table_name+" where usname='"+username+"' and pass= '"+pass+"'";
                    Statement stmt = con.createStatement();
                    // stmt.executeUpdate(query);

                    ResultSet rs=stmt.executeQuery(query);
                    while (rs.next())
                    {
                        z+=rs.getString(1); z+=" . . .";
                        z+=rs.getString(2);z+=" . . .";
                        z+=rs.getInt(3);z+=" . . .";
                        whatever = Integer.toBinaryString(rs.getInt(3));
                        isSuccess=true;
                        //  z = "Login successfull";

                        if(rs.getString(1).equals(username)){

                            isSuccess=true;
                            con.close();
                            //  z = "Login successfull";
                            break;
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
            return z;        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),z ,Toast.LENGTH_LONG).show();
            if(isSuccess) {
                Toast toast = Toast.makeText(getBaseContext(), "Baza danych jest dostępna", Toast.LENGTH_LONG);
                toast.show();
            }
            progressDialog.hide();
        }
    }
/*

    public void goto_record_one(View w ){

        connectionClass = new EXConnection();

        progressDialog=new ProgressDialog(this);
    String z =" ";
            String table_name = "users" ;
            String username = "tester";
            String pass= "1234";
            String whatever ="";
            boolean isSuccess =false;
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query=" select * from "+table_name+" where usname='"+username+"' and pass= '"+pass+"'";
  Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next())
                {
                    z+=rs.getString(1); z+=" . . .";
                    z+=rs.getString(2);z+=" . . .";
                    z+=rs.getInt(3);z+=" . . .";
                    whatever = Integer.toBinaryString(rs.getInt(3));isSuccess=true;
                    //  z = "Login successfull";

                    if(rs.getString(1).equals(username)){

                        isSuccess=true;
                        //  z = "Login successfull";
                        break;
                    }
                    else{
                        isSuccess=false;
                        break;
                    }}}
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions"+ex;
        }
        DoDB dologin=new DoDB();
        dologin.execute();


    }

*/





} //    end
