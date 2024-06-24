package com.example.university.entity;

import java.util.List;

public class CountryList {
    private List<String> countryList;

    public CountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public List<String> getCountryList() {
        return this.countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

}
