package com.example.sqlite;

public class CompanyDetailsClass {
    public  String name;
    public String website;
    public int id;

    public CompanyDetailsClass(int id, String name, String website) {
        this.name = name;
        this.website = website;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
