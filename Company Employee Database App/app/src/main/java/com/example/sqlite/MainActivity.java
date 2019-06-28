package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingButton;
    ListView companyListView;
    AddCompanyDetails companyDetails;
    TextView emptyMessage;
    CompanyDatabase companyDatabase;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyListView = findViewById(R.id.companyListView);
        floatingButton = findViewById(R.id.companyFloatingButton);
        companyDetails = new AddCompanyDetails();
        companyDatabase = new CompanyDatabase(this);
        emptyMessage = findViewById(R.id.emptyMessage);


        res = companyDatabase.viewAllCompanyData();
        if(res.getCount() == 0)
            emptyMessage.setVisibility(View.VISIBLE);
        else
            viewAll();

        addCompanyDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.deleteAllMenu){
            deleteAllCompanyData();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        res = companyDatabase.viewAllCompanyData();
        if(res.getCount() == 0)
            emptyMessage.setVisibility(View.VISIBLE);
        else
            viewAll();
    }

    public void viewAll(){
        ArrayList<CompanyDetailsClass> companyList  = new ArrayList<>();
        res = companyDatabase.viewAllCompanyData();

        while (res.moveToNext()){
            String ID = res.getInt(0)+"";
            String a = res.getString(1);
            String b = res.getString(2);
            CompanyDetailsClass x = new CompanyDetailsClass(Integer.parseInt(ID), a, b);
            companyList.add(x);
        }

        CompanyAdapter companyAdapter = new CompanyAdapter(MainActivity.this, R.layout.company_listview_layout, companyList);
        companyListView.setAdapter(companyAdapter);

        companyListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, EmployeeListView.class);

                        CompanyDetailsClass O = (CompanyDetailsClass) parent.getItemAtPosition(position);
                        i.putExtra("companyID", O.getId());
                        startActivity(i);
                    }
                }
        );
    }

    public void addCompanyDetails(){
        floatingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AddCompanyDetails.class);
                        startActivity(i);
                        emptyMessage.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

    public void deleteAllCompanyData(){
        res = companyDatabase.viewAllCompanyData();
        if(res.getCount() == 0) {
            emptyMessage.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Nothing to delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            if(companyDatabase.deleteAllCompanyData()) {
                viewAll();
                emptyMessage.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Not deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
