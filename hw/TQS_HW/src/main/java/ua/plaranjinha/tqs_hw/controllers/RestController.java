package ua.plaranjinha.tqs_hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.plaranjinha.tqs_hw.datamodels.CacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullCacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.services.CacheService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/air_quality")
public class RestController {
    @Autowired
    CacheService service;

    @GetMapping("/current/location/{location},{countryCode}")
    FullData getDataFromLocation(@PathVariable String location, @PathVariable String countryCode) {
        return service.getDataFromLocation(location, countryCode);
    }

    @GetMapping("/current/coords/{lat},{lon}")
    FullData getDataFromCoords(@PathVariable double lat, @PathVariable double lon) {
        return service.getDataFromCoords(lat, lon);
    }

    @GetMapping("/history/location/{location},{countryCode}")
    FullData getDataFromLocationHistory(@PathVariable String location, @PathVariable String countryCode) {
        return service.getDataFromLocationHistory(location, countryCode);
    }

    @GetMapping("/history/coords/{lat},{lon}")
    FullData getDataFromCoordsHistory(@PathVariable double lat, @PathVariable double lon) {
        return service.getDataFromCoordsHistory(lat, lon);
    }

    @GetMapping("/forecast/location/{location},{countryCode}")
    FullData getDataFromLocationForecast(@PathVariable String location, @PathVariable String countryCode) {
        return service.getDataFromLocationForecast(location, countryCode);
    }

    @GetMapping("/forecast/coords/{lat},{lon}")
    FullData getDataFromCoordsForecast(@PathVariable double lat, @PathVariable double lon) {
        return service.getDataFromCoordsForecast(lat, lon);
    }

    @GetMapping("/cached")
    FullCacheData getDataFromCache() {
        return service.getDataFromCache();
    }
}
