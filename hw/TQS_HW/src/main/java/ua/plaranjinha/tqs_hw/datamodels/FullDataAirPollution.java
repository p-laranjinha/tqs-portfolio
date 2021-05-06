package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class FullDataAirPollution {
    private int aqi;
    private Concentrations concentrations;
    private long time;
}
