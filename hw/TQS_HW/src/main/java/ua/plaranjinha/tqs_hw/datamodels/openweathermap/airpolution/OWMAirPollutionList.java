package ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value={"coord"})
public class OWMAirPollutionList {
    List<OWMAirPollution> list;
}
