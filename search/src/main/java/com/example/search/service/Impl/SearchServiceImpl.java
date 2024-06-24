import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class SearchServiceImpl implements SearchService {
    
    public Set<Book> searchBook(AuthorDto authorDto) {
        String uri = "http://book/library/authors{" + authorDto.getFirstName + "}/{" + authorDto.getLastName + "}";

        ResponseEntity<Set<Book>> libraryResponse =
        restTemplate.exchange(uri,
                    HttpMethod.GET, null, new ParameterizedTypeReference<Set<Book>>() {
            });
        Set<Book> books = libraryResponse.getBody();
        return books;
    }

    public WeatherResponseDto searchWeather(WeatherQueryDto weatherQueryDto) {
        String uri = "http://details/details";
        Map<String, ?> param = new HashMap<>();
        param.put("city", weatherQueryDto.getCity());

        ResponseEntity<List<Integer>> weatherResponse = 
        restTemplate.exchange(uri, 
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Integer>>() {
            }, param);
        List<Integer> idList = weatherResponse.getBody();
        
        return new WeatherResponseDto(weatherQueryDto.getCity(), idList, new Date());
    }

}
