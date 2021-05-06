package ua.plaranjinha.tqs_hw.datamodels.openweathermap.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties("local_names")
public class OWMGeolocation {
    private String name;
    private double lat;
    private double lon;
    private String country;
}
