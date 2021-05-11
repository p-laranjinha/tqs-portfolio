package ua.plaranjinha.tqs_hw.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.plaranjinha.tqs_hw.datamodels.FullData;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String index(
            @RequestParam(name="request", required = false) String request,
            @RequestParam(name="location", required = false) String location,
            @RequestParam(name="country_code", required = false) String countryCode,
            @RequestParam(name="lat", required = false) String lat,
            @RequestParam(name="lon", required = false) String lon,
            @RequestParam(name="type", required = false) String type,
            Model model) {
        model.addAttribute("appName", appName);

        if (request != null) switch (request) {
            case "location":
                if (location == null || countryCode == null || type == null)
                    break;
                switch (type) {
                    case "current":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/current/location/"+location+","+countryCode));
                        break;
                    case "history":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/history/location/"+location+","+countryCode));
                        break;
                    case "forecast":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/forecast/location/"+location+","+countryCode));
                        break;
                }
                break;
            case "coords":
                if (lat == null || lon == null || type == null)
                    break;
                switch (type) {
                    case "current":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/current/coords/"+lat+","+lon));
                        break;
                    case "history":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/history/coords/"+lat+","+lon));
                        break;
                    case "forecast":
                        model.addAttribute("data",
                                getRestRequest("http://localhost:8080/api/air_quality/forecast/coords/"+lat+","+lon));
                        break;
                }
                break;
            case "cached":
                model.addAttribute("data",
                        getRestRequest("http://localhost:8080/api/air_quality/cached/"));
                break;
        }

        return "index";
    }

    private String getRestRequest(String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK)
                return "404: Not found.";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(objectMapper.readTree(response.getBody()));
        } catch (Exception e) {
            return "404: Not found.";
        }
    }
}
