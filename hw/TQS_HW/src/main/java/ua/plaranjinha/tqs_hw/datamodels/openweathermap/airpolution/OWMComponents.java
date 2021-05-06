package ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution;

import lombok.Data;

@Data
public class OWMComponents {
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm2_5;
    private double pm10;
    private double nh3;
}
