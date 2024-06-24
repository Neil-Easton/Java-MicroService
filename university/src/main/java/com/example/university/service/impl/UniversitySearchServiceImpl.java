package com.example.university.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo1.service.SearchByUniversity;
import com.example.demo1.service.UniversitySearchService;
import com.example.demo1.entity.University;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class UniversitySearchServiceImpl implements UniversitySearchService {
    public List<University> getAllUni() {
        final String url = "http://universities.hipolabs.com/search";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<University>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<University>>() {
                });

        List<University> result = response.getBody();
        // for (University uni : result) {
        // System.out.println(uni.getName() + " " + uni.getWeb_pages() + " " +
        // uni.getDomains());
        // }
        return result;
    }

    public List<University> getUniversitesByCountries(List<String> countryList) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<List<University>>> taskList = new ArrayList<>();

        for (String uni : countryList) {
            SearchByUniversity searchByUniversity = new SearchByUniversity(uni);

            Future<List<University>> task = executorService.submit(searchByUniversity);

            taskList.add(task);

        }

        List<University> res = new ArrayList<>();

        for (Future<List<University>> future : taskList) {
            try {
                List<University> subRes = future.get();
                res.addAll(subRes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return res;

    }
}
