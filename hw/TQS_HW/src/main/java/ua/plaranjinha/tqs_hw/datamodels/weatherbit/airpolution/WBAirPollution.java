package ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value={"state_code", "timezone"})
public class WBAirPollution {
    private String city_name;
    private String country_code;
    private double lat;
    private double lon;
    private List<WBAirPollutionData> data;
}
