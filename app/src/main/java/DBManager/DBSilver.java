package DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 8/1/2015.
 */
public class DBSilver {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "movie";
    public static final String KEY_TIME = "time";
    public static final String KEY_DATE = "date";
    public static final String KEY_IMDB = "imdb";
    public static final String KEY_SYN = "synopsis";

    private static final String DATABASE_NAME = "silver";
    private static final String DATABASE_TABLE = "movietable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NAME + " TEXT NOT NULL, " +
                            KEY_DATE + " TEXT NOT NULL, " +
                            KEY_IMDB + " TEXT NOT NULL, " +
                            KEY_SYN + " TEXT NOT NULL, " +
                            KEY_TIME + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }

    public DBSilver(Context c) {
        ourContext = c;
    }

    public DBSilver openandwrite() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String name, String time, String date, String imdb, String syn) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_TIME, time);
        cv.put(KEY_DATE, date);
        cv.put(KEY_IMDB, imdb);
        cv.put(KEY_SYN, syn);
        Log.d("data inserted = ", "yes");
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public void refreshTable() {
        ourDatabase.delete(DATABASE_TABLE,null,null);

    }

    public Object[] getData(int ch) {
        // TODO Auto-generated method stub

        ArrayList<String> time = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> imdb = new ArrayList<String>();
        ArrayList<String> syn = new ArrayList<String>();
        //String result1 = "";
        String[] s = new String[]{ KEY_ROWID, KEY_NAME, KEY_TIME, KEY_DATE, KEY_IMDB, KEY_SYN};
        Cursor c = ourDatabase.query(DATABASE_TABLE, s, null, null, null, null, null);
        //int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iTime = c.getColumnIndex(KEY_TIME);
        int iDate = c.getColumnIndex(KEY_DATE);
        int iImdb = c.getColumnIndex(KEY_IMDB);
        int iSyn = c.getColumnIndex(KEY_SYN);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
//            if(ch == 0)
//                result.add("\n" + c.getString(iName)+"\n");
            name.add(c.getString(iName));
            time.add(c.getString(iTime));
            date.add(c.getString(iDate));
            imdb.add(c.getString(iImdb));
            syn.add(c.getString(iSyn));
            // = result + /*c.getString(iRow) +*/ " " + c.getString(iName);
            //result1 = result1 + /*c.getString(iRow) +*/ " " + c.getString(iAddress);
            //result1[i] = c.getString(iAddress);
        }
        //if(ch == 0)
        //v.setBackgroundColor(Color.parseColor("#a1cc3b"));

        Object[] obj = {name, time, date, imdb, syn};

        return obj;



    }

    public void deleteTitleGivenName(String myName)
    {
        //return ourDatabase.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[] { myName });
        ourDatabase.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[] { myName });
        Log.d("data deleted = ", "yes");
        //ourDatabase.delete(DATABASE_TABLE, KEY_NAME+"="+myName , null);
    }
}
