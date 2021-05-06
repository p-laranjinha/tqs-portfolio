package ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value={"timestamp_local", "timestamp_utc", "datetime", "pollen_level_tree", "pollen_level_grass", "pollen_level_weed", "mold_level", "predominant_pollen_type"})
public class WBAirPollutionData {
    private int aqi;
    private double o3;
    private double so2;
    private double no2;
    private double co;
    private double pm25;
    private double pm10;
    private long ts;
}
