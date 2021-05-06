package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution.OWMComponents;
import ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution.WBAirPollutionData;

@Data
public class Concentrations {
    private double o3;
    private double so2;
    private double no2;
    private double co;
    private double pm2_5;
    private double pm10;

    public Concentrations(OWMComponents components) {
        o3 = components.getO3();
        so2 = components.getSo2();
        no2 = components.getNo2();
        co = components.getCo();
        pm2_5 = components.getPm2_5();
        pm10 = components.getPm10();
    }

    public Concentrations(WBAirPollutionData data) {
        o3 = data.getO3();
        so2 = data.getSo2();
        no2 = data.getNo2();
        co = data.getCo();
        pm2_5 = data.getPm25();
        pm10 = data.getPm10();
    }
}
