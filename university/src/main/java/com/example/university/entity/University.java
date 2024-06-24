package com.example.university.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class University {
    private ArrayList<String> domains;
    private String name;
    private ArrayList<String> web_pages;

    public University(ArrayList<String> domains, String name, ArrayList<String> web_pages) {
        this.domains = domains;
        this.name = name;
        this.web_pages = web_pages;
    }

    public ArrayList<String> getDomains() {
        return this.domains;
    }

    public void setDomains(ArrayList<String> domains) {
        this.domains = domains;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getWeb_pages() {
        return this.web_pages;
    }

    public void setWeb_pages(ArrayList<String> web_pages) {
        this.web_pages = web_pages;
    }

}
