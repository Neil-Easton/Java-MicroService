public interface SearchService {
    Set<Book> searchBook(AuthorDto author);

    WeatherResponseDto searchWeather(WeatherQueryDto weatherQueryDto);
}
