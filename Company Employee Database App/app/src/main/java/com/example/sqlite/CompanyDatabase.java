package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompanyDatabase extends SQLiteOpenHelper {

    //Company table and database
    public static final String companyDatabase = "company.db";
    public static final String companyTable = "companyTable";

    //Employee table and database
    public static final String employeeTable = "employeeTable";

    public CompanyDatabase(Context context) {
        super(context, companyDatabase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+companyTable+" (CompanyID INTEGER PRIMARY KEY AUTOINCREMENT , CompanyName Text, CompanyWebsite Text) ");
        db.execSQL("create table "+employeeTable+" (EmployeeID INTEGER, CompanyID INTEGER, EmployeeName TEXT, EmployeePosition TEXT, EmployeeJoinedOn Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+companyTable);
        db.execSQL("drop table if exists "+employeeTable);
        onCreate(db);
    }

    public boolean addCompanyDetails(String name, String Website){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("CompanyName", name);
        contentValues.put("CompanyWebsite", Website);

        if(db.insert(companyTable, null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public boolean addEmployeeDetails(int companyID, String name, String position, String joinedOn){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("CompanyID", companyID);
        contentValues.put("EmployeeID", counter(companyID)+1);
        contentValues.put("EmployeeName", name);
        contentValues.put("EmployeePosition", position);
        contentValues.put("EmployeeJoinedOn", joinedOn);

        if(db.insert(employeeTable, null, contentValues) == -1)
            return false;
        return true;
    }

    public boolean deleteAllCompanyData(){
        SQLiteDatabase db = getWritableDatabase();

        int x = db.delete(companyTable, null, null);
        if(x == -1){
            return false;
        }
        else {
            //delete company data
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + companyTable + "'");

            //delete employee data
            db.delete(employeeTable, null, null);
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + employeeTable + "'");
            return true;
        }
    }

    public boolean deleteAllEmployeeData(int companyID){
        SQLiteDatabase db = getWritableDatabase();

        int x = db.delete(employeeTable, "CompanyID = ?", new String[] {companyID+""});
        if(x == -1){
            return false;
        }
        else {
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + employeeTable + "'");
            return true;
        }
    }

    public Cursor viewAllEmployeeData(int companyID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+employeeTable+" WHERE CompanyID = "+companyID, null);
        return res;
    }

    public Cursor viewAllCompanyData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+companyTable, null);
        return res;
    }

    public void updateDetails(String ID, String name, String Website){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("CompanyName", name);
        contentValues.put("CompanyWebsite", Website);

        db.update(companyTable, contentValues, "CompanyID = ?", new String[] {ID});

    }

    public int counter(int companyID){
        Cursor res = viewAllEmployeeData(companyID);
        int count = res.getCount();
        return count;
    }
}
