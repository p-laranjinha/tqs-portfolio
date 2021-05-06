package ua.plaranjinha.tqs_hw.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.plaranjinha.tqs_hw.datamodels.CacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullData;

@org.springframework.stereotype.Service
public class SwitcherService {

    @Autowired
    OpenWeatherMapService openWeatherMapService;

    @Autowired
    WeatherBitService weatherBitService;

    public FullData getDataFromLocation(String location, String countryCode) {
        FullData result;
        result = openWeatherMapService.getDataFromLocation(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocation(location, countryCode);
        return result;
    }

    public FullData getDataFromCoords(double lat, double lon) {
        FullData result;
        result = openWeatherMapService.getDataFromCoords(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoords(lat, lon);
        return result;
    }

    public FullData getDataFromLocationHistory(String location, String countryCode) {
        FullData result;
        result = openWeatherMapService.getDataFromLocationHistory(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocationHistory(location, countryCode);
        return result;
    }

    public FullData getDataFromCoordsHistory(double lat, double lon) {
        FullData result;
        result = openWeatherMapService.getDataFromCoordsHistory(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoordsHistory(lat, lon);
        return result;
    }

    public FullData getDataFromLocationForecast(String location, String countryCode) {
        FullData result;
        result = openWeatherMapService.getDataFromLocationForecast(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocationForecast(location, countryCode);
        return result;
    }

    public FullData getDataFromCoordsForecast(double lat, double lon) {
        FullData result;
        result = openWeatherMapService.getDataFromCoordsForecast(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoordsForecast(lat, lon);
        return result;
    }

    public CacheData getDataFromCache() {
        return null;
    }
}
