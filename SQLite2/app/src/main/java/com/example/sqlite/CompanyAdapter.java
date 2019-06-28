package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CompanyAdapter extends ArrayAdapter<CompanyDetailsClass> {

    int resource;
    Context context;
    TextView companyName;
    TextView companyWebSite;
    TextView companyID;
    CompanyDetailsClass companyDetailsClass;
    ArrayList<CompanyDetailsClass> o;

    public CompanyAdapter(Context context, int resource, ArrayList<CompanyDetailsClass> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.o = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.company_listview_layout, parent, false);

        companyName = convertView.findViewById(R.id.companyName);
        companyID = convertView.findViewById(R.id.companyID);
        companyWebSite = convertView.findViewById(R.id.companyWebsite);

        companyDetailsClass = o.get(position);

        companyID.setText(companyDetailsClass.getId()+".");
        companyName.setText(companyDetailsClass.getName());
        companyWebSite.setText(companyDetailsClass.getWebsite());

        return convertView;
    }
}
