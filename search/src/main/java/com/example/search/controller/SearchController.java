package com.example.search.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SearchController {

    final private SearchServiceImpl searchServiceImpl;

    @Autowire
    public SearchController(SearchServiceImpl searchServiceImpl) {
        this.searchServiceImpl = searchServiceImpl;
    }

    @GetMapping("/weather/search")
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
     })
    public ResponseEntity<WeatherResponseDto> searchWeather(@RequestBody WeatherQueryDto query) {
        return new ResponseEntity<>(searchServiceImpl.searchWeather(query), HttpMethod.OK);
    }

    @GetMapping("/book/search")
    public ResponseEntity<Set<Book>> getMethodName(@RequestBody AuthorDto author) {
        return new ResponseEntity<>(searchServiceImpl.searchBook(author), HttpMethod.OK);
    }

    private String fallback() {
        return "Request fails. It takes long time to response";
    }
}
