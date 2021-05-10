package ua.plaranjinha.tqs_hw.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution.OWMAirPollutionList;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.geolocation.OWMGeolocation;

import java.util.Calendar;

@Service
public class OpenWeatherMapService {
    @Autowired
    Logger logger;

    @Autowired
    Calendar calendar;

    @Value("${custom.api.openweathermap.key}")
    String apiKey;

    @Autowired
    RestTemplate restTemplate;

    final String geoURL = "http://api.openweathermap.org/geo/1.0/direct?q=%s,%s&limit=1&appid=%s";
    final String geoURLr = "http://api.openweathermap.org/geo/1.0/reverse?lat=%f&lon=%f&limit=1&appid=%s";
    final String airURL = "http://api.openweathermap.org/data/2.5/air_pollution?lat=%f&lon=%f&appid=%s";
    final String airURLh = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat=%f&lon=%f&start=%d&end=%d&appid=%s";
    final String airURLf = "http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=%f&lon=%f&appid=%s";

    FullData getDataFromLocation(String location, String countryCode) {
        OWMGeolocation geolocation = getGeolocation(geoURL, location, countryCode);
        if (geolocation == null)
            return null;
        return getData(geolocation);
    }

    FullData getDataFromCoords(double lat, double lon) {
        OWMGeolocation geolocation = getGeolocation(geoURLr, lat, lon);
        if (geolocation == null)
            return null;
        return getData(geolocation);
    }

    FullData getDataFromLocationHistory(String location, String countryCode) {
        OWMGeolocation geolocation = getGeolocation(geoURL, location, countryCode);
        if (geolocation == null)
            return null;
        return getDataHistory(geolocation);
    }

    FullData getDataFromCoordsHistory(double lat, double lon) {
        OWMGeolocation geolocation = getGeolocation(geoURLr, lat, lon);
        if (geolocation == null)
            return null;
        return getDataHistory(geolocation);
    }

    FullData getDataFromLocationForecast(String location, String countryCode) {
        OWMGeolocation geolocation = getGeolocation(geoURL, location, countryCode);
        if (geolocation == null)
            return null;
        return getDataForecast(geolocation);
    }

    FullData getDataFromCoordsForecast(double lat, double lon) {
        OWMGeolocation geolocation = getGeolocation(geoURLr, lat, lon);
        if (geolocation == null)
            return null;
        return getDataForecast(geolocation);
    }

    private OWMGeolocation getGeolocation(String url, Object x, Object y) {
        //logger.info(String.format(url, x, y, apiKey));
        OWMGeolocation[] geolocationList = restTemplate.getForObject(String.format(url, x, y, apiKey), OWMGeolocation[].class);
        if (geolocationList == null || geolocationList.length == 0)
            return null;
        return geolocationList[0];
    }

    private FullData getData(OWMGeolocation geolocation) {
        //logger.info(String.format(airURL, geolocation.getLat(), geolocation.getLon(), apiKey));
        OWMAirPollutionList airPollutionList = restTemplate.getForObject(String.format(airURL, geolocation.getLat(), geolocation.getLon(), apiKey), OWMAirPollutionList.class);

        if (airPollutionList == null || airPollutionList.getList().isEmpty())
            return null;
        return new FullData(geolocation, airPollutionList);
    }

    private FullData getDataHistory(OWMGeolocation geolocation) {
        long end = calendar.getTime().getTime() / 1000L;
        calendar.add(Calendar.HOUR, -72);
        long start = calendar.getTime().getTime() / 1000L;
        calendar.add(Calendar.HOUR, 72);

        //logger.info(String.format(airURLh, geolocation.getLat(), geolocation.getLon(), start, end, apiKey));
        OWMAirPollutionList airPollutionList = restTemplate.getForObject(String.format(airURLh, geolocation.getLat(), geolocation.getLon(), start, end, apiKey), OWMAirPollutionList.class);

        if (airPollutionList == null || airPollutionList.getList().isEmpty())
            return null;
        return new FullData(geolocation, airPollutionList);
    }

    private FullData getDataForecast(OWMGeolocation geolocation) {
        //logger.info(String.format(airURLf, geolocation.getLat(), geolocation.getLon(), apiKey));
        OWMAirPollutionList airPollutionList = restTemplate.getForObject(String.format(airURLf, geolocation.getLat(), geolocation.getLon(), apiKey), OWMAirPollutionList.class);

        if (airPollutionList == null || airPollutionList.getList().isEmpty())
            return null;
        long now = calendar.getTime().getTime() / 1000L;
        airPollutionList.getList().removeIf(x -> (x.getDt() < now));
        airPollutionList.setList(airPollutionList.getList().subList(0, 72));
        return new FullData(geolocation, airPollutionList);
    }
}
