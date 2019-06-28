package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddEmployeeDetails extends AppCompatActivity {

    TextView employeeName;
    TextView employeePosition;
    TextView employeeJoinedYear;
    Button addEmployeeButton;
    CompanyDatabase companyDatabase;
    int companyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_details);

        Bundle COMPANY_ID = getIntent().getExtras();
        if(COMPANY_ID == null)
            return;
        else
            companyID = COMPANY_ID.getInt("companyID");

        companyDatabase = new CompanyDatabase(this);
        employeeName = findViewById(R.id.employeeName);
        employeePosition = findViewById(R.id.employeePosition);
        employeeJoinedYear = findViewById(R.id.employeeJoinedYear);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        addDetail();
    }

    public void addDetail(){

        addEmployeeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean x = companyDatabase.addEmployeeDetails(
                                companyID,
                                employeeName.getText().toString(),
                                employeePosition.getText().toString(),
                                employeeJoinedYear.getText().toString()
                                );

                        if(x) {
                            employeeName.setText("");
                            employeePosition.setText("");
                            employeeJoinedYear.setText("");
                            Toast.makeText(AddEmployeeDetails.this, "Employee added.", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(AddEmployeeDetails.this, "Employee not added.", Toast.LENGTH_SHORT).show();

                    }
                }
        );

    }
}
