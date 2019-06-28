package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeListView extends AppCompatActivity {

    TextView noEmployeeMessage;
    FloatingActionButton employeeFloatingButton;
    ListView employeeListView;
    CompanyDatabase companyDatabase;
    int companyID;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_view);

        noEmployeeMessage = findViewById(R.id.noEmployeeMessage);
        employeeFloatingButton = findViewById(R.id.employeeFloatingButton);
        employeeListView = findViewById(R.id.employeeListView);
        companyDatabase = new CompanyDatabase(this);

        Bundle COMPANY_ID = getIntent().getExtras();
        companyID = COMPANY_ID.getInt("companyID");

        res = companyDatabase.viewAllEmployeeData(companyID);
        if(res.getCount() == 0)
            noEmployeeMessage.setVisibility(View.VISIBLE);

        else
            viewAll();

        addEmployeeDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.deleteAllMenu){
            deleteAllEmployeeData();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        res = companyDatabase.viewAllEmployeeData(companyID);
        if(res.getCount() == 0)
            noEmployeeMessage.setVisibility(View.VISIBLE);
        else
            viewAll();
    }

    public  void addEmployeeDetails(){
        employeeFloatingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(EmployeeListView.this, AddEmployeeDetails.class);
                        i.putExtra("companyID", companyID);
                        startActivity(i);
                    }
                }
        );
    }

    public void viewAll(){

        noEmployeeMessage.setVisibility(View.INVISIBLE);

        res = companyDatabase.viewAllEmployeeData(companyID);
        ArrayList<EmployeeDetailsClass> employeeDetailsClasses = new ArrayList<>();

        while (res.moveToNext()){
            int ID = res.getInt(0);
            String name = res.getString(2);
            String position = res.getString(3);
            String date = res.getString(4);

            EmployeeDetailsClass detailsClass = new EmployeeDetailsClass(ID, name, position, date);
            employeeDetailsClasses.add(detailsClass);
        }

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(EmployeeListView.this, R.layout.employee_listview_layout, employeeDetailsClasses);
        employeeListView.setAdapter(employeeAdapter);
    }

    public void deleteAllEmployeeData(){

        res = companyDatabase.viewAllEmployeeData(companyID);
        if(res.getCount() == 0) {
            noEmployeeMessage.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Nothing to delete.", Toast.LENGTH_SHORT).show();
        }
        else {
            if(companyDatabase.deleteAllEmployeeData(companyID)){
                viewAll();
                noEmployeeMessage.setVisibility(View.VISIBLE);
                Toast.makeText(EmployeeListView.this, "Deleted successfully.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(EmployeeListView.this, "Not deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
