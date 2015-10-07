package com.example.dean.sqlitetutorial.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dean.sqlitetutorial.person.Person;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class DatabaseHandler {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBOpenHelper mDBOpenHelper;

    public DatabaseHandler(Context context){
        mContext = context;
    }
    public void addPerson(Person person){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBFeederContract.PersonTable.COLUMN_NAME, person.getName());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER, person.getPhoneNumber());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_EMAIL, person.getEmail());

        mDatabase.insert(DBFeederContract.PersonTable.TABLE_NAME, null, contentValues);
        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;
    }
    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();

        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBFeederContract.PersonTable.TABLE_NAME, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                int tempID = cursor.getInt(cursor.getColumnIndex(DBFeederContract.PersonTable._ID));
                String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
                String tempNum = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));
                String tempMail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));

                Person tempPerson = new Person(tempID, tempName, tempNum, tempMail);
                people.add(tempPerson);

            }while (cursor.moveToNext());
            cursor.close();
        }
        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;

        return new ArrayList<Person>(people);
    }
    public Person getPerson(int id){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();
        Person tempPerson = null;

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " +DBFeederContract.PersonTable.TABLE_NAME, null);
        if(cursor != null && cursor.moveToFirst()){
            int tempID = cursor.getInt(cursor.getColumnIndex(DBFeederContract.PersonTable._ID));
            String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
            String tempNum = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));
            String tempMail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));

            tempPerson = new Person(tempID, tempName, tempNum, tempMail);
        }
        cursor.close();
        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;
        return tempPerson;
    }
    public void removePerson(int id){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getWritableDatabase();

        mDatabase.execSQL("DELETE FROM " +DBFeederContract.PersonTable.TABLE_NAME
                            + " WHERE " + DBFeederContract.PersonTable._ID + " = " + id);

        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;
    }
}
