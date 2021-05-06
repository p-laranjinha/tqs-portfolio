package ua.plaranjinha.tqs_hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String index(@RequestParam(name="location", required = false) String location, @RequestParam(name="code", required = false) String countryCode, Model model) {
        model.addAttribute("appName", appName);
        if (location != null) {
            model.addAttribute("locationData",
                    restTemplate.getForObject("http://localhost:8080/api/weather/"+location+","+countryCode, String.class));
        }
        return "index";
    }
}
