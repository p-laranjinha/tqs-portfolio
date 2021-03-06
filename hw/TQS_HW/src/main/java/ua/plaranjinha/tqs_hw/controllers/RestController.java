package ua.plaranjinha.tqs_hw.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.plaranjinha.tqs_hw.datamodels.FullCacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.services.CacheService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/air_quality")
public class RestController {
    @Autowired
    Logger logger;

    @Autowired
    CacheService service;

    @GetMapping("/current/location/{location},{countryCode}")
    ResponseEntity<FullData> getDataFromLocation(@PathVariable String location, @PathVariable String countryCode) {
        logger.info(String.format("REST: location(%s, %s)", location, countryCode));
        FullData data = service.getDataFromLocation(location, countryCode);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/current/coords/{lat},{lon}")
    ResponseEntity<FullData> getDataFromCoords(@PathVariable double lat, @PathVariable double lon) {
        logger.info(String.format("REST: coords(%f, %f)", lat, lon));
        FullData data = service.getDataFromCoords(lat, lon);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/history/location/{location},{countryCode}")
    ResponseEntity<FullData> getDataFromLocationHistory(@PathVariable String location, @PathVariable String countryCode) {
        logger.info(String.format("REST: locationHistory(%s, %s)", location, countryCode));
        FullData data = service.getDataFromLocationHistory(location, countryCode);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/history/coords/{lat},{lon}")
    ResponseEntity<FullData> getDataFromCoordsHistory(@PathVariable double lat, @PathVariable double lon) {
        logger.info(String.format("REST: coordsHistory(%f, %f)", lat, lon));
        FullData data = service.getDataFromCoordsHistory(lat, lon);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forecast/location/{location},{countryCode}")
    ResponseEntity<FullData> getDataFromLocationForecast(@PathVariable String location, @PathVariable String countryCode) {
        logger.info(String.format("REST: locationForecast(%s, %s)", location, countryCode));
        FullData data = service.getDataFromLocationForecast(location, countryCode);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forecast/coords/{lat},{lon}")
    ResponseEntity<FullData> getDataFromCoordsForecast(@PathVariable double lat, @PathVariable double lon) {
        logger.info(String.format("REST: coordsForecast(%f, %f)", lat, lon));
        FullData data = service.getDataFromCoordsForecast(lat, lon);
        return new ResponseEntity<>(data, data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cached")
    ResponseEntity<FullCacheData> getDataFromCache() {
        logger.info("REST: cache");
        return new ResponseEntity<>(service.getDataFromCache(), HttpStatus.OK);
    }
}
