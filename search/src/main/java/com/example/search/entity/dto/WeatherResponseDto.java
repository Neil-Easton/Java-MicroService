import java.util.Date;
import java.util.List;

public class WeatherResponseDto {
    private String code;

    private List<Integer> cityIdList;

    private Date date;

    public WeatherResponseDto(String code, List<Integer> cityIdList, Date date) {
        this.code = code;
        this.cityIdList = cityIdList;
        this.date = date;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getCityIdList() {
        return this.cityIdList;
    }

    public void setCityIdList(List<Integer> cityIdList) {
        this.cityIdList = cityIdList;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
