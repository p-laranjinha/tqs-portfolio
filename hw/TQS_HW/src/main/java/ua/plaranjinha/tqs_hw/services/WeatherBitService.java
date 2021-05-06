package ua.plaranjinha.tqs_hw.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.datamodels.FullDataAirPollution;
import ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution.WBAirPollution;

import java.util.Comparator;
import java.util.Locale;

@Service
public class WeatherBitService {
    @Autowired
    Logger logger;

    @Value("${custom.api.weatherbit.key}")
    String apiKey;

    @Autowired
    RestTemplate restTemplate;

    String locationURL = "http://api.weatherbit.io/v2.0/current/airquality?city=%s&country=%s&key=%s";
    String coordsURL = "http://api.weatherbit.io/v2.0/current/airquality?lat=%f&lon=%f&key=%s";
    String locationURLh = "http://api.weatherbit.io/v2.0/history/airquality?city=%s&country=%s&key=%s";
    String coordsURLh = "http://api.weatherbit.io/v2.0/history/airquality?lat=%f&lon=%f&key=%s";
    String locationURLf = "http://api.weatherbit.io/v2.0/forecast/airquality?city=%s&country=%s&key=%s";
    String coordsURLf = "http://api.weatherbit.io/v2.0/forecast/airquality?lat=%f&lon=%f&key=%s";

    FullData getDataFromLocation(String location, String countryCode) {
        return getData(locationURL, location, countryCode);
    }

    FullData getDataFromCoords(double lat, double lon) {
        return getData(coordsURL, lat, lon);
    }

    FullData getDataFromLocationHistory(String location, String countryCode) {
        FullData result = getData(locationURLh, location, countryCode);
        result.getAir_pollution().sort(Comparator.comparing(FullDataAirPollution::getTime));
        return result;
    }

    FullData getDataFromCoordsHistory(double lat, double lon) {
        FullData result = getData(coordsURLh, lat, lon);
        result.getAir_pollution().sort(Comparator.comparing(FullDataAirPollution::getTime));
        return result;
    }

    FullData getDataFromLocationForecast(String location, String countryCode) {
        return getData(locationURLf, location, countryCode);
    }

    FullData getDataFromCoordsForecast(double lat, double lon) {
        return getData(coordsURLf, lat, lon);
    }

    FullData getData(String url, Object x, Object y) {
        logger.info(String.format(Locale.US, url, x, y, apiKey));
        WBAirPollution airPollution = restTemplate.getForObject(String.format(Locale.US, url, x, y, apiKey), WBAirPollution.class);
        if (airPollution == null || airPollution.getData().isEmpty())
            return null;
        return new FullData(airPollution);
    }
}
