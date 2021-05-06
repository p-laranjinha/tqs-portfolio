package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution.OWMAirPollutionList;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.geolocation.OWMGeolocation;
import ua.plaranjinha.tqs_hw.datamodels.openweathermap.airpolution.OWMAirPollution;
import ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution.WBAirPollution;
import ua.plaranjinha.tqs_hw.datamodels.weatherbit.airpolution.WBAirPollutionData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class FullData {
    private String location;
    private String country_code;
    private Coordinates coordinates;
    private List<FullDataAirPollution> air_pollution;

    public FullData(OWMGeolocation geolocation, OWMAirPollutionList airPollutionList) {
        location = geolocation.getName();
        country_code = geolocation.getCountry();
        coordinates = new Coordinates(geolocation.getLon(), geolocation.getLat());

        air_pollution = new ArrayList<>();
        for (OWMAirPollution x: airPollutionList.getList()) {
            FullDataAirPollution y = new FullDataAirPollution();
            y.setAqi(x.getMain().getAqi());
            y.setConcentrations(new Concentrations(x.getComponents()));
            y.setTime(x.getDt());
            air_pollution.add(y);
        }
    }

    public FullData(WBAirPollution airPollution) {
        location = airPollution.getCity_name();
        country_code = airPollution.getCountry_code();
        coordinates = new Coordinates(airPollution.getLon(), airPollution.getLat());

        air_pollution = new ArrayList<>();
        for (WBAirPollutionData x: airPollution.getData()) {
            FullDataAirPollution y = new FullDataAirPollution();
            y.setAqi(x.getAqi());
            y.setConcentrations(new Concentrations(x));
            if (x.getTs() == 0) {
                Calendar calendar = Calendar.getInstance();
                long now = calendar.getTime().getTime() / 1000L;
                y.setTime(now);
            } else {
                y.setTime(x.getTs());
            }
            air_pollution.add(y);
        }
    }
}
