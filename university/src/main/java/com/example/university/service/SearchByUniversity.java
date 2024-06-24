package com.example.university.service;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo1.entity.University;

public class SearchByUniversity implements Callable<List<University>> {

    public final String university;

    public SearchByUniversity(String university) {
        this.university = university;
    }

    @Override
    public List<University> call() throws Exception {

        String url = "http://universities.hipolabs.com/search?country=";
        url = url + university.replace(' ', '+');

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<University>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<University>>() {
                });

        List<University> result = response.getBody();

        return result;

    }

}
