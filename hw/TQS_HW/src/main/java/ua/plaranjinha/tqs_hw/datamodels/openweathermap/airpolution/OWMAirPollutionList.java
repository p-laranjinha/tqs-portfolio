package ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution;

import lombok.Data;

import java.util.List;

@Data
public class OWMAirPollutionList {
    List<OWMAirPollution> list;
}
