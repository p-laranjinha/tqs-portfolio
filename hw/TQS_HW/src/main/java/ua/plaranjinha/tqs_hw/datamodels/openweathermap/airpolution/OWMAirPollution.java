package ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value={"coord"})
public class OWMAirPollution {
    private OWMAirPollutionMain main;
    private OWMComponents components;
    private long dt;
}
