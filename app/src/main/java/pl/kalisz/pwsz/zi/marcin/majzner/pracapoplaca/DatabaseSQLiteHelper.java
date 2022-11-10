package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSQLiteHelper {

    final Context context;
    private DatabaseSQLiteHelper.SQLiteHelper DBHelper;
    private SQLiteDatabase db;

    private static final String DB_NAME = "MM";
    private static final int DB_VERSION = 1;

    public static final String KEY_ROWID = "_id";
    static final String TAG = "DBSQLHELPER";


    /*TABLE : app_cords  */

    public static final String KEY_APP_USERNAME = "USERNAME";
    public static final String KEY_APP_HIDEN = "HIDEN";
    public static final String KEY_APP_ACC_TYPE = "ACCOUNT_TYPE"; // ACC ID - 0 pierwsze uruchomienie ACC ID - 1 - gosc  ACC ID - 2 - acc
    public static final String DATABASE__APP_TABLE = "APP_ACCOUNTS";
    public static final String KEY_APP_LANGUAGE = "LANGUAGE";
    public static final String DATABASE_APP_CREATE = "CREATE TABLE " + DATABASE__APP_TABLE + " (" + KEY_ROWID +
            " integer primary key autoincrement, "
            + KEY_APP_USERNAME + " TEXT, "
            + KEY_APP_HIDEN + " integer, "
            + KEY_APP_LANGUAGE + " integer, "
            + KEY_APP_ACC_TYPE + " integer ); ";

    /*TABLE : account_main  */
    public static final String KEY_ACC_USERNAME = "USERNAME";
    public static final String KEY_ACC_EMAIL = "EMAIL";
    public static final String KEY_ACC_PASSWORD = "PASSWORD";
    public static final String KEY_ACC_TYPE = "ACC_TYPE";
    public static final String KEY_ACC_CONNECTION = "ACCCONT";
    public static final String DATABASE_ACC_TABLE = "ACCOUNT";
    public static final String DATABASE_ACC_CREATE = "CREATE TABLE " + DATABASE_ACC_TABLE + " (" + KEY_ROWID +
            " integer primary key autoincrement, "
            + KEY_ACC_USERNAME + " TEXT, "
            + KEY_ACC_EMAIL + " TEXT, "
            + KEY_ACC_PASSWORD + " TEXT, "
            + KEY_ACC_TYPE + " integer, "
            + KEY_ACC_CONNECTION + " integer); ";

    /*TABLE : employee  */

    public static final String KEY_EMPLEE_NAME = "NAME";
    public static final String KEY_EMPLEE_SURNAME = "SURNAME";
    public static final String KEY_EMPLEE_PHONE = "PHONE";
    public static final String KEY_EMPLEE_CITY = "CITY";
    public static final String DATABASE_EMPLEE_TABLE = "ACCOUNT_EMPLOYEE";
    public static final String DATABASE_EMPLEE_CREATE = "CREATE TABLE " + DATABASE_EMPLEE_TABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_EMPLEE_NAME + " TEXT, "
            + KEY_EMPLEE_SURNAME + " TEXT, "
            + KEY_EMPLEE_PHONE + " integer, "
            + KEY_EMPLEE_CITY + " TEXT); ";

    /*TABLE : employer  */

    public static final String KEY_EMPLRR_NAME = "NAME";
    public static final String KEY_EMPLRR_SURNAME = "SURNAME";
    public static final String KEY_EMPLRR_PHONE = "PHONE";
    public static final String KEY_EMPLRR_CITY = "CITY";
    public static final String KEY_EMPLRR_NIP = "NIP";
    public static final String DATABASE_EMPLRR_TABLE = "ACCOUNT_EMPLOYER";
    public static final String DATABASE_EMPLRR_CREATE = "CREATE TABLE " + DATABASE_EMPLRR_TABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_EMPLRR_NAME + " TEXT, "
            + KEY_EMPLRR_SURNAME + " TEXT, "
            + KEY_EMPLRR_PHONE + " integer, "
            + KEY_EMPLRR_CITY + " TEXT, "
            + KEY_EMPLRR_NIP + " integer); ";


    /*TABLE CATEGORY OF JOBS*/
    public static final String KEY_CATEGORY_NAME = "NAME";
    public static final String DATABASE_CATEGORY_TABLE = "CATEGORY";
    public static final String DATABASE_CATEGORY_CREATE = "CREATE TABLE " + DATABASE_CATEGORY_TABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_CATEGORY_NAME + " TEXT); ";

    public DatabaseSQLiteHelper(Context ctx) {
        this.context = ctx;
        DBHelper = new SQLiteHelper(context);
    }

    /*TABLE Emmployee offer             na pewno bedzie polaczenie do imienia itd.*/

    public static final String KEY_EMPLOYEE_OFFER_NAME = "NAME";
    public static final String KEY_EMPLOYEE_OFFER_CATEGORY_KEY = "CATEGORY";        // JEDEN LUB WIECEJ
    public static final String KEY_EMPLOYEE_OFFER_CITY = "CITY";
    public static final String KEY_EMPLOYEE_OFFER_CITY_ZIP_CODE = "ZIP-CODE";
    public static final String KEY_EMPLOYEE_OFFER_CONTACT_PHONE = "CONTACT_PHONE";
    public static final String KEY_EMPLOYEE_OFFER_CONTACT_EMAIL = "CONTACT_EMAIL";
    public static final String KEY_EMPLOYEE_OFFER_TYPE= "TYPE";   // TYP oferty  wiecej w todolist.txt
    public static final String KEY_EMPLOYEE_OFFER_SALARY_MIN = "SALARY-MIN";
    public static final String KEY_EMPLOYEE_OFFER_DESCRIPTION = "DESCRIPTION";

    public static final String DATABASE_EMPLOYEE_OFFER_TABLE = "EMPLOYEE_OFFER";
    public static final String DATABASE_EMPLOYEE_OFFER_CREATE = "CREATE TABLE " + DATABASE_EMPLOYEE_OFFER_TABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_EMPLOYEE_OFFER_NAME + " TEXT, "
            + KEY_EMPLOYEE_OFFER_CATEGORY_KEY + " integer, "
                   + KEY_EMPLOYEE_OFFER_CITY + " TEXT, "
            + KEY_EMPLOYEE_OFFER_CITY_ZIP_CODE + " TEXT, "
            + KEY_EMPLOYEE_OFFER_CONTACT_PHONE + " integer, "
            + KEY_EMPLOYEE_OFFER_CONTACT_EMAIL + " TEXT, "
            + KEY_EMPLOYEE_OFFER_TYPE + " integer, "
            + KEY_EMPLOYEE_OFFER_SALARY_MIN + " integer, "
            + KEY_EMPLOYEE_OFFER_DESCRIPTION + " TEXT); ";


    /*TABLE Emmployer offer*/

    public static final String KEY_EMPLOYER_OFFER_NAME = "NAME";
    public static final String KEY_EMPLOYER_OFFER_CATEGORY_KEY = "CATEGORY";
    public static final String KEY_EMPLOYER_OFFER_CONTACT_PHONE = "CONTACT-PHONE";
    public static final String KEY_EMPLOYER_OFFER_ADDRRESS = "ADDRESS";
    public static final String KEY_EMPLOYER_OFFER_ADDRESS_NR = "ADDRESS_NR";
    public static final String KEY_EMPLOYER_OFFER_CITY = "CITY";
    public static final String KEY_EMPLOYER_OFFER_CITY_ZIP_CODE = "ZIP-CODE";
    public static final String KEY_EMPLOYER_OFFER_CONTACT_ADDRESS = "CONTACT_ADDRESS";
    public static final String KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR = "CONTACT_ADDRESS_NR";
    public static final String KEY_EMPLOYER_OFFER_TYPE= "TYPE";   // TYP oferty  wiecej w todolist.txt
    public static final String KEY_EMPLOYER_OFFER_SALARY = "SALARY";
    public static final String KEY_EMPLOYER_OFFER_DESCRIPTION = "DESCRIPTION";
    public static final String DATABASE_EMPLOYER_OFFER_TABLE = "EMPLOYER_OFFER";
    public static final String DATABASE_EMPLOYER_OFFER_CREATE = "CREATE TABLE " + DATABASE_EMPLOYER_OFFER_TABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_EMPLOYER_OFFER_NAME + " TEXT, "
            + KEY_EMPLOYER_OFFER_CATEGORY_KEY + " integer, "
                    + KEY_EMPLOYER_OFFER_CONTACT_PHONE + " integer, "
            + KEY_EMPLOYER_OFFER_ADDRRESS + " TEXT, "
            + KEY_EMPLOYER_OFFER_ADDRESS_NR + " TEXT, "
            + KEY_EMPLOYER_OFFER_CITY + " TEXT, "
            + KEY_EMPLOYER_OFFER_CITY_ZIP_CODE + " TEXT, "
            + KEY_EMPLOYER_OFFER_CONTACT_ADDRESS + " TEXT, "
            + KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR + " TEXT, "
            + KEY_EMPLOYER_OFFER_TYPE + " integer, "
            + KEY_EMPLOYER_OFFER_SALARY + " integer, "
            + KEY_EMPLOYER_OFFER_DESCRIPTION + " TEXT); ";







    /*
    SQLITE HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
            HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
            HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
            HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
            HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER
    HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER HELPER*/
    private static class SQLiteHelper extends SQLiteOpenHelper {
        SQLiteHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_APP_CREATE);
                insertAPP_Record(db, "Gość", 2, 1,  0);
                db.execSQL(DATABASE_ACC_CREATE);
                insertAccRecord(db, "Gość", "", "", 2, 0);

                db.execSQL(DATABASE_EMPLEE_CREATE);
                insertEmployeeRecord(db, "#Log", "614214", 01010101, "DEC");

                db.execSQL(DATABASE_EMPLRR_CREATE);

                insertEmployerRecord(db, "#Log", "614214", 10011101, "DEC", 0111100);

                db.execSQL(DATABASE_CATEGORY_CREATE);
                insertCategoryRecord(db, "Ogrodnik");
                insertCategoryRecord(db, "Operator kamery");
                insertCategoryRecord(db, "Kucharz");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE__APP_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_ACC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_EMPLEE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_EMPLRR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CATEGORY_TABLE);
            onCreate(db);
        }

        private void insertAPP_Record(SQLiteDatabase db, String username, int hiden,int langueage ,int acc_key) {
            Log.d("#SQLiteinsretrecord", "konstrukt ");
            ContentValues obiektValues = new ContentValues();
            obiektValues.put(KEY_APP_USERNAME, username);
            obiektValues.put(KEY_APP_HIDEN, hiden);
            obiektValues.put(KEY_APP_LANGUAGE, hiden);
            obiektValues.put(KEY_APP_ACC_TYPE, langueage);
            db.insert(DATABASE__APP_TABLE, null, obiektValues);


        }
    private void insertAccRecord(SQLiteDatabase db, String username, String email, String password, int acctype, int accconnection) {
        ContentValues obiektValues = new ContentValues();
        obiektValues.put(KEY_ACC_USERNAME, username);
        obiektValues.put(KEY_ACC_EMAIL, email);
        obiektValues.put(KEY_ACC_PASSWORD, password);
        obiektValues.put(KEY_ACC_TYPE, acctype);
        obiektValues.put(KEY_ACC_CONNECTION, accconnection);
        db.insert(DATABASE_ACC_TABLE, null, obiektValues);

    }

        private void insertEmployeeRecord(SQLiteDatabase db, String NAME, String SURNAME, int PHONE, String CITY) {
            ContentValues obiektValues = new ContentValues();
            obiektValues.put(KEY_EMPLEE_NAME, NAME);
            obiektValues.put(KEY_EMPLEE_SURNAME, SURNAME);
            obiektValues.put(KEY_EMPLEE_PHONE, PHONE);
            obiektValues.put(KEY_EMPLEE_CITY, CITY);
            db.insert(DATABASE_EMPLEE_TABLE, null, obiektValues);

        }

        private void insertEmployerRecord(SQLiteDatabase db, String NAME, String SURNAME, int PHONE, String CITY, int NIP) {
            ContentValues obiektValues = new ContentValues();
            obiektValues.put(KEY_EMPLRR_NAME, NAME);
            obiektValues.put(KEY_EMPLRR_SURNAME, SURNAME);
            obiektValues.put(KEY_EMPLRR_PHONE, PHONE);
            obiektValues.put(KEY_EMPLRR_CITY, CITY);
            obiektValues.put(KEY_EMPLRR_NIP, NIP);
            db.insert(DATABASE_EMPLRR_TABLE, null, obiektValues);

        }
    private void insertCategoryRecord(SQLiteDatabase db, String NAME) {
        ContentValues obiektValues = new ContentValues();
        obiektValues.put(KEY_CATEGORY_NAME, NAME);
       db.insert(DATABASE_CATEGORY_TABLE, null, obiektValues);

    }


    }
//END SQLIITEHELPER


    public DatabaseSQLiteHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close()
    {
        DBHelper.close();
    }




    /* APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
     APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
    APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL APP SQL
     */
    /*
     insert record in app table where acc kkey is 1 for
    2- for
     0 for
*/
    public long insertAPPAccStart( String username, int hiden,int language, int acc_key)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_APP_USERNAME, username);
        initialValues.put(KEY_APP_HIDEN, hiden);
        initialValues.put(KEY_APP_LANGUAGE, language);
        initialValues.put(KEY_APP_ACC_TYPE, acc_key);
        return db.insert(DATABASE__APP_TABLE, null, initialValues);
    }

    //---deletes a particular Organisation---
    public boolean deleteAppAcc(long rowId)
    {
        return db.delete(DATABASE__APP_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the app account logs---
    public Cursor getAPPAllAccountsStarters()
    {
        return db.query(DATABASE__APP_TABLE, new String[] {KEY_ROWID, KEY_APP_USERNAME,KEY_APP_HIDEN,KEY_APP_LANGUAGE,
                KEY_APP_ACC_TYPE}, null, null, null, null, null);
    }

    //---retrieves a particular Organisation---
    public Cursor getAPPAccStart(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE__APP_TABLE, new String[] {KEY_ROWID,
                                KEY_APP_USERNAME, KEY_APP_HIDEN,KEY_APP_LANGUAGE,KEY_APP_ACC_TYPE}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves a last APPLICTION account registered---
    public Cursor getAPPLastLogin() throws SQLException
    {
        Cursor mCursor =getAPPAllAccountsStarters();
        if (mCursor != null) {
            if(mCursor.moveToLast()){
                return mCursor;
            }

        }
        return mCursor;
    }

    //---updates a Organisation---
    public boolean updateAPPAccStart(long rowId, String username, int hiden, int acc_key)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_APP_USERNAME, username);
        args.put(KEY_APP_HIDEN, hiden);
        args.put(KEY_APP_ACC_TYPE, acc_key);
        return db.update(DATABASE__APP_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---inserts new record of language --
    public long updateAPPLanguage(int language)
    {
        ContentValues args = new ContentValues();
        Cursor crs = getAPPLastLogin();
        if(language!=crs.getInt(3)) {
            args.put(KEY_APP_USERNAME, crs.getColumnName(1));
            args.put(KEY_APP_HIDEN, crs.getInt(2));
            args.put(KEY_APP_LANGUAGE, language);
            args.put(KEY_APP_ACC_TYPE, crs.getInt(4));
                }
        else {return 0;}
        return db.insert(DATABASE__APP_TABLE, null, args);
    }


    /*insert logout record */
    public long logout_APPinsert( String username)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_APP_USERNAME, username);
        initialValues.put(KEY_APP_HIDEN, 1);
        initialValues.put(KEY_APP_ACC_TYPE, 0); //2 poniewaz ktos sie logowal
        return db.insert(DATABASE__APP_TABLE, null, initialValues);
    }


     /*   ACCOUNT $$$$$$ ACCOUNT $$$$$$$ ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$
    ACCOUNT $$$$$$ ACCOUNT $$$$$$$  ACCOUNT $$$$$$ ACCOUNT $$$$$$$ */

    //---insert a acc into the database---
    public long insertAcc(String username, String email, String password, int acctype, int accconection)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ACC_USERNAME, username);
        initialValues.put(KEY_ACC_EMAIL, email);
        initialValues.put(KEY_ACC_PASSWORD, password);
        initialValues.put(KEY_ACC_TYPE, acctype);
        initialValues.put(KEY_ACC_CONNECTION, accconection);
        return db.insert(DATABASE_ACC_TABLE, null, initialValues);
    }

    //---deletes a particular acc---
    public boolean deleteAcc(long rowId)
    {
        return db.delete(DATABASE_ACC_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the acc---
    public Cursor getAllAccounts()
    {
        return db.query(DATABASE_ACC_TABLE, new String[] {KEY_ROWID, KEY_ACC_USERNAME,
                KEY_ACC_EMAIL, KEY_ACC_TYPE, KEY_ACC_CONNECTION}, null, null, null, null, null);
    }

    //---retrieves a particular acc by email---
    public boolean isEmailRegistered(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ACC_EMAIL + "=" + email, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            return true;
        }else {
        return false;}
    }

    public boolean isEmailRegistered2(String email)
    {
        try {
            Cursor mCursor =
                    db.query(true, DATABASE_ACC_TABLE, new String[]{KEY_ROWID,
                                    KEY_ACC_USERNAME, KEY_ACC_EMAIL, KEY_ACC_TYPE, KEY_ACC_CONNECTION}, KEY_ACC_EMAIL + "=" + email, null,
                            null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
                return true;
            } else {
                return false;
            }
        }catch(SQLException e){return false;}
    }
    //---retrieves a particular acc by email---
    public boolean isEmailRegistered3(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ACC_EMAIL + "=" + email, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            return true;
        }else {
            return false;}
    }
    //---retrieves a particular acc by email---
    public boolean isEmailRegistered4(String email) throws SQLException
    {
        String[] args = {email};
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ACC_EMAIL + " LIKE ?" , args,
                        null, null, null, null);
        if (mCursor != null) {
            if(mCursor.moveToFirst()){return true;}else{return false;}

        }
        else {return true;}

    }

    // login verification by email and password
    //---retrieves a particular acc by email---
    public boolean tryLogin(String email, String password) throws SQLException
    {
        String[] args = {email};
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME , KEY_ACC_EMAIL, KEY_ACC_PASSWORD}, KEY_ACC_EMAIL + " LIKE ?", args,
                        null, null, null, null);
        if (mCursor != null) {
            if(mCursor.moveToFirst()){
               /* Log.d("@@#zero", mCursor.getString(0));
                Log.d("@@#j", mCursor.getString(1));
                Log.d("@@#dwa", mCursor.getString(2));
                Log.d("@@#trzy", mCursor.getString(3));*/
                if(password.equalsIgnoreCase(mCursor.getString(3)))
                {return true;}else {return false;}
            }else { return false;}

        }
        return false;
    }

    //---retrieves a particular acc---
    public Cursor getAcc(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves a particular acc  by email---
    public Cursor getAccbyMail(String mail) throws SQLException
    {
        String[] args = {mail};
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ACC_EMAIL + " LIKE ?", args,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves a particular acc  by username---
    public Cursor getAccbyUsername(String username) throws SQLException
    {
        String[] args = {username};
        Cursor mCursor =
                db.query(true, DATABASE_ACC_TABLE, new String[] {KEY_ROWID,
                                KEY_ACC_USERNAME, KEY_ACC_EMAIL,KEY_ACC_TYPE,  KEY_ACC_CONNECTION}, KEY_ACC_USERNAME + " LIKE ?", args,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean isNumberReg(long nr){

        return true;
    }

    //---updates a acc---
    public boolean updateAcc(long rowId, String nazwa, String rodzaj)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ACC_USERNAME, nazwa);
        args.put(KEY_ACC_EMAIL, rodzaj);
        args.put(KEY_ACC_TYPE, rodzaj);
        args.put(KEY_ACC_CONNECTION, rodzaj);
        return db.update(DATABASE_ACC_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

/*
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee  employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee  employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee  employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee  employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    employeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee*/

    //---insert a acc into the database---
    public long insertEmployee(String NAME, String SURNAME, int PHONE, String CITY)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMPLEE_NAME, NAME);
        initialValues.put(KEY_EMPLEE_SURNAME, SURNAME);
        initialValues.put(KEY_EMPLEE_PHONE, PHONE);
        initialValues.put(KEY_EMPLEE_CITY, CITY);

        return db.insert(DATABASE_EMPLEE_TABLE, null, initialValues);
    }

    //---deletes a particular acc---
    public boolean deleteEmployee(long rowId)
    {
        return db.delete(DATABASE_EMPLEE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the acc---
    public Cursor getAllEmployees()
    {
        return db.query(DATABASE_EMPLEE_TABLE, new String[] {KEY_ROWID, KEY_EMPLEE_NAME,
                KEY_EMPLEE_SURNAME,KEY_EMPLEE_PHONE,KEY_EMPLEE_CITY}, null, null, null, null, null);
    }

    //---retrieves a particular acc by phone nr---
    public boolean isPhoneRegistered(int phone) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_EMPLEE_TABLE, new String[] {KEY_ROWID, KEY_EMPLEE_NAME,
                                KEY_EMPLEE_SURNAME,KEY_EMPLEE_PHONE,KEY_EMPLEE_CITY}, KEY_EMPLEE_PHONE + "=" + phone, null,
                        null, null, null, null);
        if (mCursor != null) {
            if(mCursor.moveToFirst())
            return true;
            else {
                return false;
            }
        }
        return false;
    }

    //---retrieves a particular acc---
    public Cursor getEmployee(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_EMPLEE_TABLE, new String[] {KEY_ROWID, KEY_EMPLEE_NAME,
                                KEY_EMPLEE_SURNAME,KEY_EMPLEE_PHONE,KEY_EMPLEE_CITY}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a acc---
    public boolean updateEmployee(long rowId, String NAME, String SURNAME, int PHONE, String CITY)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMPLEE_NAME, NAME);
        args.put(KEY_EMPLEE_SURNAME, SURNAME);
        args.put(KEY_EMPLEE_PHONE, PHONE);
        args.put(KEY_EMPLEE_CITY, CITY);

        return db.update(DATABASE_EMPLEE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
/*
employerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrremployerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrRRRRRRRRRRRRRRRRRRRRRR
        RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrrrrrrrrrrrrrrrr*/


    //---insert a acc into the database---
    public long insertEmployer(String NAME, String SURNAME, int PHONE, String CITY, int NIP)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMPLRR_NAME, NAME);
        initialValues.put(KEY_EMPLRR_SURNAME, SURNAME);
        initialValues.put(KEY_EMPLRR_PHONE, PHONE);
        initialValues.put(KEY_EMPLRR_CITY, CITY);
        initialValues.put(KEY_EMPLRR_NIP, NIP);
        return db.insert(DATABASE_EMPLRR_TABLE, null, initialValues);
    }

    //---deletes a particular acc---
    public boolean deleteEmployer(long rowId)
    {
        return db.delete(DATABASE_EMPLRR_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the acc---
    public Cursor getAllEmployers()
    {
        return db.query(DATABASE_EMPLRR_TABLE, new String[] {KEY_ROWID, KEY_EMPLRR_NAME,
                KEY_EMPLRR_SURNAME,KEY_EMPLRR_PHONE,KEY_EMPLRR_CITY,KEY_EMPLRR_NIP}, null, null, null, null, null);
    }

    //---retrieves a particular acc by phone---
    public boolean isPhoneRegistered(long phone) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_EMPLRR_TABLE, new String[] {KEY_ROWID, KEY_EMPLRR_NAME,
                                KEY_EMPLRR_SURNAME,KEY_EMPLRR_PHONE,KEY_EMPLRR_CITY,KEY_EMPLRR_NIP}, KEY_EMPLRR_PHONE + "=" + phone, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            return true;
        }
        return false;
    }

    //---retrieves a particular acc---
    public Cursor getEmployer(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_EMPLRR_TABLE, new String[] {KEY_ROWID, KEY_EMPLRR_NAME,
                                KEY_EMPLRR_SURNAME,KEY_EMPLRR_PHONE,KEY_EMPLRR_CITY,KEY_EMPLRR_NIP}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a acc---
    public boolean updateEmployer(long rowId, String NAME, String SURNAME, int PHONE, String CITY, String NIP)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMPLRR_NAME, NAME);
        args.put(KEY_EMPLRR_SURNAME, SURNAME);
        args.put(KEY_EMPLRR_PHONE, PHONE);
        args.put(KEY_EMPLRR_CITY, CITY);
        args.put(KEY_EMPLRR_CITY, NIP);
        return db.update(DATABASE_EMPLRR_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


    /*CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CAT CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CAT CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CAT CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY
    CATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORYCATEGORY CATEGORY CATEGORY CATEGORY*/
    //---insert a contact into the database---
    public long insertCategory(String name)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY_NAME, name);
        return db.insert(DATABASE_CATEGORY_TABLE, null, initialValues);
    }

    //---deletes a particular Organisation---
    public boolean deleteCategory(long rowId)
    {
        return db.delete(DATABASE_CATEGORY_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the Organisation---
    public Cursor getAllCategory()
    {
        return db.query(DATABASE_CATEGORY_TABLE, new String[] {KEY_ROWID, KEY_CATEGORY_NAME,
        }, null, null, null, null, null);
    }

    //---retrieves a particular Organisation---
    public Cursor getCategory(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_CATEGORY_TABLE, new String[] {KEY_ROWID,
                                KEY_CATEGORY_NAME}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a Organisation---
    public boolean updateCategory(long rowId, String nazwa, String rodzaj)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_CATEGORY_NAME, nazwa);

        return db.update(DATABASE_CATEGORY_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


    /*WORK OFFER WORK OFFER WORK WORK OFFER WORK WORK WORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    WORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORKWORK OFFER WORK OFFER WORK WORK OFFER WORK WORK
    */



    //---insert a acc into the database---
    public long insertEmployerOffert(String NAME, String CATEGORY, int PHONE,String ADRESS,
                                     int ADRESS_NR, String CITY,String ZIPCODE,
                                     String CONTACT_ADDRESS, int CONTACT_ADDRESS_NR,
                                     int TYPE, int SALARY, String DESCRIPTION )
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMPLOYER_OFFER_NAME, NAME);
        initialValues.put(KEY_EMPLOYER_OFFER_CATEGORY_KEY, CATEGORY);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_PHONE, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_ADDRRESS, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_ADDRESS_NR, ADRESS_NR);
        initialValues.put(KEY_EMPLOYER_OFFER_CITY, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_CITY_ZIP_CODE, ZIPCODE);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_ADDRESS, CONTACT_ADDRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR, CONTACT_ADDRESS_NR);
        initialValues.put(KEY_EMPLOYER_OFFER_TYPE, TYPE);
        initialValues.put(KEY_EMPLOYER_OFFER_SALARY, SALARY);
        initialValues.put(KEY_EMPLOYER_OFFER_DESCRIPTION, DESCRIPTION);
       return db.insert(DATABASE_EMPLOYER_OFFER_TABLE, null, initialValues);
    }

    //---deletes a particular acc---
    public boolean deleteEmployerOffert(long rowId)
    {
        return db.delete(DATABASE_EMPLOYER_OFFER_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the acc---
    public Cursor getAllEmployersOfferts()
    {
        return db.query(DATABASE_EMPLOYER_OFFER_TABLE, new String[] {KEY_ROWID, KEY_EMPLOYER_OFFER_NAME,
                KEY_EMPLOYER_OFFER_CATEGORY_KEY,KEY_EMPLOYER_OFFER_CONTACT_PHONE,KEY_EMPLOYER_OFFER_ADDRRESS,KEY_EMPLOYER_OFFER_ADDRESS_NR,
                KEY_EMPLOYER_OFFER_CITY,KEY_EMPLOYER_OFFER_CITY_ZIP_CODE,KEY_EMPLOYER_OFFER_CONTACT_ADDRESS,KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR,
                KEY_EMPLOYER_OFFER_TYPE,KEY_EMPLOYER_OFFER_SALARY,KEY_EMPLOYER_OFFER_DESCRIPTION}, null, null, null, null, null);
    }


    //---retrieves a particular acc---
    public Cursor getEmployerOffert(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(DATABASE_EMPLOYER_OFFER_TABLE, new String[] {KEY_ROWID, KEY_EMPLOYER_OFFER_NAME,
                        KEY_EMPLOYER_OFFER_CATEGORY_KEY,KEY_EMPLOYER_OFFER_CONTACT_PHONE,KEY_EMPLOYER_OFFER_ADDRRESS,KEY_EMPLOYER_OFFER_ADDRESS_NR,
                        KEY_EMPLOYER_OFFER_CITY,KEY_EMPLOYER_OFFER_CITY_ZIP_CODE,KEY_EMPLOYER_OFFER_CONTACT_ADDRESS,KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR,
                        KEY_EMPLOYER_OFFER_TYPE,KEY_EMPLOYER_OFFER_SALARY,KEY_EMPLOYER_OFFER_DESCRIPTION}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a acc---
    public boolean updateEmployerOffert(long rowId, String NAME, String CATEGORY, int PHONE,String ADRESS,
                                        int ADRESS_NR, String CITY,String ZIPCODE,
                                        String CONTACT_ADDRESS, int CONTACT_ADDRESS_NR,
                                        int TYPE, int SALARY, String DESCRIPTION )
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMPLOYER_OFFER_NAME, NAME);
        initialValues.put(KEY_EMPLOYER_OFFER_CATEGORY_KEY, CATEGORY);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_PHONE, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_ADDRRESS, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_ADDRESS_NR, ADRESS_NR);
        initialValues.put(KEY_EMPLOYER_OFFER_CITY, ADRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_CITY_ZIP_CODE, ZIPCODE);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_ADDRESS, CONTACT_ADDRESS);
        initialValues.put(KEY_EMPLOYER_OFFER_CONTACT_ADDRESS_NR, CONTACT_ADDRESS_NR);
        initialValues.put(KEY_EMPLOYER_OFFER_TYPE, TYPE);
        initialValues.put(KEY_EMPLOYER_OFFER_SALARY, SALARY);
        initialValues.put(KEY_EMPLOYER_OFFER_DESCRIPTION, DESCRIPTION);
        return db.update(DATABASE_EMPLOYER_OFFER_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }


/*
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER
    EMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFEREMPLOYEE OFFER*/












    //END CLASS
}
