package ua.plaranjinha.tqs_hw.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.plaranjinha.tqs_hw.datamodels.*;

@org.springframework.stereotype.Service
public class CacheService {
    @Autowired
    OpenWeatherMapService openWeatherMapService;

    @Autowired
    WeatherBitService weatherBitService;

    FullDataCache currentCache = new FullDataCache();

    FullDataCache historyCache = new FullDataCache();

    FullDataCache forecastCache = new FullDataCache();

    public FullData getDataFromLocation(String location, String countryCode) {
        StringKey key = new StringKey(location, countryCode);
        if (currentCache.containsKey(key)) {
            CacheData cacheData = currentCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocation(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocation(location, countryCode);

        if (result != null)
            currentCache.put(result);
        return result;
    }

    public FullData getDataFromCoords(double lat, double lon) {
        DoubleKey key = new DoubleKey(lat, lon);
        if (currentCache.containsKey(key)) {
            CacheData cacheData = currentCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoords(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoords(lat, lon);

        if (result != null)
            currentCache.put(result);
        return result;
    }

    public FullData getDataFromLocationHistory(String location, String countryCode) {
        StringKey key = new StringKey(location, countryCode);
        if (historyCache.containsKey(key)) {
            CacheData cacheData = historyCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocationHistory(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocationHistory(location, countryCode);

        if (result != null)
            historyCache.put(result);
        return result;
    }

    public FullData getDataFromCoordsHistory(double lat, double lon) {
        DoubleKey key = new DoubleKey(lat, lon);
        if (historyCache.containsKey(key)) {
            CacheData cacheData = historyCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoordsHistory(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoordsHistory(lat, lon);

        if (result != null)
            historyCache.put(result);
        return result;
    }

    public FullData getDataFromLocationForecast(String location, String countryCode) {
        StringKey key = new StringKey(location, countryCode);
        if (forecastCache.containsKey(key)) {
            CacheData cacheData = forecastCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocationForecast(location, countryCode);
        if (result == null)
            result = weatherBitService.getDataFromLocationForecast(location, countryCode);

        if (result != null)
            forecastCache.put(result);
        return result;
    }

    public FullData getDataFromCoordsForecast(double lat, double lon) {
        DoubleKey key = new DoubleKey(lat, lon);
        if (forecastCache.containsKey(key)) {
            CacheData cacheData = forecastCache.get(key);
            if (cacheData == null)
                return null;
            return cacheData.getData();
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoordsForecast(lat, lon);
        if (result == null)
            result = weatherBitService.getDataFromCoordsForecast(lat, lon);

        if (result != null)
            forecastCache.put(result);
        return result;
    }

    public FullCacheData getDataFromCache() {
        FullCacheData fullCacheData = new FullCacheData();
        fullCacheData.setCurrent(currentCache.getAll());
        fullCacheData.setHistory(historyCache.getAll());
        fullCacheData.setForecast(forecastCache.getAll());
        return fullCacheData;
    }
}