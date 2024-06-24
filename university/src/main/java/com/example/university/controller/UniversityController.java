package com.example.university.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.university.entity.CountryList;
import com.example.university.entity.University;
import com.example.university.service.UniversitySearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversitySearchService universitySearchService;

    @Autowired
    public UniversityController(UniversitySearchService universitySearchService) {
        this.universitySearchService = universitySearchService;
    }

    @GetMapping
    public List<University> getAllUniversities() {
        List<University> res = universitySearchService.getAllUni();
        return res;
    }

    @GetMapping
    public List<University> getUniversitiesByCountry(@RequestBody String countryList) {

        JSONArray json = (JSONArray) new JSONObject(countryList).get("countryList");

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < json.length(); ls.add((String) json.get(i++)))
            ;

        // System.out.println(ls);

        List<University> res = universitySearchService
                .getUniversitesByCountries(ls);

        return res;

    }

}
