package ExternalDB;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import android.view.View;

import androidx.annotation.Nullable;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;

public class DBEXHelpix {

    private Connection con;


    public DBEXHelpix() {

        con = new EXConnection().CONN();

    }

    public static Connection getC() {
        return new EXConnection().CONN();

    }

    public void cl() {
        try {
            con.close();
        } catch (Exception e) {
            Log.v("#DBEX", "s");
        }

    }

    public void resum() {
        con = new EXConnection().CONN();

    }

    public int getUserIdByUsnameAndPass(String name, String pass) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {

                return -1;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE usname=? AND password=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, name);
                smts.setString(2, pass);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return rsl.getInt("_id");

                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {

            Log.e("Błąd DBEX 1", "SQL :  " + e.toString());

        }


        return -1;
    }

    public int getUserIdByEmailAndPass(String name, String pass) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return -1;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE email=? AND password=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, name);
                smts.setString(2, pass);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return rsl.getInt("_id");

                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {

            Log.e("Błąd DBEX 2", "SQL :  " + e.toString());

        }
        return -1;
    }
    /*
     *
     *
     */


    public boolean CheckPassForUsername(String name, String pass) throws SQLException {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE usname=? AND password=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, name);
                smts.setString(2, pass);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 3", "SQL :  " + e.toString());
            throw new SQLException(e.toString());


        }
    }

    public boolean CheckPassForEmail(String email, String pass) throws SQLException {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE email=? AND password=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, email);
                smts.setString(2, pass);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 4", "SQL :  " + e.toString());
            throw new SQLException(e.toString());


        }
    }

    public boolean CheckUserByUsname(String name) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE usname=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, name);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 5", "SQL :  " + e.toString());
            // throw new SQLException(e.toString());


        }
        return true;
    }

    /*public int CountEmpeeUserOffertsByUserID(int id) {

        String table_name = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return 1;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE =?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, id);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return 0;
                } else {
                    return 1;
                }
            }

        } catch (SQLException e) {
            Log.e("#DBHLP1", "CheckUserByUsname " + e.toString());
            // throw new SQLException(e.toString());


        }

        return 0;
    }*/

    public boolean CheckUserByEmail(String mail) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE email=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, mail);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 252", "SQL :  " + e.toString());
            // throw new SQLException(e.toString());


        }
        return true;
    }


    public boolean isPhoneRegistered(int n) {
        String table_name = "employer_acc";
        String table_name2 = "employee_acc";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE phone=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, n);
                ResultSet rsl = smts.executeQuery();
                boolean A = true;
                boolean B = true;
                if (rsl.first()) {
                    A = true;
                } else {
                    A = false;
                }
                Q = "SELECT _id FROM " + table_name2 + " WHERE phone=?";
                smts = con.prepareStatement(Q);
                smts.setInt(1, n);
                rsl = smts.executeQuery();
                if (rsl.first()) {
                    B = true;
                } else {
                    B = false;
                }
                if (A || B) {
                    return true;
                }
                if (!A && !B) {
                    return false;
                }

            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 300", "SQL :  " + e.toString());


        }
        return false;
    }

    public boolean isEmailRegistered(String mail) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT acc_id FROM " + table_name + " WHERE email=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setString(1, mail);
                ResultSet rsl = smts.executeQuery();

                if (rsl.first()) {
                return true;
                } else {
                    return false;
                }

            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 329", "SQL :  " + e.toString());

        }
        return false;
    }

    public boolean isPhoneRegisteredForOffer(int n) {
        String table_name = "employer_offert";
        String table_name2 = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT _id FROM " + table_name + " WHERE phone=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, n);
                ResultSet rsl = smts.executeQuery();
                boolean A = true;
                boolean B = true;
                if (rsl.first()) {
                    A = true;
                } else {
                    A = false;
                }
                Q = "SELECT _id FROM " + table_name2 + " WHERE phone=?";
                smts = con.prepareStatement(Q);
                smts.setInt(1, n);
                rsl = smts.executeQuery();
                if (rsl.first()) {
                    B = true;
                } else {
                    B = false;
                }
                if (A || B) {
                    return true;
                }
                if (!A && !B) {
                    return false;
                }

            }

        } catch (SQLException e) {
            Log.e("Błąd DBEX 347", "SQL :  " + e.toString());


        }
        return false;
    }

    public boolean CheckPhoneForEmployee(int phone, int accid){
        String table_name = "employee_acc";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT phone FROM " + table_name + " WHERE acc_id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, accid);
                ResultSet rsl = smts.executeQuery();
                Log.d("PHONECHECK", "QUESY:" +smts.toString());
                if (rsl.first()) {
                    int phonein = rsl.getInt(1);
                    Log.d("PHONECHECK", "w b: "+phonein+" podany:"+phone);
                    if(phone==phonein){
                        return true;
                    } else {
                        return false;
                    }
                }

            }
        }
            catch (SQLException e) {
                Log.e("Błąd DBEX 380", "SQL :  " + e.toString());

            }
        return false;
    }

    public boolean CheckEmailForACC(String mail, int accid){
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT email FROM " + table_name + " WHERE _id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, accid);
                ResultSet rsl = smts.executeQuery();
                Log.d("MAILCHECK", "QUESY:" +smts.toString());
                if (rsl.first()) {
                    String maildb = rsl.getString(1);
                    Log.d("MAILCHECK", "w b: "+maildb+" podany:"+mail);
                    if(mail.equalsIgnoreCase(maildb)){
                        return true;
                    } else {
                        return false;
                    }
                }

            }
        }
        catch (SQLException e) {
            Log.e("Błąd DBEX 380", "SQL :  " + e.toString());

        }
        return false;
    }

    // NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM
            public int getAccId(int userid) {
                String table_name = "employee_acc";
// NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM
                try {
                    con = getC();
                    if (con == null) {
                        return -1;
// NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM NVM
                    } else {
                        String Q = "SELECT acc_type FROM " + table_name + " WHERE acc_id=?";
                        PreparedStatement smts = con.prepareStatement(Q);
                        smts.setInt(1, userid);
                        ResultSet rsl = smts.executeQuery();
                        Log.d("MAILCHECK", "QUESY:" + smts.toString());
                        if (rsl.first()) {
                            String maildb = rsl.getString(1);
                        } else {
                            return 0;
                        }
                    }
                } catch (SQLException e) {
                    Log.e("Błąd DBEX 380", "SQL :  " + e.toString());

                }
                return -1;
            }


            public String[] getEmployeeByAccID(int id){
String table_name = "employee_acc";
                try {
                    con = getC();
                    if (con == null) {
                        return null;
                    } else {
                    String q = "SELECT * FROM "+table_name+" WHERE acc_id=?";
                        PreparedStatement smts = con.prepareStatement(q);
                        smts.setInt(1, id);
                        ResultSet rsl = smts.executeQuery();
                        String data[] = new String[6];
                        if(rsl.first()){
                            data[0] = Integer.toString(rsl.getInt("_id"));
                            data[1] = rsl.getString("name");
                            data[2] = rsl.getString("surname");
                            data[3] = Integer.toString(rsl.getInt("phone"));
                            data[4] = rsl.getString("city");
                            data[5] = Integer.toString(rsl.getInt("acc_id"));



                        }else {
                            return null;
                        }
                    return data;
                    }
                }
                    catch (SQLException e) {
                        Log.e("Błąd DBEX 486", "SQL :  " + e.toString());

                    }



        return null;
            }

    public boolean CheckPhoneForEmployeeOffert(int phone, int accid){
        String table_name = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String Q = "SELECT phone FROM " + table_name + " WHERE acc_id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, accid);
                ResultSet rsl = smts.executeQuery();
                Log.d("PHONECHECK", "QUESY:" +smts.toString());
                if (rsl.first()) {
                    int phonein = rsl.getInt(1);
                    Log.d("PHONECHECK", "w b: "+phonein+" podany:"+phone);
                    if(phone==phonein){
                        return true;
                    } else {
                        return false;
                    }
                }

            }
        }
        catch (SQLException e) {
            Log.e("Błąd DBEX 411", "SQL :  " + e.toString());

        }
        return false;
    }
    public boolean insertDate(String data){
        String table_name = "date";
        try {
            con = getC();
            if (con == null) {

                return false;

            } else {

String q ="INSERT INTO "+table_name+" (date) VALUES (?)";
                PreparedStatement stmtl = con.prepareStatement(q);
                stmtl.setString(1,data);
                int rsl = stmtl.executeUpdate();
                if (rsl >= 0) {
                    return true;

                } else {
                    return false;
                }
            }
        }catch (SQLException e) {

            Log.e("Błąd DBEX 556", "SQL :  " + e.toString());

        }

return false;
    }

    public String[] getUserById(int id) {
        String table_name = "users";
        String[] data = new String[4];
        try {
            con = getC();
            if (con == null) {

                return null;

            } else {
                String Q = "SELECT * FROM " + table_name + " WHERE _id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    data[0] = Integer.toString(rsl.getInt("_id"));
                    data[1] = rsl.getString("usname");
                    data[2] = rsl.getString("email");
                    data[3] = Integer.toString(rsl.getInt("acc_type"));

                } else {
                    return null;
                }
                return data;
            }

        } catch (SQLException e) {

            Log.e("Błąd DBEX 8", "SQL :  " + e.toString());

        }


        return null;
    }


    public int getUserAccType(int id) {
        String table_name = "users";
        try {
            con = getC();
            if (con == null) {
                return -1;

            } else {
                String Q = "SELECT acc_type FROM " + table_name + " WHERE _id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    return rsl.getInt("acc_type");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {

            Log.e("Błąd DBEX 9", "SQL :  " + e.toString());

        }
        return -1;
    }


    public String[][] getAllEmployerOfferts(int counted) {
        boolean isSuccess = false;
        String z = "";
        String table_name = "employer_offert";

        try {
            //  con = new EXConnection().CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query = "SELECT * FROM " + table_name + " ORDER BY date DESC";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs = stmt.executeQuery(query);
                String[][] e = new String[counted][17];
                int i = 0;
                while (rs.next()) {
                    isSuccess = true;
                    //  z = "Login successfull";
                    e[i][0] = (Integer.toString(rs.getInt("_id")));
                    e[i][1] = (rs.getString("title"));
                    e[i][2] = Integer.toString(rs.getInt("phone"));
                    e[i][3] = (rs.getString("city"));
                    e[i][4] = (rs.getString("address"));
                    e[i][5] = Integer.toString(rs.getInt("address_nr"));
                    e[i][6] = Integer.toString(rs.getInt("postcode"));
                    e[i][7] = Integer.toString(rs.getInt("type"));
                    switch (rs.getInt("type")) {
                        case 0: {
                            e[i][8] = "Umowa o dzieło";
                            e[i][9] = Integer.toString(rs.getInt("salary_o"));
                            e[i][10] = "";
                            e[i][11] = "Za wykonanie";
                            break;
                        }
                        case 1: {
                            e[i][8] = "Umowa o pracę/zlecenie";
                            e[i][9] = Integer.toString(rs.getInt("salary_a"));
                            e[i][10] = Integer.toString(rs.getInt("hours"));
                            e[i][11] = "Za godzinę*";
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    e[i][12] = "";
                    e[i][13] = "";

                    /*e[i][9] = rs.getDate("date").toString();
                    e[i][10] = rs.getDate("date_from").toString();
                    e[i][11] = rs.getDate("date_to").toString();*/
                    e[i][14] = rs.getString("info");
                    e[i][15] = Integer.toString(getCategory(rs.getInt("cat_id")));
                    e[i][16] = Integer.toString(rs.getInt("acc_id"));
                    i++;

                }
                return e;
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = "Exceptions" + ex;
            Log.e("Błąd DBEX 9", "?? :  " + ex.toString());
        }
        return null;
    }

    public String[][] getSmallEmprOffertsByUserId(int i) {
        String table_name = "employer_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT * FROM " + table_name + " WHERE acc_id=?";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setInt(1, i);
                ResultSet rs = stmt.executeQuery();

                        //CountEmployerOffertsById
                String[][] data = new String[countUserOffertsEMPRWhereId(i)][3];
                int k = 0;
                while (rs.next()) {
                    data[k][0] = Integer.toString(rs.getInt("_id"));
                    data[k][1] = rs.getString("title");
                    data[k][2] = rs.getString("info");
                    k++;
                }
                return data;
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 10", "SQL :  " + e.toString());
            return null;
        }

    }

    public String[][] getSmallFolOffertsForEmpeByUserID(int i) {
        String tablename = "f_employee_offerts";
        String q1 = "SELECT empee_id FROM " + tablename + " WHERE user_id=?";
        try {
            con = getC();
            if (con == null) {
                Log.d("#DBHP22", "con null");
                return null;
            }
            PreparedStatement stmt = con.prepareStatement(q1);
            stmt.setInt(1, i);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Integer> ar = new ArrayList();
            while (rs.next()) {

                ar.add(rs.getInt("empee_id"));
            }
            if (ar.size() != 0) {
                IArrayTransfer(ar);
            } else return null;
            String[][] T = new String[ar.size()][3];
            tablename = "employee_offert";
            for (int k = 0; k < ar.size(); k++) {
                q1 = "SELECT * FROM " + tablename + " WHERE _id=?";
                stmt = con.prepareStatement(q1);
                stmt.setInt(1, ar.get(k));
                rs = stmt.executeQuery();
                if (rs.first()) {
                    T[k][0] = Integer.toString(rs.getInt("_id"));
                    T[k][1] = rs.getString("title");
                    T[k][2] = rs.getString("title");
                }

            }
            if (T[0][0] != null) {
                return T;
            } else return null;

        } catch (SQLException e) {
            Log.d("#DBEX13", e.toString());
            return null;
        }

    }


    public String[][] getSmallEmpeOffertsByUserId(int i) {
        String table_name = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT * FROM " + table_name + " WHERE acc_id=?";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setInt(1, i);
                ResultSet rs = stmt.executeQuery();
                 //CountEmployerOffertsById
                String[][] data = new String[countUserOffertsEMPEWhereId(i)][3];
                int k = 0;
                while (rs.next()) {
                    data[k][0] = Integer.toString(rs.getInt("_id"));
                    data[k][1] = rs.getString("title");
                    data[k][2] = Integer.toString(rs.getInt("cat_id"));
                    k++;
                }
                return data;
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 11", "SQL :  " + e.toString());
            return null;
        }

    }

    public int[] getIDsSmallEmpeOffertsFolByUserEmprID(int i) {
        String table_name = "f_employer_offerts";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT empe_id FROM " + table_name + " WHERE user_id=?";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setInt(1, i);
                ResultSet rs = stmt.executeQuery();
                ArrayList<Integer> ar = new ArrayList();
                while (rs.next()) {
                    ar.add(rs.getInt("empe_id"));
                }
                if (ar.size() != 0) {
                    return IArrayTransfer(ar);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 12", "SQL :  " + e.toString());

        }
        return null;
    }

    public String[][] getSmallEmpeOffertsFolForUser(int[] e) {


        return null;
    }

    public String[][] getSmallFolOffertsForEmprByUserID(int id) {


        return null;
    }

    public int CountEmployerOffertsById(int i) {
        int k = 0;
        String tablename = "employer_offert";
        try {
            con = getC();
            if (con == null) {
                throw new SQLException();
            } else {
                String queryl = "SELECT COUNT(_id) FROM " + tablename + " WHERE acc_id=?;";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                //con.prepareStatement()
                stmtl.setInt(1, i);
                ResultSet rsl = stmtl.executeQuery();
                if (rsl.next()) {
                    i++;
                } else {
                    return 0;
                }
                return i;
            }

//
//                String query="SELECT whatever FROM users;";
//                Statement stmt = con.createStatement();
//                ResultSet rs=stmt.executeQuery(query);
//
//                while(rs.next()){
//                    whatever.add(rs.getString(3));}
//


        } catch (SQLException e) {
            Log.e("Błąd DBEX 13", "SQL :  " + e.toString());
        }

        return k;
    }


    public String[][] getAllEmployerOffertsASC() {
        boolean isSuccess = false;
        String z = "";
        String table_name = "employer_offerts";

        try {
            //  con = new EXConnection().CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query = "select * from " + table_name + " ORDER BY nazwa ASC;";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs = stmt.executeQuery(query);
                String[][] e = new String[countEmployerOfferts()][2];
                int i = 0;
                while (rs.next()) {
                    isSuccess = true;
                    //  z = "Login successfull";
                    e[i][0] = (Integer.toString(rs.getInt(1)));
                    e[i][1] = (rs.getString(2));

                    i++;
                }

                return e;
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = "Exceptions" + ex;
            Log.e("Błąd DBEX 14", "?? :  " + ex.toString());
        }

        return null;
    }


    public int getCategory(int i) {

        String table_name = "category";
        try {
            //  con = new EXConnection().CONN();
            if (con == null) {

            } else {
                String query = "select * from "+ table_name+" WHERE _id=?";
                 PreparedStatement smts = con.prepareStatement(query);
                smts.setInt(1, i);
                ResultSet rsl = smts.executeQuery();

                if (rsl.first()) {
                    return rsl.getInt("int");
                } else {
                    return 0;
                }
            }
        } catch (Exception ex) {
            Log.e("Błąd DBEX 16", "?? :  " + ex.toString());
        }

        return 0;
    }


    public int countEmployerOfferts() {
        int i = 0;
        String table_name = "employer_offert";
        try {

            if (con == null) {
                throw new SQLException();
            } else {
                String queryl = "SELECT COUNT(_id) FROM " + table_name + "";
                Statement stmtl = con.createStatement();
                //con.prepareStatement()
                ResultSet rsl = stmtl.executeQuery(queryl);
                //int na string !
                //con.commit();

                if (rsl.first()) {
                    i = rsl.getInt(1);
                    return i;
                } else {
                    return 0;
                }

            }

//
//                String query="SELECT whatever FROM users;";
//                Statement stmt = con.createStatement();
//                ResultSet rs=stmt.executeQuery(query);
//
//                while(rs.next()){
//                    whatever.add(rs.getString(3));}
//


        } catch (SQLException e) {
            Log.e("Błąd DBEX 17", "SQL :  " + e.toString());
        }

        return i;

    }


    //
    public int countUserOffertsEMPRWhereId(int id) {
        int i = 0;

        try {

            if (con == null) {
                throw new SQLException();
            } else {
                String queryl = "SELECT COUNT(_id) FROM employer_offert WHERE acc_id=?";

                PreparedStatement smts = con.prepareStatement(queryl);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();

                if (rsl.first()) {
                    i = rsl.getInt(1);
                    Log.v("#DBEXEMPR", Integer.toString(i));
                } else {
                    return 0;
                }
                return i;
            }
//
//                String query="SELECT whatever FROM users;";
//                Statement stmt = con.createStatement();
//                ResultSet rs=stmt.executeQuery(query);
//
//                while(rs.next()){
//                    whatever.add(rs.getString(3));}
//
        } catch (SQLException e) {
            Log.e("Błąd DBEX 18", "SQL :  " + e.toString());
        }
        return i;
    }

    public int countUserOffertsEMPEWhereId(int id) {
        int i = 0;

        try {

            if (con == null) {
                throw new SQLException();
            } else {
                String queryl = "SELECT COUNT(_id) FROM employee_offert WHERE acc_id=?";

                PreparedStatement smts = con.prepareStatement(queryl);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();

                if (rsl.first()) {
                    i = rsl.getInt(1);
                } else {
                    return 0;
                }
                return i;
            }

//
//                String query="SELECT whatever FROM users;";
//                Statement stmt = con.createStatement();
//                ResultSet rs=stmt.executeQuery(query);
//
//                while(rs.next()){
//                    whatever.add(rs.getString(3));}
//


        } catch (SQLException e) {
            Log.e("Błąd DBEX 19", "SQL :  " + e.toString());
        }

        return i;

    }

    public String[] SArrayTransfer(ArrayList<String> Queed) {
        String[] temp = new String[Queed.size()];
        for (int ITERATOR = 0; ITERATOR < temp.length; ITERATOR++) {
            temp[ITERATOR] = Queed.get(ITERATOR);
        }
        return temp;
    }

    public int[] IArrayTransfer(ArrayList<Integer> Queed) {
        int[] temp = new int[Queed.size()];
        for (int ITERATOR = 0; ITERATOR < temp.length; ITERATOR++) {
            temp[ITERATOR] = Queed.get(ITERATOR);
        }
        return temp;
    }

    public String[] getEmployerOffertById(int id) {

        String table_name = "employer_offert";

        try {
            con = getC();
            if (con == null) {
                return null;

            } else {
                String Q = "SELECT * FROM " + table_name + " WHERE _id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    switch (rsl.getInt("type")) {
                        case 0: {
                            String[] data = new String[15];
                            data[0] = Integer.toString(rsl.getInt("_id"));
                            data[1] = rsl.getString("title");
                            data[2] = Integer.toString(rsl.getInt("phone"));
                            data[3] = rsl.getString("email");
                            data[4] = rsl.getString("city");
                            data[5] = rsl.getString("address");
                            data[6] = Integer.toString(rsl.getInt("address_nr"));
                            data[7] = Integer.toString(rsl.getInt("postcode"));
                            data[8] = Integer.toString(rsl.getInt("salary_o"));
                            data[9] = Integer.toString(rsl.getInt("hours"));
                            data[10] = rsl.getString("date");
                            Log.d("DBEX dateGET", rsl.getDate("date").toString());
                            data[11] = rsl.getString("info");
                            data[12] =Integer.toString( rsl.getInt("cat_id")) ;
                            data[13] =Integer.toString( rsl.getInt("acc_id")) ;
                            data[14]= Integer.toString( rsl.getInt("type")) ;
                            // week no
                            //data no
                            return data;
                        }
                        case 1: {
                            String[] data = new String[15];
                            data[0] = Integer.toString(rsl.getInt("_id"));
                            data[1] = rsl.getString("title");
                            data[2] = Integer.toString(rsl.getInt("phone"));
                            data[3] = rsl.getString("email");
                            data[4] = rsl.getString("city");
                            data[5] = rsl.getString("address");
                            data[6] = Integer.toString(rsl.getInt("address_nr"));
                            data[7] = Integer.toString(rsl.getInt("postcode"));
                            data[8] = Integer.toString(rsl.getInt("salary_a"));
                            data[9] = Integer.toString(rsl.getInt("hours"));
                            data[10] = rsl.getString("date");
                            Log.d("DBEX dateGET", rsl.getDate("date").toString());
                            data[11] = rsl.getString("info");
                            data[12] =Integer.toString( rsl.getInt("cat_id")) ;
                            data[13] =Integer.toString( rsl.getInt("acc_id")) ;
                            data[14]= Integer.toString( rsl.getInt("type")) ;
                            return data;
                        }
                        default: {
                            break;
                        }

                    }

                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 20", "SQL :  " + e.toString());
        }
        return null;
    }

    public String[] getEmployeeOffertById(int id) {

        String table_name = "employee_offert";
        String[] data = new String[11];
        try {
            con = getC();
            if (con == null) {
                return null;

            } else {
                String Q = "SELECT * FROM " + table_name + " WHERE _id=?";
                PreparedStatement smts = con.prepareStatement(Q);
                smts.setInt(1, id);
                ResultSet rsl = smts.executeQuery();
                if (rsl.first()) {
                    data[0] = Integer.toString(rsl.getInt(1));
                    data[1] = rsl.getString("title");
                    data[2] = Integer.toString(rsl.getInt("phone"));
                    data[3] = rsl.getString("email");
                    data[4] = rsl.getString("city");
                    data[5] = Integer.toString(rsl.getInt("postcode"));
                    data[6] = Integer.toString(rsl.getInt("salary"));
                    data[7] = rsl.getString("date");
                    data[8] = rsl.getString("info");
                    data[9] = Integer.toString(rsl.getInt("cat_id"));
                    data[10] = Integer.toString(rsl.getInt("acc_id"));
                    return data;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {

            Log.e("Błąd DBEX 21", "SQL :  " + e.toString());

        }
        return null;
    }


    public boolean insertUserEmprCompA(String usname, String email, String pass, int type,
                                       String name, String surname, int phone, String city, int nip, String cname, String address,
                                       int address_nr) {
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {

                String queryl = "INSERT INTO users (usname,email,password,acc_type) VALUES (?,?,?,?);";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setString(1, usname);
                stmtl.setString(2, email);
                stmtl.setString(3, pass);
                stmtl.setInt(4, type);

                int rsl = stmtl.executeUpdate();

                Log.d("#in1", "insert into user result :" + rsl);
                if (rsl >= 0) {
                    queryl = "SELECT _id FROM users WHERE usname=?";
                    stmtl = con.prepareStatement(queryl);
                    stmtl.setString(1, usname);
                    ResultSet rs1 = stmtl.executeQuery();

                    int rs = -1;
                    if (rs1.first()) {
                     rs = rs1.getInt("_id");
                     Log.d("#in2", "select id :" + rs);
                        queryl = "INSERT INTO employer_acc (name, surname, phone, city, NIP, c_name, address, address_nr, acc_id,type) VALUES ( ? , ? , ? , " +
                                " ?, ? , ?, ? , ? , ? ,?);";
                        stmtl = con.prepareStatement(queryl);
                        stmtl.setString(1, name);
                        stmtl.setString(2, surname);
                        stmtl.setInt(3, phone);
                        stmtl.setString(4, city);
                        stmtl.setInt(5, nip);
                        stmtl.setString(6, cname);
                        stmtl.setString(7, address);
                        stmtl.setInt(8, address_nr);
                        stmtl.setInt(9, rs);
                        stmtl.setInt(10, 1);
                      int  rsl2 = stmtl.executeUpdate();
                        Log.d("#in3", "insert into employer:" + rsl);
                        if (rsl2 >= 0) {
                            queryl = "SELECT _id FROM employer_acc WHERE acc_id=?";
                            stmtl = con.prepareStatement(queryl);
                            stmtl.setInt(1, rs);
                            ResultSet rs2 = stmtl.executeQuery();
                            if (rs2.first()) {
                                int rss = rs2.getInt("_id");

                                Log.d("#in4", "getEmprIdByUserID" + rs);
                                queryl = "UPDATE users SET acc_id=? WHERE _id=?";
                                stmtl = con.prepareStatement(queryl);
                                stmtl.setInt(1, rss);
                                stmtl.setInt(2, rs);
                             int   rs3 = stmtl.executeUpdate();
                                if (rs3 >= 0) {
                                    Log.d("DBEX CreateACC", "updating keys- success");
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        } catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 22", "SQL :  " + e.toString());
        }
        return false;
    }

    public int getEmprIdByUserID(int id) {
        String tablename = "employer_acc";
        try {
            con = getC();
            if (con == null) {
                return -1;
            } else {
                String queryl = "SELECT acc_id FROM " + tablename + " WHERE _id=?";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setInt(1, id);
                ResultSet rsl = stmtl.executeQuery();
                if (rsl.first()) {
                    return rsl.getInt("acc_id");
                } else return 0;
            }
        } catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 23", "SQL :  " + e.toString());
        }


        return -1;
    }
public boolean changePasswordforUserId(int id, String newpass){

    try {
        con = getC();
        if (con == null) {
            return false;
        } else {
           String  queryl = "UPDATE users SET password=? WHERE _id=?";
            PreparedStatement  stmtl = con.prepareStatement(queryl);
            stmtl.setString(1, newpass);
            stmtl.setInt(2, id);
            int   rs3 = stmtl.executeUpdate();
            if (rs3 >= 0) {
                Log.d("DBEX passwordchange", "updating keys- success");
                return true;
            } else {
                return false;
            }
        }
    }
    catch (java.sql.SQLException e) {
        Log.e("Błąd DBEX 1318", "SQL :  " + e.toString());
    }


        return false;
}
    public String[] getTagsByEmpeeOffertId(int id){
String tablename = "tags";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String queryl = "SELECT tag_id FROM " + tablename + " WHERE eo_id=?";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setInt(1, id);
                ResultSet rsl = stmtl.executeQuery();
                ArrayList<String> lista = new ArrayList();
                while (rsl.next()) {
                    int k = rsl.getInt(1);
                    String query2 = "SELECT tagname FROM tag WHERE _id="+k;
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query2);
                    if(rs.first()){
                        String a=  rs.getString(1);
                        lista.add(a);
                    }
                }
                if (lista.size() > 0) {
                    String[] listaend = new String[lista.size()];
                    for(int i=0;i<lista.size();i++){
                        listaend[i]= lista.get(i);
                    }
                        return listaend;
                } else {
                    return null;
                }
            }
        }
        catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 1251", "SQL :  " + e.toString());
        }

        return null;








    }
    public boolean insertUserEmprComp(String usname, String email, String pass, int type,
                                      String name, String surname, int phone, String city, int nip, String cname, String address,
                                      int address_nr) {
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                String queryl = "INSERT INTO users (usname,email,password,acc_type) VALUES (?,?,?,?);";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setString(1, usname);
                stmtl.setString(2, email);
                stmtl.setString(3, pass);
                stmtl.setInt(4, type);

                int rsl = stmtl.executeUpdate();

                Log.d("#in1", "insert into user result :" + rsl);
                if (rsl >= 0) {
                    queryl = "SELECT _id FROM users WHERE usname=?";
                    stmtl = con.prepareStatement(queryl);
                    stmtl.setString(1, usname);
                    ResultSet rs1 = stmtl.executeQuery();
                    int rs = -1;
                    if (rs1.first()) {
                        rs = rs1.getInt("_id");

                        queryl = "INSERT INTO employer_acc (name, surname, phone, city, NIP, c_name, address, address_nr, acc_id) VALUES ( ? , ? , ? , " +
                                " ?, ? , ?, ? , ? , ? );";
                        stmtl = con.prepareStatement(queryl);
                        stmtl.setString(1, name);
                        stmtl.setString(2, surname);
                        stmtl.setInt(3, phone);
                        stmtl.setString(4, city);
                        stmtl.setInt(5, nip);
                        stmtl.setString(6, cname);
                        stmtl.setString(7, address);
                        stmtl.setInt(8, address_nr);
                        stmtl.setInt(9, rs);
                        rsl = stmtl.executeUpdate();
                        if (rsl >= 0) {
                            Log.d("#in2", "insert into user result :" + rs1);
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        } catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 24", "SQL :  " + e.toString());
        }
        return false;
    }


    public boolean insertUserEmpe(String usname, String email, String pass, int type,
                                  String name, String surname, int phone, String city) {
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                String queryl = "INSERT INTO users (usname,email,password,acc_type) VALUES (?,?,?,?);";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setString(1, usname);
                stmtl.setString(2, email);
                stmtl.setString(3, pass);
                stmtl.setInt(4, type);
                int rsl = stmtl.executeUpdate();

                Log.d("#in11", "insert into user result :" + rsl);
                if (rsl >= 0) {
                    queryl = "SELECT _id FROM users WHERE usname=?";
                    stmtl = con.prepareStatement(queryl);
                    stmtl.setString(1, usname);
                    ResultSet rs1 = stmtl.executeQuery();
                    int rs = -1;
                    Log.d("#in22", "select result :" + rs1);
                    if (rs1.first()) {
                        rs = rs1.getInt("_id");
                        queryl = "INSERT INTO employee_acc (name, surname, phone, city, acc_id) VALUES ( ? , ? ,?, ? , ?)";
                        stmtl = con.prepareStatement(queryl);
                        stmtl.setString(1, name);
                        stmtl.setString(2, surname);
                        stmtl.setInt(3, phone);
                        stmtl.setString(4, city);
                        stmtl.setInt(5, rs);
                       int rsl2 = stmtl.executeUpdate();
                        if (rsl2 >= 0) {
                            queryl = "SELECT _id FROM employee_acc WHERE acc_id=?";
                            stmtl = con.prepareStatement(queryl);
                            stmtl.setInt(1, rs);
                            ResultSet rs2 = stmtl.executeQuery();
                            if (rs2.first()) {
                                int rss =  rs2.getInt("_id");

                                Log.d("#in33", "insert into employee_acc:" + rs);
                                queryl = "UPDATE users SET acc_id=? WHERE _id=?";
                                stmtl = con.prepareStatement(queryl);
                                stmtl.setInt(1, rss);
                                stmtl.setInt(2, rs);
                                int rs3 = stmtl.executeUpdate();
                                if (rs3 >= 0) {
                                    Log.d("DBEX CreateACC", "updating keys- success");
                                    return true;
                                } else {
                                    return false;
                                }
                            }else {
                                return false;
                            }


                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        } catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 1125", "SQL :  " + e.toString());
        }
        return false;
    }



    public boolean insertUserEmpr(String usname, String email, String pass, int type,
                                  String name, String surname, int phone, String city) {
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                String queryl = "INSERT INTO users (usname,email,password,acc_type) VALUES (?,?,?,?);";
                PreparedStatement stmtl = con.prepareStatement(queryl);
                stmtl.setString(1, usname);
                stmtl.setString(2, email);
                stmtl.setString(3, pass);
                stmtl.setInt(4, type);
                int rsl = stmtl.executeUpdate();

                Log.d("#in33", "insert into user result :" + rsl);
                if (rsl >= 0) {

                    queryl = "SELECT _id FROM users WHERE usname=?";
                    stmtl = con.prepareStatement(queryl);
                    stmtl.setString(1, usname);
                    ResultSet rs1 = stmtl.executeQuery();
                    int rs = -1;
                    Log.d("#in44", "insert into user result :" + rs);

                    if (rs1.first()) {
                        rs = rs1.getInt("_id");
                        queryl = "INSERT INTO employer_acc (name, surname, phone, city, acc_id, type) VALUES ( ? , ? ,? , ? , ?, ?)";
                        stmtl = con.prepareStatement(queryl);
                        stmtl.setString(1, name);
                        stmtl.setString(2, surname);
                        stmtl.setInt(3, phone);
                        stmtl.setString(4, city);
                        stmtl.setInt(5, rs);
                        stmtl.setInt(6, 0);
                        rsl = stmtl.executeUpdate();
                        Log.d("#in55", "insert into employer_acc result :" + rsl);
                        if (rsl >= 0) {
                            queryl = "SELECT _id FROM employer_acc WHERE acc_id=?";
                            stmtl = con.prepareStatement(queryl);
                            stmtl.setInt(1, rs);
                            ResultSet rs2 = stmtl.executeQuery();
                            if (rs2.first()) {
                                int rss = rs2.getInt("_id");
                                queryl = "UPDATE users SET acc_id=? WHERE _id=?";
                                stmtl = con.prepareStatement(queryl);
                                stmtl.setInt(1, rss);
                                stmtl.setInt(2, rs);
                                int rsl2 = stmtl.executeUpdate();
                                if (rsl2 >= 0) {
                                    Log.d("DBEX CreateACC", "updating keys- success");
                                    return true;
                                } else {
                                    return false;
                                }
                            }else {
                                return false;
                            }

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        } catch (java.sql.SQLException e) {
            Log.e("Błąd DBEX 1182", "SQL :  " + e.toString());
        }
        return false;
    }


    public boolean insertEmprOffert(int id, String title, int phone, String city, String address, int address_nr, int postcode,
                                    int type, int salary, int week_id, Date date, Date datefrom, Date dateto, int hours, String info, int cat_id) {


        String k = "INSERT INTO employer_offert(title, phone, city, address, address_nr, post-code, type, salary_a, date, date_from, date_to, hours, info, cat_id, acc_id) " +
                "VALUES(?, ?, ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";


        return false;
    }


    public boolean insertEmprOffertWithoutWeek(int id, String title, int phone, String city, String address, int address_nr, int postcode,
                                               int type, int salary, String date, String info, int cat_id) {
        String tablename = "employer_offert";
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                int accid = getAccIDByUserID(id);
                String q = "INSERT INTO " + tablename + " (title, phone, city, address, address_nr, postcode, type, salary_o,date, info, cat_id, acc_id) VALUES(?, ?, ? , ?, ?, ?, ?,?, ?, ?, ?, ? );";
                PreparedStatement stmtl = con.prepareStatement(q);
                stmtl.setString(1, title);
                stmtl.setInt(2, phone);
                stmtl.setString(3, city);
                stmtl.setString(4, address);
                stmtl.setInt(5, address_nr);
                stmtl.setInt(6, postcode);
                stmtl.setInt(7, type);
                stmtl.setInt(8, salary);
                stmtl.setString(9, date);
                //                stmtl.setDate(9, date);
                //stmtl.setString(9, datefrom);
                //stmtl.setString(10, dateto);
                stmtl.setString(10, info);
                stmtl.setInt(11, cat_id);
                stmtl.setInt(12, id);
                int rsl = stmtl.executeUpdate();
                Log.d("#in33", "insert into employer_offert result :" + rsl);
                if (rsl >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e("Błąd DBEX 1233", "SQL :  " + e.toString());
        }
        return false;
    }

    public boolean insertEmprOffertWithoutWeekSalaryPerHours(int id, String title, int phone, String city, String address, int address_nr, int postcode,
                                               int type, int salary, int hours, String date, String info, int cat_id) {
        String tablename = "employer_offert";
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                int accid = getAccIDByUserID(id);
                String q = "INSERT INTO " + tablename + " (title, phone, city, address, address_nr, postcode, type, salary_a,hours ,date, info, cat_id, acc_id) VALUES(?, ?, ? , ?, ?,?, ?, ?,?, ?, ?, ?, ? );";
                PreparedStatement stmtl = con.prepareStatement(q);
                stmtl.setString(1, title);
                stmtl.setInt(2, phone);
                stmtl.setString(3, city);
                stmtl.setString(4, address);
                stmtl.setInt(5, address_nr);
                stmtl.setInt(6, postcode);
                stmtl.setInt(7, type);
                stmtl.setInt(8, salary);
                stmtl.setInt(9, hours);
                stmtl.setString(10, date);
                //                stmtl.setDate(9, date);
                //stmtl.setString(9, datefrom);
                //stmtl.setString(10, dateto);
                stmtl.setString(11, info);
                stmtl.setInt(12, cat_id);
                stmtl.setInt(13, id);
                int rsl = stmtl.executeUpdate();
                Log.d("#in33", "insert into employer_offert result :" + rsl);
                if (rsl >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e("Błąd DBEX 1233", "SQL :  " + e.toString());
        }
        return false;
    }

    public String[][] SelectEMPROffertsByCity(String city) {

        String tablename = "employer_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT * FROM " + tablename + " WHERE city=?";
                PreparedStatement stmtl = con.prepareStatement(q);
                stmtl.setString(1, city);
                ResultSet rs = stmtl.executeQuery();
                String e[][] = new String[CountEMPROffertsByCity(city)][17];
                int i = 0;
                while (rs.next()) {
                    e[i][0] = (Integer.toString(rs.getInt("_id")));
                    e[i][1] = (rs.getString("title"));
                    e[i][2] = Integer.toString(rs.getInt("phone"));
                    e[i][3] = (rs.getString("city"));
                    e[i][4] = (rs.getString("address"));
                    e[i][5] = Integer.toString(rs.getInt("address_nr"));
                    e[i][6] = Integer.toString(rs.getInt("postcode"));
                    e[i][7] = Integer.toString(rs.getInt("type"));
                    switch (rs.getInt("type")) {
                        case 0: {
                            e[i][8] = "Umowa o dzieło";
                            e[i][9] = Integer.toString(rs.getInt("salary_o"));
                            e[i][10] = "";
                            e[i][11] = "Za wykonanie";
                            break;
                        }
                        case 1: {
                            e[i][8] = "Umowa o pracę/zlecenie";
                            e[i][9] = Integer.toString(rs.getInt("salary_a"));
                            e[i][10] = Integer.toString(rs.getInt("hours"));
                            e[i][11] = "Za godzinę*";
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    e[i][12] = "";
                    e[i][13] = "";

                    /*e[i][9] = rs.getDate("date").toString();
                    e[i][10] = rs.getDate("date_from").toString();
                    e[i][11] = rs.getDate("date_to").toString();*/
                    e[i][14] = rs.getString("info");
                    e[i][15] = Integer.toString(getCategory(rs.getInt("cat_id")));
                    e[i][16] = Integer.toString(rs.getInt("acc_id"));
                    i++;
                }
                return e;
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 1352", "SQL :  " + e.toString());

        }
        return null;
    }
    public String[][] SelectEMPROffertsByFilters(boolean[] filerts_on,  @Nullable String catID,@Nullable String city, @Nullable String Salary,
                                                 @Nullable String type){
        String tablename = "employer_offert";
        boolean tabs[] = filerts_on;
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {

                String ql = "SELECT * FROM "+tablename+" ";
                int k =0;
                for(int i=0; i< tabs.length; i++){
                    if(tabs[i])
                        k++;
                }
                if(k>0){
                    ql+=" WHERE ";
                }
Log.d("EMPRFILTERS", "licznik filtrow: "+k+";;");
                int g=k;
                for(int i=0; i< tabs.length;i++){

                    if(tabs[i]&&i==0){
                        ql+=" cat_id=? ";k--;
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }
                    if (tabs[i]&&i==1){
                        ql+=" city=? ";k--;
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }

                     if (tabs[i]&&i==2){
                        ql+=" salary_o=? ";k--;
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }
                     if (tabs[i]&&i==3){
                        ql+=" type=? ";k--;

                    }

                }
                int h=1;
                PreparedStatement stmtl = con.prepareStatement(ql);
                Log.d("EMPREEFILTERS" , "query : "+ql);
                for (int i=0; i<tabs.length;i++){
                    if(tabs[i]&&i==0) {
                        stmtl.setInt(h, Integer.parseInt(catID));
                        h++;
                    }
                    if(tabs[i]&&i==1){

                        stmtl.setString(h, city);h++; }

                    if(tabs[i]&&i==2){

                        stmtl.setInt(h, Integer.parseInt(Salary));h++;
                        }
                    if(tabs[i]&&i==3){

                        stmtl.setInt(h, Integer.parseInt(type));h++;
                        }
                }
                Log.d("EMPRFILTERS", "prepared statmeent+:"+stmtl.toString());
                ResultSet rs = stmtl.executeQuery();
                // arraylist
                ArrayList<ArrayList<String>> dane = new ArrayList();
                int i=0;
                 while(rs.next()){
                     dane.add(new ArrayList());
                    dane.get(i).add(Integer.toString(rs.getInt("_id")));
                     dane.get(i).add(rs.getString("title"));
                     dane.get(i).add(Integer.toString(rs.getInt("phone")));
                     dane.get(i).add(rs.getString("city"));
                     dane.get(i).add(rs.getString("address"));
                     dane.get(i).add(Integer.toString(rs.getInt("address_nr")));
                     dane.get(i).add(Integer.toString(rs.getInt("postcode")));
                     dane.get(i).add(Integer.toString(rs.getInt("type")));
                    switch (rs.getInt("type")) {
                        case 0: {
                            dane.get(i).add("Umowa o dzieło");
                            dane.get(i).add(Integer.toString(rs.getInt("salary_o")));
                            dane.get(i).add("");
                            dane.get(i).add("Za wykonanie");
                            break;
                        }
                        case 1: {
                            dane.get(i).add("Umowa o pracę/zlecenie");
                            dane.get(i).add(Integer.toString(rs.getInt("salary_a")));
                            dane.get(i).add(Integer.toString(rs.getInt("hours")));
                            dane.get(i).add("Za godzinę*");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                     dane.get(i).add("");
                     dane.get(i).add("");
                     dane.get(i).add(rs.getString("info"));
                     dane.get(i).add(Integer.toString(getCategory(rs.getInt("cat_id"))));
                     dane.get(i).add( Integer.toString(rs.getInt("acc_id")));
                 i++;
                }
                if(dane!=null){
                    String e[][]= new String[dane.size()][17];
                    for(i=0;i<dane.size();i++){
                        for(int iteratori=0;iteratori<dane.get(i).size();iteratori++){
                            e[i][iteratori]=dane.get(i).get(iteratori);
                        }
                    }
                    return e;
                }
                else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }
        return null;
    }

    public int countEmployeeOfferts(){
String tablename = "employee_offert";
int i =0;
        try {
            con = getC();
            if (con == null) {
                return -1;
            } else {

                String q ="SELECT COUNT(_id) FROM "+tablename+"";
                Statement stmtl = con.createStatement();
                //con.prepareStatement()
                ResultSet rsl = stmtl.executeQuery(q);
                //con.commit();
                if (rsl.first()) {
                    i = rsl.getInt(1);
                    return i;
                } else {
                    return 0;
                }

            }
        }catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }



        return -1;
    }

    public String[][] getAllEmployeeOffertsWithTags(int _count){
        String tablename = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String[][] data = new String[_count][11];
                String q ="SELECT * FROM "+tablename+" ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                int i=0;
                while(rs.next()){
                    data[i][0]= (Integer.toString(rs.getInt("_id")));
                    data[i][1] = (rs.getString("title"));
                    data[i][2] = Integer.toString(rs.getInt("phone"));
                    data[i][3] = (rs.getString("email"));
                    data[i][4] = (rs.getString("city"));
                    data[i][5] = Integer.toString(rs.getInt("postcode"));
                    data[i][6] = Integer.toString(rs.getInt("salary"));
                    data[i][7] = (rs.getString("info"));
                    data[i][8] = Integer.toString(rs.getInt("cat_id"));
                    data[i][9] = Integer.toString(rs.getInt("acc_id"));

                    String[] tagi = getTagsByEmpeeOffertId(Integer.parseInt(data[i][0]));
                    String tagii ="";
                    if(tagi!=null){
                        for(int ik=0;ik<tagi.length;ik++){
                            tagii+=tagi[ik];
                            tagii+=" ; ";

                        }
                        }
                    data[i][10]= tagii;

                    i++;
                }
                return data;

            }



        }catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }



        return null;
    }
    public String[][] getAllEmployeeOfferts(int _count){
String tablename = "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String[][] data = new String[_count][10];
            String q ="SELECT * FROM "+tablename+" ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(q);
            int i=0;
            while(rs.next()){
                data[i][0]= (Integer.toString(rs.getInt("_id")));
                data[i][1] = (rs.getString("title"));
                data[i][2] = Integer.toString(rs.getInt("phone"));
                data[i][3] = (rs.getString("email"));
                data[i][4] = (rs.getString("city"));
                data[i][5] = Integer.toString(rs.getInt("postcode"));
                data[i][6] = Integer.toString(rs.getInt("salary"));
                data[i][7] = (rs.getString("info"));
                data[i][8] = Integer.toString(rs.getInt("cat_id"));
                data[i][9] = Integer.toString(rs.getInt("acc_id"));
                i++;
            }
            return data;

            }



        }catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }



        return null;
    }


    public String[][] SelectEMPEEOffertsByFilters(boolean[] filerts_on,  @Nullable String catID,@Nullable String city){
        String tablename = "employee_offert";
        boolean tabs[] = filerts_on;
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {

                String ql = "SELECT * FROM "+tablename+" ";
                int k =0;
                for(int i=0; i< tabs.length; i++){
                    if(tabs[i])
                        k++;
                }
                if(k>0){
                    ql+=" WHERE ";
                }
                Log.d("EMPEEFILTERS", "licznik filtrow: "+k+";;");
                int g=k;
                for(int i=0; i< tabs.length;i++){

                    if(tabs[i]&&i==0){
                        ql+=" cat_id=? ";k--;
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }
                    if (tabs[i]&&i==1){
                        ql+=" city=? ";k--;

                    }
                }
                int h=1;
                PreparedStatement stmtl = con.prepareStatement(ql);
                Log.d("EMPEEFILTERS" , "query : "+ql);
                for (int i=0; i<tabs.length;i++){
                    if(tabs[i]&&i==0) {
                        stmtl.setInt(h, Integer.parseInt(catID));
                        h++;
                    }
                    if(tabs[i]&&i==1){

                        stmtl.setString(h, city);h++; }

                }
                Log.d("EMPEEFILTERS", "prepared statmeent+:"+stmtl.toString());
                ResultSet rs = stmtl.executeQuery();
                // arraylist
                ArrayList<ArrayList<String>> dane = new ArrayList();
                int i=0;
                while(rs.next()){
                    dane.add(new ArrayList());
                    dane.get(i).add(Integer.toString(rs.getInt("_id")));
                    dane.get(i).add(rs.getString("title"));
                    dane.get(i).add(Integer.toString(rs.getInt("phone")));
                    dane.get(i).add(rs.getString("email"));
                    dane.get(i).add(rs.getString("city"));
                    dane.get(i).add(Integer.toString(rs.getInt("postcode")));
                    dane.get(i).add(Integer.toString(rs.getInt("salary")));
                    dane.get(i).add(rs.getString("info"));
                    dane.get(i).add(Integer.toString(getCategory(rs.getInt("cat_id"))));
                    dane.get(i).add( Integer.toString(rs.getInt("acc_id")));

                    String[] tagi = getTagsByEmpeeOffertId(rs.getInt("_id"));
                    String tagii ="";
                    if(tagi!=null){
                        for(int ik=0;ik<tagi.length;ik++){
                            tagii+=tagi[ik];
                            tagii+=" ; ";

                        }
                    }
                    dane.get(i).add(tagii);

                    i++;
                }
                if(dane!=null){
                    String e[][]= new String[dane.size()][11];
                    for(i=0;i<dane.size();i++){
                        for(int iteratori=0;iteratori<dane.get(i).size();iteratori++){
                            e[i][iteratori]=dane.get(i).get(iteratori);
                        }
                    }
                    return e;
                }
                else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }
        return null;
    }
    public String[][] getEmprOffertsBySearch(String query){
String tablename= "employer_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT * FROM "+tablename+" WHERE title LIKE ?";
                    String search ="%"+query+"%";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setString(1, search);
                ResultSet rs = stmt.executeQuery();
                ArrayList<ArrayList<String>> dane = new ArrayList();
                int i=0;
                while(rs.next()){
                    dane.add(new ArrayList());
                    dane.get(i).add(Integer.toString(rs.getInt("_id")));
                    dane.get(i).add(rs.getString("title"));
                    dane.get(i).add(Integer.toString(rs.getInt("phone")));
                    dane.get(i).add(rs.getString("city"));
                    dane.get(i).add(rs.getString("address"));
                    dane.get(i).add(Integer.toString(rs.getInt("address_nr")));
                    dane.get(i).add(Integer.toString(rs.getInt("postcode")));
                    dane.get(i).add(Integer.toString(rs.getInt("type")));
                    switch (rs.getInt("type")) {
                        case 0: {
                            dane.get(i).add("Umowa o dzieło");
                            dane.get(i).add(Integer.toString(rs.getInt("salary_o")));
                            dane.get(i).add("");
                            dane.get(i).add("Za wykonanie");
                            break;
                        }
                        case 1: {
                            dane.get(i).add("Umowa o pracę/zlecenie");
                            dane.get(i).add(Integer.toString(rs.getInt("salary_a")));
                            dane.get(i).add(Integer.toString(rs.getInt("hours")));
                            dane.get(i).add("Za godzinę*");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    dane.get(i).add("");
                    dane.get(i).add("");
                    dane.get(i).add(rs.getString("info"));
                    dane.get(i).add(Integer.toString(getCategory(rs.getInt("cat_id"))));
                    dane.get(i).add( Integer.toString(rs.getInt("acc_id")));
                    i++;
                }
                if(dane!=null){
                    String e[][]= new String[dane.size()][17];
                    for(i=0;i<dane.size();i++){
                        for(int iteratori=0;iteratori<dane.get(i).size();iteratori++){
                            e[i][iteratori]=dane.get(i).get(iteratori);
                        }
                    }
                    return e;
                }
                else {
                    return null;
                }

                       // select title
                        // contains


            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 1788", "SQL :  " + e.toString());

        }


        return null;
    }

    public String[][] getEmpeeOffertsTagsBySearch(String query){
        String table_tag = "tag";
        String table_tags = "tags";
        String table_name = "employee_offert";

        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String sq ="%"+query+"%";
        String q = "SELECT _id FROM "+table_tag+" WHERE tagname LIKE ?";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setString(1, sq);
                ResultSet rs = stmt.executeQuery();
                ArrayList<Integer> lista_match_id = new ArrayList();
                while(rs.next()){
                    lista_match_id.add(rs.getInt("_id"));
                 }
                if(!rs.first()){
                    return null;
                }else {
                    ArrayList<ArrayList<String>> dane = new ArrayList();
                    int kk=0;
                    for (int i = 0; i < lista_match_id.size(); i++) {
                        String q1 = "SELECT eo_id FROM " + table_tags + " WHERE tag_id=?";
                        PreparedStatement stmt1 = con.prepareStatement(q1);
                        stmt1.setInt(1, lista_match_id.get(i));
                        ResultSet rs1 = stmt1.executeQuery();
                        while(rs1.next()){
                            dane.add(new ArrayList<String>());
                            String q2 = "SELECT * FROM "+table_name+" WHERE _id=?";
                            PreparedStatement stmt2 = con.prepareStatement(q2);
                            stmt2.setInt(1, rs1.getInt("eo_id"));
                            ResultSet rs2 = stmt2.executeQuery();
                            if(rs2.first()) {
                                dane.get(kk).add(Integer.toString(rs2.getInt("_id")));
                                dane.get(kk).add(rs2.getString("title"));
                                dane.get(kk).add(Integer.toString(rs2.getInt("phone")));
                                dane.get(kk).add(rs2.getString("email"));
                                dane.get(kk).add(rs2.getString("city"));
                                dane.get(kk).add(Integer.toString(rs2.getInt("postcode")));
                                dane.get(kk).add(Integer.toString(rs2.getInt("salary")));
                                dane.get(kk).add(rs2.getString("info"));
                                dane.get(kk).add(Integer.toString(getCategory(rs2.getInt("cat_id"))));
                                dane.get(kk).add( Integer.toString(rs2.getInt("acc_id")));

                                String[] tagi = getTagsByEmpeeOffertId(rs2.getInt("_id"));
                                String tagii ="";
                                if(tagi!=null){
                                    for(int ik=0;ik<tagi.length;ik++){
                                        tagii+=tagi[ik];
                                        tagii+=" ; ";

                                    }
                                }
                                dane.get(kk).add(tagii);

                                kk++;
                            }

                        }


                    }
                    if(dane!=null){
                        String e[][]= new String[dane.size()][11];
                        for(int ii=0;ii<dane.size();ii++){
                            for(int iteratori=0;iteratori<dane.get(ii).size();iteratori++){
                                e[ii][iteratori]=dane.get(ii).get(iteratori);
                            }
                        }
                        return e;
                    }
                    else {
                        return null;
                    }


                }

            }
        }catch (SQLException e) {
            Log.e("Błąd DBEX 2160", "SQL :  " + e.toString());

        }


        return null;
    }


    public String[][] getEmpeeOffertsBySearch(String query){
        String tablename= "employee_offert";
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                String q = "SELECT * FROM "+tablename+" WHERE title LIKE ?";
                String search ="%"+query+"%";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setString(1, search);
                ResultSet rs = stmt.executeQuery();
                ArrayList<ArrayList<String>> dane = new ArrayList();
                int i=0;
                while(rs.next()){
                    dane.add(new ArrayList());
                    dane.get(i).add(Integer.toString(rs.getInt("_id")));
                    dane.get(i).add(rs.getString("title"));
                    dane.get(i).add(Integer.toString(rs.getInt("phone")));
                    dane.get(i).add(rs.getString("email"));
                    dane.get(i).add(rs.getString("city"));
                    dane.get(i).add(Integer.toString(rs.getInt("postcode")));
                    dane.get(i).add(Integer.toString(rs.getInt("salary")));
                    dane.get(i).add(rs.getString("info"));
                    dane.get(i).add(Integer.toString(getCategory(rs.getInt("cat_id"))));
                    dane.get(i).add( Integer.toString(rs.getInt("acc_id")));

                    String[] tagi = getTagsByEmpeeOffertId(rs.getInt("_id"));
                    String tagii ="";
                    if(tagi!=null){
                        for(int ik=0;ik<tagi.length;ik++){
                            tagii+=tagi[ik];
                            tagii+=" ; ";

                        }
                    }
                    dane.get(i).add(tagii);

                    i++;
                }
                if(dane!=null){
                    String e[][]= new String[dane.size()][11];
                    for(i=0;i<dane.size();i++){
                        for(int iteratori=0;iteratori<dane.get(i).size();iteratori++){
                            e[i][iteratori]=dane.get(i).get(iteratori);
                        }
                    }
                    return e;
                }
                else {
                    return null;
                }

                // select title
                // contains


            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 2145", "SQL :  " + e.toString());

        }


        return null;
    }
    public String[][] SelectEMPROffertsByFilters2(@Nullable String city, @Nullable String catID, @Nullable String Salary,
                                                 @Nullable String type){
        String tablename = "employer_offert";
        boolean tabs[] = new boolean[4] ;
        try {
            con = getC();
            if (con == null) {
                return null;
            } else {
                if (city!=null)
                { tabs[0]=true;}else { tabs[1]=false; }
                if (catID!=null)
                { tabs[1]=true;}else { tabs[1]=false; }
                if (Salary!=null)
                { tabs[2]=true;}else { tabs[2]=false; }
                if (type!=null)
                { tabs[3]=true;}else { tabs[1]=false; }
            String ql = "SELECT * FROM "+tablename+"";
                int k =0;
                for(int i=0; i< tabs.length; i++){
                    if(tabs[i])
                        k++;
                  }
                String[] tabb = new String[4];
                if (city!=null)
                { tabb[0]=city;}
                if (catID!=null)
                { tabb[1]=catID;}
                if (Salary!=null)
                { tabb[2]=Salary;}
                if (type!=null)
                { tabb[3]=type;}
                int g=k;
                for(int i=0; i< tabs.length;i++){
                    if(tabs[i]&&i==0){
                        ql+=" WHERE city=? ";
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }else if (tabs[i]&&i==1){
                        ql+=" WHERE cat_id=? ";
                        if(k!=0){
                            ql+= " AND ";
                        }
                    }
                    else if (tabs[i]&&i==2){
                        ql+=" WHERE salary_o=? ";
                        if(k!=0){
                        ql+= " AND ";
                        }
                    }
                    else if (tabs[i]&&i==3){
                        ql+=" WHERE type=? ";
                        g++;
                    }
                    k--;
                }
                int h=1;
                PreparedStatement stmtl = con.prepareStatement(ql);
                for (int i=0; i<tabs.length;i++){
                    if(tabs[i]&&i==0){
                        if(h<g){
                            stmtl.setString(h, city);h++; }}
                    if(tabs[i]&&i==1){
                        if(h<g){
                            stmtl.setInt(h, Integer.parseInt(catID));h++;
                        }}
                    if(tabs[i]&&i==2){
                        if(h<g){
                            stmtl.setInt(h, Integer.parseInt(Salary));h++;
                    }  }
                    if(tabs[i]&&i==3){
                        if(h<g){
                            stmtl.setInt(h, Integer.parseInt(type));h++;
                    }  }
                    }
                ResultSet rs = stmtl.executeQuery();

                String e[][]= new String[CountEMPROffertsWithFiltersCity(ql, tabb)][12];
                int i=0;
                while(rs.next()){
                    e[i][0] = (Integer.toString(rs.getInt("_id")));
                    e[i][1] = (rs.getString("title"));
                    e[i][2] = Integer.toString(rs.getInt("phone"));
                    e[i][3] = (rs.getString("city"));
                    e[i][4] = (rs.getString("address"));
                    e[i][5] = Integer.toString(rs.getInt("address_nr"));
                    e[i][6] = Integer.toString(rs.getInt("postcode"));
                    e[i][7] = Integer.toString(rs.getInt("type"));
                    switch (rs.getInt("type")) {
                        case 0: {
                            e[i][8] = "Umowa o dzieło";
                            e[i][9] = Integer.toString(rs.getInt("salary_o"));
                            e[i][10] = "";
                            e[i][11] = "Za wykonanie";
                            break;
                        }
                        case 1: {
                            e[i][8] = "Umowa o pracę/zlecenie";
                            e[i][9] = Integer.toString(rs.getInt("salary_a"));
                            e[i][10] = Integer.toString(rs.getInt("hours"));
                            e[i][11] = "Za godzinę*";
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    e[i][12] = "";
                    e[i][13] = "";
                }
                if(e!=null){
                    return e;
                }
                else {
                    return null;
                }
            }
        } catch (SQLException e) {
            Log.e("Błąd DBEX 1388", "SQL :  " + e.toString());

        }
        return null;
    }

        public boolean deleteEmployerOffertById(int id){
            String table_name = "employer_offert";
            try {
                con = getC();
                if (con == null) {
                    return false;

                } else {
            String q = "DELETE FROM "+table_name+" WHERE _id=?";
                    PreparedStatement stmt = con.prepareStatement(q);
                    stmt.setInt(1, id);
                    int rs = stmt.executeUpdate();
                    if(rs>=0){
                        return true;
                    }
                    else {
                        return false;
                    }
                }

            }catch(SQLException e){
                Log.e("Błąd DBEX 2373", "?? :  " + e.toString());

            }

return false;
        }
    public boolean deleteEmployeeOffertById(int id){
        String table_name = "employee_offert";
        String table_tags = "tags";
        try {
            con = getC();
            if (con == null) {
                return false;

            } else {
                String q = "DELETE FROM "+table_name+" WHERE _id=?";
                PreparedStatement stmt = con.prepareStatement(q);
                stmt.setInt(1, id);
                int rs = stmt.executeUpdate();
                if(rs>=0){
                    String q1 = "DELETE FROM "+table_tags+" WHERE eo_id=?";
                    PreparedStatement stmt1 = con.prepareStatement(q1);
                    stmt1.setInt(1, id);
                    rs = stmt1.executeUpdate();
                    if(rs>=0){
                    return true;}
                    else {
                    return false;}
                }
                else {
                    return false;
                }
            }

        }catch(SQLException e){
            Log.e("Błąd DBEX 2373", "?? :  " + e.toString());

        }

        return false;
    }
        public int CountEMPROffertsWithFiltersCity(String ql, String[] a){
        String tablename = "employer_offert";
        try {
            con = getC();
            if(con==null){
                return -1;

            }else {
                 String l = ql.replace("*", "COUNT(_id)");
                 PreparedStatement stmt= con.prepareStatement(l);
                 int h= 1;
                 for (int i=0;i<a.length;i++) {
                     try {
                       if(a[i]!=null){
                           if(i==0){
                                   stmt.setString(h, a[i]); }
                           if(i==1){

                                   stmt.setInt(h, Integer.parseInt(a[i]));h++;
                              }
                           if(i==2){

                                   stmt.setInt(h, Integer.parseInt(a[i]));h++;
                                }
                           if(i==3){
                                   stmt.setInt(h, Integer.parseInt(a[i]));h++;
                               }
                           }
                     } catch (NullPointerException e) {

                     }
                 }


            }

        }catch(Exception e){
            Log.e("Błąd DBEX 1431", "?? :  " + e.toString());

        }

        return -1;
        }

        public int CountEMPROffertsByCity(String city) {
            String tablename = "employer_offert";
            try {
                con = getC();
                if (con == null) {
                    return -1;
                } else {
                    String q = "SELECT COUNT(_id) FROM " + tablename + " WHERE city=?";
                    PreparedStatement stmtl = con.prepareStatement(q);
                    stmtl.setString(1, city);
                    ResultSet rs = stmtl.executeQuery();

                    if (rs.first()) {
                        return rs.getInt(1);

                    } else {
                        return 0;
                    }
                }
            } catch (SQLException e) {
                Log.e("Błąd DBEX 1458", "SQL :  " + e.toString());
            }
            return -1;
        }

        public String[] getEmployerInfoForEmployerOffertByAccID(int accid, int id){
        String table_employer_offert = "employer_offert";
        String table_users = "users";
        String table_employeracc ="employer_acc";
        try {
                con = getC();
                if (con == null) {
                    return null;
                } else {
                   /* String qmain = "Select acc_id from "+table_employer_offert+" where _id=?";
                    PreparedStatement stmtl = con.prepareStatement(qmain);
                    stmtl.setInt(1, id);
                    ResultSet rs = stmtl.executeQuery();
                    int aid  = -1;
                    if (rs.first()) {
                        id = rs.getInt("acc_id");
                    }else {
                        return null;

                    }*/
                    String[] data= new String[1];

                    String q1 = "SELECT type FROM "+ table_employeracc+" WHERE acc_id=?";
                    PreparedStatement stmtl = con.prepareStatement(q1);
                    stmtl.setInt(1, accid);
                     ResultSet rs = stmtl.executeQuery();
                    int type_employer = -1;
                    Log.d("DBEX EMPROFFERT", "accID: "+accid);
                    Log.d("DBEX EMPROFFERT", "query: "+q1);
                    Log.d("DBEX EMPROFFERT", "query2: "+stmtl.toString());
                    if (rs.first()) {
                        type_employer=  rs.getInt(1);
                        Log.d("DBEX EMPROFFERT", "type employer"+type_employer);
                        Log.d("DBEX EMPROFFERT", "powinno byc "+rs.getInt(1));
                        // 1 company, 0 persona
                    } else {
                        Log.d("DBEX EMPROFFERT", "type employer"+type_employer);
                        return data;
                    }
                String q2 = "SELECT (email) FROM "+table_users+" WHERE _id=?";
                    stmtl = con.prepareStatement(q2);
                    stmtl.setInt(1, accid);
                    rs = stmtl.executeQuery();
                    String email = "";
                    if (rs.first()) {
                        email=  rs.getString(1);
                        Log.d("DBEX EMPROFFERT", "email :"+email);
                    }
                    else {
                        return data;
                    }

                    switch (type_employer){
                        case 1:{
                            data= new String[7];
                            String q3 = "SELECT * FROM "+table_employeracc+" WHERE acc_id=?";
                            stmtl = con.prepareStatement(q3);
                            stmtl.setInt(1, accid);
                            rs = stmtl.executeQuery();
                            Log.d("DBEX EMPROFFERT", "type 1 :"+rs.toString());
                            if (rs.first()) {
                                data[0]= rs.getString("c_name");
                                data[1]= rs.getString("city");
                                data[2]= rs.getString("address");
                                data[3]= Integer.toString(rs.getInt("address_nr"));
                                data[4]= Integer.toString(rs.getInt("NIP"));
                                data[5]= Integer.toString(rs.getInt("phone"));
                                data[6]=email;
                            }
                            else {
                                return data;
                            }

                            break;}

                        case 0 : {
                            data= new String[5];
                            String q3 = "SELECT * FROM "+table_employeracc+" WHERE acc_id=?";
                            stmtl = con.prepareStatement(q3);
                            stmtl.setInt(1, accid);
                            rs = stmtl.executeQuery();
                            if (rs.first()) {
                                data[0]= rs.getString("name");
                                data[1]= rs.getString("surname");
                                data[2]= rs.getString("city");
                                data[3]= Integer.toString(rs.getInt("phone"));
                                data[4]=email;
                            }
                            else {
                                return data;
                            }


                        break;}
                    }
                    return data;

                }
            }
              catch (SQLException e) {
                    Log.e("Błąd DBEX 1568", "SQL :  " + e.toString());
                }
        return null;
        }

        public  int getAccIDByUserID(int id){
        String table_users= "users";
        try {
            con = getC();
            if (con == null) {
                return -1;
            } else {
                String query = "SELECT acc_id FROM " + table_users + " WHERE _id=?";
                PreparedStatement stmtl = con.prepareStatement(query);
                stmtl.setInt(1, id);
                ResultSet rs = stmtl.executeQuery();
                if(rs.first()){
                    return rs.getInt("acc_id");
                               }
                else {
                    return -1;
                }
            }
        }
            catch (SQLException e) {
                Log.e("Błąd DBEX 1647", "SQL :  " + e.toString());
            }

        return -1;
        }

        public boolean insertEmpeeOffert(String title, int phone, String email, String city, int postcode, int salary, String date, String desc,
                                         int cat_id, int accid, ArrayList<String> list){
        String tablename = "employee_offert";
try {
    con = getC();
    if (con == null) {
        return false;}
    else {

        String q = "INSERT INTO " + tablename + " (title, phone, email,city, postcode, salary,date, info, cat_id, acc_id) VALUES(?, ?, ? , ?,?, ?, ?, ?, ?, ? );";
        PreparedStatement stmtl = con.prepareStatement(q);
        stmtl.setString(1, title);
        stmtl.setInt(2, phone);
        stmtl.setString(3, email);
        stmtl.setString(4,city);
        stmtl.setInt(5, postcode);
        stmtl.setInt(6, salary);
        stmtl.setString(7, date);
        stmtl.setString(8, desc);
        stmtl.setInt(9, cat_id);
        stmtl.setInt(10, accid);
        int rsl = stmtl.executeUpdate();
        Log.d("#DBEX", "insert into employee_offert result :" + rsl);
        if (rsl >= 0) {
               q = "SELECT LAST_INSERT_ID()";
            Statement stmt = con.createStatement();
            // stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(q);
             if(rs.next()) {
            int lastid = rs.getInt(1);
            Log.d("DBEX last id:", ""+lastid);
 for(int i=0;i<list.size();i++){
                // tag w tag przed tags
    //                String qin = "INSERT INTO tags";
                //select
                int tempid = -1;
                String q1 = "SELECT _id FROM tag WHERE tagname=?";
                 stmtl = con.prepareStatement(q1);
                stmtl.setString(1, list.get(i));
                    rs = stmtl.executeQuery();
                    if(rs.first()){
                         tempid = rs.getInt(1);
                        String q2 = "SELECT count FROM tag WHERE _id="+tempid;
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(q2);
                        if(rs.first()){
                            int counted = rs.getInt(1);
                            counted++;
                            String q3 = "UPDATE tag SET count="+counted+" WHERE _id="+tempid;
                            stmt = con.createStatement();
                           int rs1 = stmt.executeUpdate(q3);
                           if(rs1>=0){
                               //git
                               String q4 = "INSERT INTO tags (tag_id, eo_id) VALUES ("+tempid+","+lastid+")";
                               stmt = con.createStatement();
                               rs1 = stmt.executeUpdate(q4);
                                if(rs1>=0){

                                }
                                else {return false;}
                           }
                           else {

                           }
                        }else {
                            return false;
                        }

                    }else {
                        String q2 = "INSERT INTO tag (tagname, count) VALUES (?, 1 )";
                        stmtl = con.prepareStatement(q2);
                        stmtl.setString(1, list.get(i));
                        int rs2 = stmtl.executeUpdate();
                        if(rs2>=0){
                            String q3 = "SELECT LAST_INSERT_ID()";
                             stmt = con.createStatement();
                            // stmt.executeUpdate(query);
                             rs = stmt.executeQuery(q3);
                            if(rs.next()) {
                                int lastidtag = rs.getInt(1);
                                String q4 = "INSERT INTO tags (tag_id, eo_id) VALUES ("+lastidtag+","+lastid+")";
                                stmt = con.createStatement();
                                rs2 = stmt.executeUpdate(q4);
                                if(rs2>=0){}
                                else{
                                    return false;
                                }
                            }

                        }else {
                            return false;
                        }

                            //add
                    }

                // +1 OR add
                // get last id
                // add to tags z id



            }

            }
            //
        } else {
            return false;
        }


    }

        }
            catch (SQLException e) {
                Log.e("Błąd DBEX 1647", "SQL :  " + e.toString());
            }
            return false;
        }

public boolean checkFollowedByEMPEEId( int id, int offertid) {
    String tablename = "f_employer_offerts";

    try {
        con = getC();
        if (con == null) {
            return false;
        } else {
            String query = "SELECT _id FROM " +tablename+" WHERE empe_id=? AND offert_id=?";
            PreparedStatement stmtl = con.prepareStatement(query);
            stmtl.setInt(1, id);
            stmtl.setInt(2, offertid);
            ResultSet rs = stmtl.executeQuery();
            if(rs.first()){
                return true;
            }
            else{ return false;}


        }



    }catch (SQLException e){
        Log.e("Błąd 2838 1647", "SQL :  " + e.toString());


    }
    return false;
}

    public boolean checkFollowedByEMPRId( int id, int offertid) {
        String tablename = "f_employee_offerts";

        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                String query = "SELECT _id FROM " +tablename+" WHERE empr_id=? AND offert_id=?";
                PreparedStatement stmtl = con.prepareStatement(query);
                stmtl.setInt(1, id);
                stmtl.setInt(2, offertid);
                ResultSet rs = stmtl.executeQuery();
                if(rs.first()){
                    return true;
                }
                else{ return false;}


            }



        }catch (SQLException e){
            Log.e("Błąd 2838 1647", "SQL :  " + e.toString());


        }
        return false;
    }

    public boolean unfollowEMPEEOffert(int userid, int offertid){
String tablename = "f_employee_offerts";
try {
    con = getC();
    if (con == null) {
        return false;
    } else {
        String query = "DELETE FROM "+tablename+" WHERE offert_id =? AND empr_id=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, offertid);
        stmt.setInt(2, userid);
        int rs = stmt.executeUpdate();
        if(rs>=0){
            return true;
        }else{
            return false;
        }

    }
}
catch (SQLException e ){
    Log.e("Błąd DBEX 2893", "?? :  " + e.toString());

}

        return false;
    }
    public boolean unfollowEMPROffert(int userid, int offertid){
        String tablename = "f_employer_offerts";
        try {
            con = getC();
            if (con == null) {
                return false;
            } else {
                String query = "DELETE FROM "+tablename+" WHERE offert_id =? AND empe_id=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, offertid);
                stmt.setInt(2, userid);
                int rs = stmt.executeUpdate();
                if(rs>=0){
                    return true;
                }else{
                    return false;
                }

            }
        }
        catch (SQLException e ){
            Log.e("Błąd DBEX 2893", "?? :  " + e.toString());

        }

        return false;
    }


public boolean followEMPEEOffert(int userid, int offertid){
String tablename = "f_employee_offerts";
    try {
        con = getC();
        if (con == null) {
            return false;
        } else {
        String query = "INSERT INTO "+tablename+" (offert_id, empr_id) VALUES (?, ?) ";
            PreparedStatement stmtl = con.prepareStatement(query);
            stmtl.setInt(1, offertid);
            stmtl.setInt(2, userid);
            int rsl = stmtl.executeUpdate();
            if (rsl >= 0) {
                return true;
            }else {
                return false;
            }


        }
    }catch (SQLException e){

        Log.e("Błąd DBEX 2945", "?? :  " + e.toString());
    }


        return false;
}

    public boolean followEMPROffert(int userid, int offertid){
        String tablename = "f_employer_offerts";
        try {
            con = getC();
                if (con == null) {
                    return false;
            } else {
                String query = "INSERT INTO "+tablename+" (offert_id, empe_id) VALUES (?, ?) ";
                PreparedStatement stmtl = con.prepareStatement(query);
                stmtl.setInt(1, offertid);
                stmtl.setInt(2, userid);
                int rsl = stmtl.executeUpdate();
                if (rsl >= 0) {
                    return true;
                }else {
                    return false;
                }


            }
        }catch (SQLException e){

            Log.e("Błąd DBEX 2945", "?? :  " + e.toString());
        }


        return false;
    }

    public int countUserFollowedOffertsEMPEWhereId(int id){
        String tablename = "f_employer_offerts";
        try {
            con = getC();
            if (con == null) {
                return -1;
            } else {
                String query = "SELECT COUNT(_id) FROM "+tablename+" WHERE empe_id=?";
                PreparedStatement stmtl = con.prepareStatement(query);
                stmtl.setInt(1, id);
                ResultSet rs = stmtl.executeQuery();
                if (rs.first()) {
                    return rs.getInt(1);

                } else {
                    return 0;
                }


            }

        }
        catch(SQLException e){
            Log.e("Błąd DBEX 3008", "?? :  " + e.toString());



        }





        return -1;
    }

public String[][] getSmallEmpeFollowedOffertsByUserId(int id ){
        String tablename = "f_employer_offerts";
        String tablename1 = "employer_offert";
        String[][] data;
        ArrayList<ArrayList<String>> aray = new ArrayList();
        try {
            con = getC();
            if (con == null) {
                return null;}
            else {
                String query = "SELECT offert_id FROM " + tablename + " WHERE empe_id=?";
                PreparedStatement stmtl = con.prepareStatement(query);
                stmtl.setInt(1, id);
                ResultSet rs = stmtl.executeQuery();
                while(rs.next()){
                    int tempid= rs.getInt(1);
                    aray.add(new ArrayList());
                    String query1 = "SELECT _id, title, city FROM " + tablename1 + " WHERE _id=?";
                    PreparedStatement stmt2 = con.prepareStatement(query1);
                    stmt2.setInt(1, tempid);
                    ResultSet rs1 = stmt2.executeQuery();
                    if(rs1.first()){
                        String title = rs1.getString("title");
                        String city = rs1.getString("city");
                        int iid = rs1.getInt("_id");
                        aray.get(aray.size()-1).add(title); aray.get(aray.size()-1).add(city);
                        aray.get(aray.size()-1).add(Integer.toString(iid));
                    }
                }
                data= new String[aray.size()][3];
                for(int i=0;i<aray.size();i++){
                    for(int ii=0;ii<aray.get(i).size();ii++){
                        data[i][ii]=aray.get(i).get(ii);
                    }

                }
                if(data!=null){
                    return data;
                }else {
                    return null;
                }


            }

        }catch(SQLException e){
            Log.e("Błąd DBEX 3042", "SQL :  " + e.toString());


        }

        return null;
}

public int countUserFollowedOffertsEMPRWhereId(int id) {
    String tablename = "f_employee_offerts";
    try {
        con = getC();
        if (con == null) {
            return -1;
        } else {
            String query = "SELECT COUNT(_id) FROM "+tablename+" WHERE empr_id=?";
            PreparedStatement stmtl = con.prepareStatement(query);
            stmtl.setInt(1, id);
            ResultSet rs = stmtl.executeQuery();
            if (rs.first()) {
                return rs.getInt(1);

            } else {
                return 0;
            }


        }

    }
    catch(SQLException e){
        Log.e("Błąd DBEX 3008", "?? :  " + e.toString());



    }
    return -1;

}


public String[][] getSmallEmprFollowedOffertsByUserId(int id){
    String tablename = "f_employee_offerts";
    String tablename1 = "employee_offert";
    String[][] data;
    ArrayList<ArrayList<String>> aray = new ArrayList();
    try {
        con = getC();
        if (con == null) {
            return null;}
        else {
            String query = "SELECT offert_id FROM " + tablename + " WHERE empr_id=?";
            PreparedStatement stmtl = con.prepareStatement(query);
            stmtl.setInt(1, id);
            ResultSet rs = stmtl.executeQuery();
            while(rs.next()){
                int tempid= rs.getInt(1);
                aray.add(new ArrayList());
                String query1 = "SELECT _id, title, city FROM " + tablename1 + " WHERE _id=?";
                PreparedStatement stmt2 = con.prepareStatement(query1);
                stmt2.setInt(1, tempid);
                ResultSet rs1 = stmt2.executeQuery();
                if(rs1.first()){
                    String title = rs1.getString("title");
                    String city = rs1.getString("city");
                    int iid = rs1.getInt("_id");
                    aray.get(aray.size()-1).add(title); aray.get(aray.size()-1).add(city);
                    aray.get(aray.size()-1).add(Integer.toString(iid));
                }
            }
            data= new String[aray.size()][3];
            for(int i=0;i<aray.size();i++){
                for(int ii=0;ii<aray.get(i).size();ii++){
                    data[i][ii]=aray.get(i).get(ii);
                }

            }
            if(data!=null){
                return data;
            }else {
                return null;
            }


        }

    }catch(SQLException e){
        Log.e("Błąd DBEX 3163", "SQL :  " + e.toString());


    }

    return null;
}




 }  // END CLASS




//    private class DBEXAccount extends AsyncTask<String,String,String,String,String,String> {
//
//
//    }


