package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class Coordinates {
    private double lon;
    private double lat;

    public Coordinates(double lon, double lat) {
        this.setLon(lon);
        this.setLat(lat);
    }
}
