package com.example.university.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo1.entity.University;

@Service
public interface UniversitySearchService {

    List<University> getAllUni();

    List<University> getUniversitesByCountries(List<String> countryList);
}
