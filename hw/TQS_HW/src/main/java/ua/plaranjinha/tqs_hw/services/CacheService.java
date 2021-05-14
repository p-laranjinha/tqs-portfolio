package ua.plaranjinha.tqs_hw.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.plaranjinha.tqs_hw.datamodels.*;

@org.springframework.stereotype.Service
public class CacheService {
    @Autowired
    Logger logger;

    @Autowired
    OpenWeatherMapService openWeatherMapService;

    @Autowired
    WeatherBitService weatherBitService;

    final FullDataCache currentCache = new FullDataCache();

    final FullDataCache historyCache = new FullDataCache();

    final FullDataCache forecastCache = new FullDataCache();

    int nOWMRequests = 0;
    int nWBRequests = 0;
    int nCacheRequests = 0;
    int nFailedRequests = 0;

    public FullData getDataFromLocation(String location, String countryCode) {
        if (countryCode.length() > 2) {
            logger.warn(String.format("SERV: location(%s, %s) - Invalid input", location, countryCode));
            nFailedRequests++;
            return null;
        }
        StringKey key = new StringKey(location, countryCode);
        if (currentCache.containsKey(key)) {
            CacheData cacheData = currentCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: location(%s, %s) - Cached request", location, countryCode));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                currentCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocation(location, countryCode);
        if (result == null) {
            result = weatherBitService.getDataFromLocation(location, countryCode);
            if (result != null) {
                logger.info(String.format("SERV: location(%s, %s) - WB request", location, countryCode));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: location(%s, %s) - OWM request", location, countryCode));
            nOWMRequests++;
        }

        if (result != null)
            currentCache.put(result);
        else {
            logger.warn(String.format("SERV: location(%s, %s) - Failed request", location, countryCode));
            nFailedRequests++;
        }
        return result;
    }

    public FullData getDataFromCoords(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            logger.warn(String.format("SERV: coords(%f, %f) - Invalid input", lat, lon));
            nFailedRequests++;
            return null;
        }
        DoubleKey key = new DoubleKey(lat, lon);
        if (currentCache.containsKey(key)) {
            CacheData cacheData = currentCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: coords(%f, %f) - Cached request", lat, lon));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                currentCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoords(lat, lon);
        if (result == null) {
            result = weatherBitService.getDataFromCoords(lat, lon);
            if (result != null) {
                logger.info(String.format("SERV: coords(%f, %f) - WB request", lat, lon));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: coords(%f, %f) - OWM request", lat, lon));
            nOWMRequests++;
        }

        if (result != null)
            currentCache.put(result);
        else {
            logger.warn(String.format("SERV: coords(%f, %f) - Failed request", lat, lon));
            nFailedRequests++;
        }
        return result;
    }

    public FullData getDataFromLocationHistory(String location, String countryCode) {
        if (countryCode.length() > 2) {
            logger.warn(String.format("SERV: locationHistory(%s, %s) - Invalid input", location, countryCode));
            nFailedRequests++;
            return null;
        }
        StringKey key = new StringKey(location, countryCode);
        if (historyCache.containsKey(key)) {
            CacheData cacheData = historyCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: locationHistory(%s, %s) - Cached request", location, countryCode));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                historyCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocationHistory(location, countryCode);
        if (result == null) {
            result = weatherBitService.getDataFromLocationHistory(location, countryCode);
            if (result != null) {
                logger.info(String.format("SERV: locationHistory(%s, %s) - WB request", location, countryCode));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: location(%s, %s) - OWM request", location, countryCode));
            nOWMRequests++;
        }

        if (result != null)
            historyCache.put(result);
        else {
            logger.warn(String.format("SERV: locationHistory(%s, %s) - Failed request", location, countryCode));
            nFailedRequests++;
        }
        return result;
    }

    public FullData getDataFromCoordsHistory(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            logger.warn(String.format("SERV: coordsHistory(%f, %f) - Invalid input", lat, lon));
            nFailedRequests++;
            return null;
        }
        DoubleKey key = new DoubleKey(lat, lon);
        if (historyCache.containsKey(key)) {
            CacheData cacheData = historyCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: coordsHistory(%f, %f) - Cached request", lat, lon));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                historyCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoordsHistory(lat, lon);
        if (result == null) {
            result = weatherBitService.getDataFromCoordsHistory(lat, lon);
            if (result != null) {
                logger.info(String.format("SERV: coordsHistory(%f, %f) - WB request", lat, lon));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: coordsHistory(%f, %f) - OWM request", lat, lon));
            nOWMRequests++;
        }

        if (result != null)
            historyCache.put(result);
        else {
            logger.warn(String.format("SERV: coordsHistory(%f, %f) - Failed request", lat, lon));
            nFailedRequests++;
        }
        return result;
    }

    public FullData getDataFromLocationForecast(String location, String countryCode) {
        if (countryCode.length() > 2) {
            logger.warn(String.format("SERV: locationForecast(%s, %s) - Invalid input", location, countryCode));
            nFailedRequests++;
            return null;
        }
        StringKey key = new StringKey(location, countryCode);
        if (forecastCache.containsKey(key)) {
            CacheData cacheData = forecastCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: locationForecast(%s, %s) - Cached request", location, countryCode));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                forecastCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromLocationForecast(location, countryCode);
        if (result == null) {
            result = weatherBitService.getDataFromLocationForecast(location, countryCode);
            if (result != null) {
                logger.info(String.format("SERV: locationForecast(%s, %s) - WB request", location, countryCode));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: locationForecast(%s, %s) - OWM request", location, countryCode));
            nOWMRequests++;
        }

        if (result != null)
            forecastCache.put(result);
        else {
            logger.warn(String.format("SERV: locationForecast(%s, %s) - Failed request", location, countryCode));
            nFailedRequests++;
        }
        return result;
    }

    public FullData getDataFromCoordsForecast(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            logger.warn(String.format("SERV: coordsForecast(%f, %f) - Invalid input", lat, lon));
            nFailedRequests++;
            return null;
        }
        DoubleKey key = new DoubleKey(lat, lon);
        if (forecastCache.containsKey(key)) {
            CacheData cacheData = forecastCache.get(key);
            if (cacheData != null) {
                logger.info(String.format("SERV: coordsForecast(%f, %f) - Cached request", lat, lon));
                nCacheRequests++;
                return cacheData.getData();
            } else {
                forecastCache.remove(key);
            }
        }

        FullData result;
        result = openWeatherMapService.getDataFromCoordsForecast(lat, lon);
        if (result == null) {
            result = weatherBitService.getDataFromCoordsForecast(lat, lon);
            if (result != null) {
                logger.info(String.format("SERV: coordsForecast(%f, %f) - WB request", lat, lon));
                nWBRequests++;
            }
        }
        else {
            logger.info(String.format("SERV: coordsForecast(%f, %f) - OWM request", lat, lon));
            nOWMRequests++;
        }

        if (result != null)
            forecastCache.put(result);
        else {
            logger.warn(String.format("SERV: coordsForecast(%f, %f) - Failed request", lat, lon));
            nFailedRequests++;
        }
        return result;
    }

    public FullCacheData getDataFromCache() {
        FullCacheData fullCacheData = new FullCacheData();
        fullCacheData.setOwm_requests(nOWMRequests);
        fullCacheData.setWb_requests(nWBRequests);
        fullCacheData.setCache_requests(nCacheRequests);
        fullCacheData.setFailed_requests(nFailedRequests);
        fullCacheData.setCurrent(currentCache.getAll());
        fullCacheData.setHistory(historyCache.getAll());
        fullCacheData.setForecast(forecastCache.getAll());
        logger.info("SERV: cache");
        return fullCacheData;
    }
}
