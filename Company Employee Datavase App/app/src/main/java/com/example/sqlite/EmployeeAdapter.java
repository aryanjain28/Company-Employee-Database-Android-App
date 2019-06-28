package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class EmployeeAdapter extends ArrayAdapter<EmployeeDetailsClass> {

    Context context;
    int resource;

    TextView employeeID_layout;
    TextView employeeName_layout;
    TextView employeePosition_layout;
    TextView employeeJoinedOn_layout;
    EmployeeDetailsClass employeeDetailsClass;
    ArrayList<EmployeeDetailsClass> objects;


    public EmployeeAdapter(Context context, int resource, ArrayList<EmployeeDetailsClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.employee_listview_layout, parent, false);

        employeeID_layout = convertView.findViewById(R.id.employeeID_layout);
        employeeName_layout = convertView.findViewById(R.id.employeeName_layout);
        employeePosition_layout = convertView.findViewById(R.id.employeePosition_layout);
        employeeJoinedOn_layout = convertView.findViewById(R.id.employeeJoinedOn_layout);

        employeeDetailsClass = objects.get(position);

        employeeID_layout.setText(employeeDetailsClass.getID()+".");
        employeeName_layout.setText(employeeDetailsClass.getName());
        employeePosition_layout.setText(employeeDetailsClass.getPosition());
        employeeJoinedOn_layout.setText(employeeDetailsClass.getDate());

        return convertView;
    }
}
