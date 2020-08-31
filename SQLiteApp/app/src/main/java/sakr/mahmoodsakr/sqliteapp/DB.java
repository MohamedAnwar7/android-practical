package sakr.mahmoodsakr.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mahmood.Sakr on 27/04/2017.
 */
public class DB extends SQLiteOpenHelper {
    private static final String Dbname = "Sakr_database.db";
    private static final String Tablename = "newTable";
    private static int version_code = 2;

    /* version number of the database (starting at 1); if the database is older,
           onUpgrade will be used to upgrade the database; if the database is
           newer, onDowngrade will be used to downgrade the database
     */
    private Context context;

    //  /data/data/packagename/databases/Dbname.db
    public DB(Context context) {
        super(context, Dbname, null, version_code);   // create the database
        this.context = context;
        Toast.makeText(context, "Constructor is Called Successfully .", Toast.LENGTH_SHORT).show();
        String Dbpath = context.getDatabasePath(Dbname).getPath();
        Log.d("sakr", "DB Path : " + Dbpath);
        Toast.makeText(context, " DB path is " + Dbpath, Toast.LENGTH_LONG).show();
        //null >> create your custom cursor object
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //created when the db is being created at the first time
        // execSql >> performe single sql query that doesn't return data
        try {
            db.execSQL("create table " + Tablename + "( id integer primary key autoincrement , name text , email text);");
            Toast.makeText(context, "DataBase is Created Successfully .", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context, " DB Creation exception ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //use this method to upgrade to a new database schema
        // Not mandatory to make drop to the table , you can make any query in the upgrade method
        try {
            db.execSQL("drop table if exists " + Tablename);
            Toast.makeText(context, "database old version : " + oldVersion + " is Upgraded Successfully to newly version :" + newVersion, Toast.LENGTH_LONG).show();
            onCreate(db); // to recreate the table again
        } catch (SQLException e) {
            Toast.makeText(context, " DB Upgrade exception ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + Tablename);
            Toast.makeText(context, "database is downgraged - old version : " + oldVersion + " && newly version :" + newVersion, Toast.LENGTH_LONG).show();
            onCreate(db); // to recreate the table again
        } catch (SQLException e) {
            Toast.makeText(context, " DB Downgraged exception ", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean insertDate(String name, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);
        long result = db.insert(Tablename, null, content);
        // result >> the row ID of the newly inserted row, or -1 if an error occurred
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateDate(String id, String name, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        //update based the id column
        content.put("name", name);
        content.put("email", email);
        int result = db.update(Tablename, content, "id=?", new String[]{id});
        //result >> the number of rows affected or >> = 0 if no update was done !
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeData(String id) {
// Firstly searching for the id
        SQLiteDatabase db1 = getReadableDatabase();

        Cursor cursor = db1.rawQuery("select * from " + Tablename + " where id=?", new String[]{id});
        // cursor like result set in java
        Boolean flag = false; // not founded as a default result
        if (cursor.moveToNext()) {
            flag = true;  // founded
        }
        if (flag == true) {
// continue the delete process
            SQLiteDatabase db = getWritableDatabase();
            int result = db.delete(Tablename, "id=?", new String[]{id});
             // result >>= the number of rows affected , >> = 0 if No Deletion occur .
            // To remove all rows , pass "1" as the whereClause.
//            int result = db.delete(Tablename, "1",null); // all rows are deleted from table
            return true;
        } else {
            return false;
        }
    }

    public ArrayList getAllData() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Tablename, null);
        //Cursor object is positioned before the first entry.
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            arrayList.add(id + "   -   " + name + "   -   " + email);
        }
        return arrayList;
    }

    public boolean searchByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from " + Tablename + " where name=? ", new String[]{name});
        Boolean flag = false;
        if (cursor.moveToNext()) {
            flag = true;
        }
        return flag;
    }

}