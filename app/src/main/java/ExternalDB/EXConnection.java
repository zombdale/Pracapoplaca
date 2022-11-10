package ExternalDB;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EXConnection {

//    String classs = "ojdbc8"; // external lib
    String classs = "com.mysql.jdbc.Driver";

    String user = "admintester";        // user - user
    String pass = "1234";               // pass for user - UFvwaqhQGRfdjdM8
    String url ="jdbc:mysql://192.168.159.209:3306/androidppapp";



    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException se) {
            Log.e("Błąd SQL w API", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("Błąd Class w API", e.getMessage());
        } catch (Exception e) {
            Log.e("Bład unkown w API", e.getMessage());
        }
        Log.d("Sukces API", "API OK");
        return conn;
    }



}
