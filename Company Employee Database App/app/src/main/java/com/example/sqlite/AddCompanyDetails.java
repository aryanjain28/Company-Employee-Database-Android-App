package com.example.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCompanyDetails extends AppCompatActivity {

    EditText companyName;
    EditText companyWebsite;
    Button addCompanyButton;
    CompanyDatabase companyDatabase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company_details);

        companyName = findViewById(R.id.companyName);
        companyWebsite = findViewById(R.id.companyWebsite);
        addCompanyButton = findViewById(R.id.addCompanyButton);
        companyDatabase = new CompanyDatabase(this);

        companyInputs();
    }

    public void companyInputs(){
        addCompanyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = companyName.getText().toString();
                        if(name.equals(""))
                            Toast.makeText(AddCompanyDetails.this, "Please write company name.", Toast.LENGTH_SHORT).show();
                        else {
                            boolean x = companyDatabase.addCompanyDetails(
                                    name,
                                    companyWebsite.getText().toString()
                            );

                            if (x) {
                                Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
                                companyName.setText("");
                                companyWebsite.setText("");
                            } else
                                Toast.makeText(context, "Not added.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
