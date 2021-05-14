package ua.plaranjinha.tqs_hw.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import ua.plaranjinha.tqs_hw.TqsHwApplication;
import ua.plaranjinha.tqs_hw.datamodels.CacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullCacheData;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.services.CacheService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.Locale;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(RestController.class)
class RestControllerIT {
    @TestConfiguration
    static class TestConfigs {
        @Bean
        public Logger logger() {
            return LoggerFactory.getLogger(TqsHwApplication.class);
        }
    }

    @Autowired
    MockMvc mvc;

    @MockBean
    CacheService cacheService;

    private final static String currentJson = "{\"location\":\"London\",\"country_code\":\"GB\",\"coordinates\":{\"lon\":-0.09184,\"lat\":51.51279},\"air_pollution\":[{\"aqi\":30,\"concentrations\":{\"o3\":65.6247,\"so2\":1.6354,\"no2\":18.7214,\"co\":310.004,\"pm2_5\":2.98503,\"pm10\":8.33251},\"time\":1620588231}]}";
    private final static String historyJson = "{\"location\":\"London\",\"country_code\":\"GB\",\"coordinates\":{\"lon\":-0.1257,\"lat\":51.5085},\"air_pollution\":[{\"aqi\":1,\"concentrations\":{\"o3\":73.67,\"so2\":5.66,\"no2\":12.85,\"co\":213.62,\"pm2_5\":1.14,\"pm10\":1.44},\"time\":1620331200},{\"aqi\":1,\"concentrations\":{\"o3\":66.52,\"so2\":5.36,\"no2\":13.2,\"co\":213.62,\"pm2_5\":1.0,\"pm10\":1.32},\"time\":1620334800},{\"aqi\":1,\"concentrations\":{\"o3\":63.66,\"so2\":4.95,\"no2\":13.02,\"co\":213.62,\"pm2_5\":0.94,\"pm10\":1.29},\"time\":1620338400},{\"aqi\":1,\"concentrations\":{\"o3\":67.95,\"so2\":4.05,\"no2\":10.11,\"co\":210.29,\"pm2_5\":0.78,\"pm10\":1.17},\"time\":1620342000},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":3.52,\"no2\":7.28,\"co\":208.62,\"pm2_5\":0.7,\"pm10\":1.13},\"time\":1620345600},{\"aqi\":1,\"concentrations\":{\"o3\":72.96,\"so2\":3.22,\"no2\":5.91,\"co\":208.62,\"pm2_5\":0.73,\"pm10\":1.17},\"time\":1620349200},{\"aqi\":1,\"concentrations\":{\"o3\":70.1,\"so2\":3.4,\"no2\":6.17,\"co\":208.62,\"pm2_5\":0.92,\"pm10\":1.41},\"time\":1620352800},{\"aqi\":1,\"concentrations\":{\"o3\":66.52,\"so2\":3.55,\"no2\":6.51,\"co\":208.62,\"pm2_5\":1.09,\"pm10\":1.65},\"time\":1620356400},{\"aqi\":1,\"concentrations\":{\"o3\":64.37,\"so2\":3.61,\"no2\":6.6,\"co\":210.29,\"pm2_5\":1.14,\"pm10\":1.8},\"time\":1620360000},{\"aqi\":1,\"concentrations\":{\"o3\":62.94,\"so2\":3.87,\"no2\":7.2,\"co\":210.29,\"pm2_5\":1.1,\"pm10\":1.89},\"time\":1620363600},{\"aqi\":1,\"concentrations\":{\"o3\":60.8,\"so2\":4.71,\"no2\":10.2,\"co\":216.96,\"pm2_5\":1.23,\"pm10\":2.14},\"time\":1620367200},{\"aqi\":1,\"concentrations\":{\"o3\":59.37,\"so2\":5.54,\"no2\":12.68,\"co\":223.64,\"pm2_5\":1.37,\"pm10\":2.38},\"time\":1620370800},{\"aqi\":1,\"concentrations\":{\"o3\":61.51,\"so2\":6.02,\"no2\":11.65,\"co\":223.64,\"pm2_5\":1.38,\"pm10\":2.46},\"time\":1620374400},{\"aqi\":1,\"concentrations\":{\"o3\":65.8,\"so2\":5.84,\"no2\":8.91,\"co\":220.3,\"pm2_5\":1.34,\"pm10\":2.45},\"time\":1620378000},{\"aqi\":1,\"concentrations\":{\"o3\":78.68,\"so2\":4.11,\"no2\":5.48,\"co\":211.95,\"pm2_5\":1.02,\"pm10\":1.83},\"time\":1620381600},{\"aqi\":2,\"concentrations\":{\"o3\":89.41,\"so2\":3.49,\"no2\":4.58,\"co\":208.62,\"pm2_5\":0.79,\"pm10\":1.33},\"time\":1620385200},{\"aqi\":2,\"concentrations\":{\"o3\":92.98,\"so2\":3.16,\"no2\":4.58,\"co\":206.95,\"pm2_5\":0.73,\"pm10\":1.17},\"time\":1620388800},{\"aqi\":2,\"concentrations\":{\"o3\":92.98,\"so2\":3.01,\"no2\":4.67,\"co\":206.95,\"pm2_5\":0.8,\"pm10\":1.22},\"time\":1620392400},{\"aqi\":2,\"concentrations\":{\"o3\":91.55,\"so2\":3.31,\"no2\":5.1,\"co\":206.95,\"pm2_5\":0.91,\"pm10\":1.26},\"time\":1620396000},{\"aqi\":2,\"concentrations\":{\"o3\":89.41,\"so2\":3.64,\"no2\":6.0,\"co\":210.29,\"pm2_5\":1.08,\"pm10\":1.37},\"time\":1620399600},{\"aqi\":2,\"concentrations\":{\"o3\":83.69,\"so2\":4.83,\"no2\":9.94,\"co\":216.96,\"pm2_5\":1.32,\"pm10\":1.65},\"time\":1620403200},{\"aqi\":1,\"concentrations\":{\"o3\":76.53,\"so2\":6.32,\"no2\":15.42,\"co\":220.3,\"pm2_5\":1.54,\"pm10\":1.93},\"time\":1620406800},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":7.45,\"no2\":18.34,\"co\":223.64,\"pm2_5\":1.81,\"pm10\":2.28},\"time\":1620410400},{\"aqi\":1,\"concentrations\":{\"o3\":60.8,\"so2\":9.89,\"no2\":26.39,\"co\":233.65,\"pm2_5\":2.65,\"pm10\":3.32},\"time\":1620414000},{\"aqi\":1,\"concentrations\":{\"o3\":43.99,\"so2\":12.4,\"no2\":39.76,\"co\":253.68,\"pm2_5\":4.04,\"pm10\":5.0},\"time\":1620417600},{\"aqi\":2,\"concentrations\":{\"o3\":32.19,\"so2\":12.76,\"no2\":49.35,\"co\":267.03,\"pm2_5\":5.12,\"pm10\":6.31},\"time\":1620421200},{\"aqi\":2,\"concentrations\":{\"o3\":27.18,\"so2\":12.04,\"no2\":53.47,\"co\":270.37,\"pm2_5\":5.66,\"pm10\":7.0},\"time\":1620424800},{\"aqi\":2,\"concentrations\":{\"o3\":29.68,\"so2\":10.37,\"no2\":50.72,\"co\":267.03,\"pm2_5\":5.47,\"pm10\":6.84},\"time\":1620428400},{\"aqi\":2,\"concentrations\":{\"o3\":38.27,\"so2\":8.46,\"no2\":40.78,\"co\":257.02,\"pm2_5\":4.9,\"pm10\":6.22},\"time\":1620432000},{\"aqi\":1,\"concentrations\":{\"o3\":48.64,\"so2\":6.91,\"no2\":28.79,\"co\":240.33,\"pm2_5\":4.12,\"pm10\":5.26},\"time\":1620435600},{\"aqi\":1,\"concentrations\":{\"o3\":57.94,\"so2\":6.38,\"no2\":18.85,\"co\":223.64,\"pm2_5\":3.37,\"pm10\":4.3},\"time\":1620439200},{\"aqi\":1,\"concentrations\":{\"o3\":65.09,\"so2\":6.56,\"no2\":13.2,\"co\":213.62,\"pm2_5\":2.96,\"pm10\":3.76},\"time\":1620442800},{\"aqi\":1,\"concentrations\":{\"o3\":77.25,\"so2\":6.14,\"no2\":8.14,\"co\":206.95,\"pm2_5\":1.93,\"pm10\":2.45},\"time\":1620446400},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":5.66,\"no2\":7.45,\"co\":206.95,\"pm2_5\":1.66,\"pm10\":1.94},\"time\":1620450000},{\"aqi\":1,\"concentrations\":{\"o3\":59.37,\"so2\":5.66,\"no2\":10.54,\"co\":213.62,\"pm2_5\":2.19,\"pm10\":2.39},\"time\":1620453600},{\"aqi\":1,\"concentrations\":{\"o3\":55.08,\"so2\":5.01,\"no2\":12.85,\"co\":213.62,\"pm2_5\":2.42,\"pm10\":2.56},\"time\":1620457200},{\"aqi\":1,\"concentrations\":{\"o3\":53.64,\"so2\":4.89,\"no2\":12.85,\"co\":208.62,\"pm2_5\":2.15,\"pm10\":2.28},\"time\":1620460800},{\"aqi\":1,\"concentrations\":{\"o3\":52.21,\"so2\":4.47,\"no2\":11.48,\"co\":201.94,\"pm2_5\":1.71,\"pm10\":1.81},\"time\":1620464400},{\"aqi\":1,\"concentrations\":{\"o3\":53.64,\"so2\":3.79,\"no2\":8.65,\"co\":193.6,\"pm2_5\":1.23,\"pm10\":1.33},\"time\":1620468000},{\"aqi\":1,\"concentrations\":{\"o3\":51.5,\"so2\":3.82,\"no2\":7.88,\"co\":186.92,\"pm2_5\":1.4,\"pm10\":1.59},\"time\":1620471600},{\"aqi\":1,\"concentrations\":{\"o3\":50.07,\"so2\":4.11,\"no2\":8.23,\"co\":183.58,\"pm2_5\":2.7,\"pm10\":3.34},\"time\":1620475200},{\"aqi\":1,\"concentrations\":{\"o3\":55.08,\"so2\":3.76,\"no2\":6.26,\"co\":181.91,\"pm2_5\":3.58,\"pm10\":5.5},\"time\":1620478800},{\"aqi\":1,\"concentrations\":{\"o3\":62.23,\"so2\":3.52,\"no2\":5.27,\"co\":181.91,\"pm2_5\":3.69,\"pm10\":7.42},\"time\":1620482400},{\"aqi\":1,\"concentrations\":{\"o3\":67.23,\"so2\":3.22,\"no2\":4.88,\"co\":181.91,\"pm2_5\":3.43,\"pm10\":7.73},\"time\":1620486000},{\"aqi\":1,\"concentrations\":{\"o3\":69.38,\"so2\":2.98,\"no2\":4.97,\"co\":181.91,\"pm2_5\":2.89,\"pm10\":6.55},\"time\":1620489600},{\"aqi\":1,\"concentrations\":{\"o3\":70.81,\"so2\":3.07,\"no2\":5.4,\"co\":181.91,\"pm2_5\":2.66,\"pm10\":5.93},\"time\":1620493200},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":3.46,\"no2\":6.08,\"co\":181.91,\"pm2_5\":2.9,\"pm10\":6.39},\"time\":1620496800},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":4.11,\"no2\":7.63,\"co\":181.91,\"pm2_5\":3.27,\"pm10\":6.93},\"time\":1620500400},{\"aqi\":1,\"concentrations\":{\"o3\":72.96,\"so2\":4.71,\"no2\":9.08,\"co\":181.91,\"pm2_5\":3.63,\"pm10\":7.29},\"time\":1620504000},{\"aqi\":1,\"concentrations\":{\"o3\":74.39,\"so2\":5.25,\"no2\":10.2,\"co\":183.58,\"pm2_5\":3.91,\"pm10\":7.63},\"time\":1620507600},{\"aqi\":1,\"concentrations\":{\"o3\":75.82,\"so2\":5.36,\"no2\":10.63,\"co\":183.58,\"pm2_5\":4.03,\"pm10\":7.7},\"time\":1620511200},{\"aqi\":1,\"concentrations\":{\"o3\":76.53,\"so2\":4.95,\"no2\":9.6,\"co\":183.58,\"pm2_5\":4.0,\"pm10\":7.52},\"time\":1620514800},{\"aqi\":1,\"concentrations\":{\"o3\":75.82,\"so2\":4.71,\"no2\":8.57,\"co\":181.91,\"pm2_5\":3.85,\"pm10\":7.17},\"time\":1620518400},{\"aqi\":1,\"concentrations\":{\"o3\":73.67,\"so2\":4.41,\"no2\":7.8,\"co\":181.91,\"pm2_5\":3.45,\"pm10\":6.48},\"time\":1620522000},{\"aqi\":1,\"concentrations\":{\"o3\":70.81,\"so2\":4.23,\"no2\":7.28,\"co\":181.91,\"pm2_5\":3.02,\"pm10\":5.65},\"time\":1620525600},{\"aqi\":1,\"concentrations\":{\"o3\":67.95,\"so2\":4.23,\"no2\":7.2,\"co\":181.91,\"pm2_5\":2.67,\"pm10\":4.95},\"time\":1620529200},{\"aqi\":1,\"concentrations\":{\"o3\":65.09,\"so2\":4.29,\"no2\":7.03,\"co\":181.91,\"pm2_5\":2.4,\"pm10\":4.37},\"time\":1620532800},{\"aqi\":1,\"concentrations\":{\"o3\":62.94,\"so2\":4.23,\"no2\":6.86,\"co\":181.91,\"pm2_5\":2.15,\"pm10\":3.86},\"time\":1620536400},{\"aqi\":1,\"concentrations\":{\"o3\":60.08,\"so2\":4.35,\"no2\":8.05,\"co\":183.58,\"pm2_5\":1.94,\"pm10\":3.38},\"time\":1620540000},{\"aqi\":1,\"concentrations\":{\"o3\":57.94,\"so2\":4.53,\"no2\":9.51,\"co\":183.58,\"pm2_5\":1.7,\"pm10\":2.83},\"time\":1620543600},{\"aqi\":1,\"concentrations\":{\"o3\":58.65,\"so2\":4.41,\"no2\":9.25,\"co\":181.91,\"pm2_5\":1.45,\"pm10\":2.35},\"time\":1620547200},{\"aqi\":1,\"concentrations\":{\"o3\":60.08,\"so2\":4.23,\"no2\":7.88,\"co\":178.58,\"pm2_5\":1.24,\"pm10\":1.97},\"time\":1620550800},{\"aqi\":1,\"concentrations\":{\"o3\":61.51,\"so2\":3.93,\"no2\":6.34,\"co\":176.91,\"pm2_5\":1.13,\"pm10\":1.74},\"time\":1620554400},{\"aqi\":1,\"concentrations\":{\"o3\":62.23,\"so2\":3.73,\"no2\":5.83,\"co\":175.24,\"pm2_5\":1.15,\"pm10\":1.77},\"time\":1620558000},{\"aqi\":1,\"concentrations\":{\"o3\":62.94,\"so2\":3.37,\"no2\":5.44,\"co\":175.24,\"pm2_5\":1.24,\"pm10\":1.9},\"time\":1620561600},{\"aqi\":1,\"concentrations\":{\"o3\":65.09,\"so2\":3.13,\"no2\":5.01,\"co\":173.57,\"pm2_5\":1.28,\"pm10\":1.96},\"time\":1620565200},{\"aqi\":1,\"concentrations\":{\"o3\":65.8,\"so2\":3.34,\"no2\":5.27,\"co\":173.57,\"pm2_5\":1.33,\"pm10\":2.01},\"time\":1620568800},{\"aqi\":1,\"concentrations\":{\"o3\":65.8,\"so2\":3.43,\"no2\":5.83,\"co\":175.24,\"pm2_5\":1.39,\"pm10\":2.13},\"time\":1620572400},{\"aqi\":1,\"concentrations\":{\"o3\":62.94,\"so2\":3.99,\"no2\":8.05,\"co\":176.91,\"pm2_5\":1.54,\"pm10\":2.36},\"time\":1620576000},{\"aqi\":1,\"concentrations\":{\"o3\":57.22,\"so2\":5.01,\"no2\":12.17,\"co\":180.24,\"pm2_5\":1.79,\"pm10\":2.69},\"time\":1620579600},{\"aqi\":1,\"concentrations\":{\"o3\":53.64,\"so2\":6.2,\"no2\":15.25,\"co\":183.58,\"pm2_5\":1.97,\"pm10\":2.88},\"time\":1620583200},{\"aqi\":1,\"concentrations\":{\"o3\":49.35,\"so2\":7.99,\"no2\":18.51,\"co\":191.93,\"pm2_5\":2.15,\"pm10\":2.97},\"time\":1620586800}]}";
    private final static String forecastJson = "{\"location\":\"London\",\"country_code\":\"GB\",\"coordinates\":{\"lon\":-0.1257,\"lat\":51.5085},\"air_pollution\":[{\"aqi\":1,\"concentrations\":{\"o3\":42.56,\"so2\":10.13,\"no2\":22.62,\"co\":200.27,\"pm2_5\":2.48,\"pm10\":3.24},\"time\":1620590400},{\"aqi\":1,\"concentrations\":{\"o3\":37.91,\"so2\":10.37,\"no2\":24.33,\"co\":205.28,\"pm2_5\":2.69,\"pm10\":3.38},\"time\":1620594000},{\"aqi\":1,\"concentrations\":{\"o3\":35.76,\"so2\":9.42,\"no2\":22.96,\"co\":203.61,\"pm2_5\":2.68,\"pm10\":3.21},\"time\":1620597600},{\"aqi\":1,\"concentrations\":{\"o3\":35.41,\"so2\":8.23,\"no2\":20.39,\"co\":200.27,\"pm2_5\":2.53,\"pm10\":2.91},\"time\":1620601200},{\"aqi\":1,\"concentrations\":{\"o3\":35.05,\"so2\":8.46,\"no2\":18.85,\"co\":196.93,\"pm2_5\":2.54,\"pm10\":2.86},\"time\":1620604800},{\"aqi\":1,\"concentrations\":{\"o3\":32.9,\"so2\":9.54,\"no2\":18.68,\"co\":196.93,\"pm2_5\":3.12,\"pm10\":3.45},\"time\":1620608400},{\"aqi\":1,\"concentrations\":{\"o3\":28.97,\"so2\":9.66,\"no2\":20.05,\"co\":195.27,\"pm2_5\":4.83,\"pm10\":5.25},\"time\":1620612000},{\"aqi\":1,\"concentrations\":{\"o3\":30.76,\"so2\":8.7,\"no2\":20.22,\"co\":195.27,\"pm2_5\":5.48,\"pm10\":5.93},\"time\":1620615600},{\"aqi\":1,\"concentrations\":{\"o3\":48.64,\"so2\":7.57,\"no2\":15.59,\"co\":196.93,\"pm2_5\":3.67,\"pm10\":4.07},\"time\":1620619200},{\"aqi\":1,\"concentrations\":{\"o3\":57.22,\"so2\":7.87,\"no2\":12.85,\"co\":198.6,\"pm2_5\":2.37,\"pm10\":2.8},\"time\":1620622800},{\"aqi\":1,\"concentrations\":{\"o3\":55.08,\"so2\":8.46,\"no2\":14.74,\"co\":203.61,\"pm2_5\":2.4,\"pm10\":3.08},\"time\":1620626400},{\"aqi\":1,\"concentrations\":{\"o3\":50.78,\"so2\":8.23,\"no2\":16.97,\"co\":205.28,\"pm2_5\":2.82,\"pm10\":3.7},\"time\":1620630000},{\"aqi\":1,\"concentrations\":{\"o3\":48.64,\"so2\":8.94,\"no2\":17.82,\"co\":205.28,\"pm2_5\":3.31,\"pm10\":4.4},\"time\":1620633600},{\"aqi\":1,\"concentrations\":{\"o3\":53.64,\"so2\":7.99,\"no2\":12.17,\"co\":205.28,\"pm2_5\":3.25,\"pm10\":4.23},\"time\":1620637200},{\"aqi\":1,\"concentrations\":{\"o3\":65.8,\"so2\":5.6,\"no2\":6.86,\"co\":203.61,\"pm2_5\":2.96,\"pm10\":3.88},\"time\":1620640800},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":4.71,\"no2\":4.84,\"co\":201.94,\"pm2_5\":2.68,\"pm10\":3.68},\"time\":1620644400},{\"aqi\":1,\"concentrations\":{\"o3\":75.82,\"so2\":4.17,\"no2\":3.9,\"co\":198.6,\"pm2_5\":2.65,\"pm10\":3.8},\"time\":1620648000},{\"aqi\":1,\"concentrations\":{\"o3\":78.68,\"so2\":3.87,\"no2\":3.38,\"co\":196.93,\"pm2_5\":2.7,\"pm10\":3.95},\"time\":1620651600},{\"aqi\":2,\"concentrations\":{\"o3\":82.25,\"so2\":3.79,\"no2\":3.3,\"co\":196.93,\"pm2_5\":2.57,\"pm10\":3.91},\"time\":1620655200},{\"aqi\":2,\"concentrations\":{\"o3\":84.4,\"so2\":3.67,\"no2\":3.43,\"co\":198.6,\"pm2_5\":2.41,\"pm10\":3.77},\"time\":1620658800},{\"aqi\":2,\"concentrations\":{\"o3\":84.4,\"so2\":3.79,\"no2\":4.33,\"co\":200.27,\"pm2_5\":2.37,\"pm10\":3.85},\"time\":1620662400},{\"aqi\":2,\"concentrations\":{\"o3\":82.97,\"so2\":4.35,\"no2\":6.0,\"co\":203.61,\"pm2_5\":2.55,\"pm10\":4.29},\"time\":1620666000},{\"aqi\":2,\"concentrations\":{\"o3\":80.82,\"so2\":4.95,\"no2\":7.63,\"co\":205.28,\"pm2_5\":2.64,\"pm10\":4.51},\"time\":1620669600},{\"aqi\":1,\"concentrations\":{\"o3\":75.1,\"so2\":6.91,\"no2\":12.0,\"co\":211.95,\"pm2_5\":2.9,\"pm10\":4.87},\"time\":1620673200},{\"aqi\":1,\"concentrations\":{\"o3\":66.52,\"so2\":8.58,\"no2\":16.28,\"co\":216.96,\"pm2_5\":3.39,\"pm10\":5.55},\"time\":1620676800},{\"aqi\":1,\"concentrations\":{\"o3\":61.51,\"so2\":9.3,\"no2\":18.85,\"co\":220.3,\"pm2_5\":3.83,\"pm10\":6.13},\"time\":1620680400},{\"aqi\":1,\"concentrations\":{\"o3\":54.36,\"so2\":9.78,\"no2\":23.31,\"co\":223.64,\"pm2_5\":4.51,\"pm10\":7.02},\"time\":1620684000},{\"aqi\":1,\"concentrations\":{\"o3\":45.78,\"so2\":9.3,\"no2\":26.05,\"co\":223.64,\"pm2_5\":5.06,\"pm10\":7.82},\"time\":1620687600},{\"aqi\":1,\"concentrations\":{\"o3\":41.49,\"so2\":9.06,\"no2\":26.39,\"co\":223.64,\"pm2_5\":5.49,\"pm10\":8.61},\"time\":1620691200},{\"aqi\":1,\"concentrations\":{\"o3\":35.76,\"so2\":8.35,\"no2\":26.73,\"co\":226.97,\"pm2_5\":6.06,\"pm10\":9.57},\"time\":1620694800},{\"aqi\":1,\"concentrations\":{\"o3\":26.82,\"so2\":7.57,\"no2\":28.45,\"co\":230.31,\"pm2_5\":6.8,\"pm10\":10.6},\"time\":1620698400},{\"aqi\":1,\"concentrations\":{\"o3\":18.77,\"so2\":7.51,\"no2\":30.16,\"co\":233.65,\"pm2_5\":7.53,\"pm10\":11.47},\"time\":1620702000},{\"aqi\":1,\"concentrations\":{\"o3\":11.8,\"so2\":7.99,\"no2\":32.22,\"co\":236.99,\"pm2_5\":8.18,\"pm10\":12.08},\"time\":1620705600},{\"aqi\":1,\"concentrations\":{\"o3\":5.45,\"so2\":9.54,\"no2\":35.3,\"co\":243.66,\"pm2_5\":9.06,\"pm10\":12.99},\"time\":1620709200},{\"aqi\":2,\"concentrations\":{\"o3\":4.6,\"so2\":13.95,\"no2\":35.99,\"co\":277.04,\"pm2_5\":11.37,\"pm10\":15.5},\"time\":1620712800},{\"aqi\":2,\"concentrations\":{\"o3\":5.72,\"so2\":19.07,\"no2\":37.7,\"co\":330.45,\"pm2_5\":14.79,\"pm10\":19.03},\"time\":1620716400},{\"aqi\":2,\"concentrations\":{\"o3\":6.62,\"so2\":20.98,\"no2\":40.78,\"co\":357.15,\"pm2_5\":16.15,\"pm10\":20.05},\"time\":1620720000},{\"aqi\":2,\"concentrations\":{\"o3\":9.48,\"so2\":21.94,\"no2\":41.81,\"co\":323.77,\"pm2_5\":13.66,\"pm10\":16.92},\"time\":1620723600},{\"aqi\":1,\"concentrations\":{\"o3\":63.66,\"so2\":10.37,\"no2\":17.48,\"co\":216.96,\"pm2_5\":4.42,\"pm10\":5.84},\"time\":1620727200},{\"aqi\":1,\"concentrations\":{\"o3\":75.82,\"so2\":7.21,\"no2\":10.11,\"co\":206.95,\"pm2_5\":2.77,\"pm10\":3.67},\"time\":1620730800},{\"aqi\":2,\"concentrations\":{\"o3\":82.97,\"so2\":6.38,\"no2\":8.23,\"co\":203.61,\"pm2_5\":2.26,\"pm10\":2.98},\"time\":1620734400},{\"aqi\":2,\"concentrations\":{\"o3\":90.84,\"so2\":5.66,\"no2\":7.03,\"co\":203.61,\"pm2_5\":1.86,\"pm10\":2.39},\"time\":1620738000},{\"aqi\":2,\"concentrations\":{\"o3\":91.55,\"so2\":5.36,\"no2\":6.43,\"co\":203.61,\"pm2_5\":1.48,\"pm10\":1.91},\"time\":1620741600},{\"aqi\":2,\"concentrations\":{\"o3\":91.55,\"so2\":5.01,\"no2\":6.17,\"co\":203.61,\"pm2_5\":1.35,\"pm10\":1.88},\"time\":1620745200},{\"aqi\":2,\"concentrations\":{\"o3\":90.84,\"so2\":5.13,\"no2\":7.2,\"co\":206.95,\"pm2_5\":1.4,\"pm10\":2.08},\"time\":1620748800},{\"aqi\":2,\"concentrations\":{\"o3\":86.55,\"so2\":5.72,\"no2\":9.68,\"co\":210.29,\"pm2_5\":1.64,\"pm10\":2.51},\"time\":1620752400},{\"aqi\":2,\"concentrations\":{\"o3\":84.4,\"so2\":6.32,\"no2\":11.48,\"co\":213.62,\"pm2_5\":1.9,\"pm10\":3.01},\"time\":1620756000},{\"aqi\":2,\"concentrations\":{\"o3\":84.4,\"so2\":7.87,\"no2\":14.57,\"co\":220.3,\"pm2_5\":2.21,\"pm10\":3.54},\"time\":1620759600},{\"aqi\":1,\"concentrations\":{\"o3\":78.68,\"so2\":9.42,\"no2\":18.68,\"co\":226.97,\"pm2_5\":2.67,\"pm10\":4.26},\"time\":1620763200},{\"aqi\":1,\"concentrations\":{\"o3\":72.96,\"so2\":9.78,\"no2\":20.39,\"co\":233.65,\"pm2_5\":3.18,\"pm10\":5.14},\"time\":1620766800},{\"aqi\":1,\"concentrations\":{\"o3\":67.23,\"so2\":9.66,\"no2\":21.94,\"co\":233.65,\"pm2_5\":3.89,\"pm10\":6.43},\"time\":1620770400},{\"aqi\":1,\"concentrations\":{\"o3\":65.09,\"so2\":9.18,\"no2\":21.08,\"co\":230.31,\"pm2_5\":4.45,\"pm10\":7.68},\"time\":1620774000},{\"aqi\":1,\"concentrations\":{\"o3\":65.8,\"so2\":8.94,\"no2\":19.19,\"co\":223.64,\"pm2_5\":4.64,\"pm10\":8.31},\"time\":1620777600},{\"aqi\":1,\"concentrations\":{\"o3\":66.52,\"so2\":8.58,\"no2\":16.97,\"co\":223.64,\"pm2_5\":4.57,\"pm10\":8.32},\"time\":1620781200},{\"aqi\":1,\"concentrations\":{\"o3\":63.66,\"so2\":8.35,\"no2\":15.94,\"co\":223.64,\"pm2_5\":4.7,\"pm10\":8.58},\"time\":1620784800},{\"aqi\":1,\"concentrations\":{\"o3\":59.37,\"so2\":8.58,\"no2\":15.77,\"co\":220.3,\"pm2_5\":5.32,\"pm10\":9.52},\"time\":1620788400},{\"aqi\":1,\"concentrations\":{\"o3\":53.64,\"so2\":9.06,\"no2\":16.45,\"co\":220.3,\"pm2_5\":6.12,\"pm10\":10.53},\"time\":1620792000},{\"aqi\":1,\"concentrations\":{\"o3\":47.21,\"so2\":10.13,\"no2\":17.65,\"co\":223.64,\"pm2_5\":6.65,\"pm10\":10.85},\"time\":1620795600},{\"aqi\":1,\"concentrations\":{\"o3\":40.05,\"so2\":11.92,\"no2\":21.76,\"co\":233.65,\"pm2_5\":7.09,\"pm10\":10.9},\"time\":1620799200},{\"aqi\":1,\"concentrations\":{\"o3\":35.05,\"so2\":13.95,\"no2\":25.02,\"co\":243.66,\"pm2_5\":7.43,\"pm10\":10.71},\"time\":1620802800},{\"aqi\":1,\"concentrations\":{\"o3\":35.41,\"so2\":15.26,\"no2\":24.68,\"co\":247.0,\"pm2_5\":7.61,\"pm10\":10.49},\"time\":1620806400},{\"aqi\":1,\"concentrations\":{\"o3\":41.13,\"so2\":15.26,\"no2\":21.25,\"co\":243.66,\"pm2_5\":7.44,\"pm10\":10.25},\"time\":1620810000},{\"aqi\":1,\"concentrations\":{\"o3\":65.09,\"so2\":9.54,\"no2\":13.2,\"co\":230.31,\"pm2_5\":5.22,\"pm10\":7.79},\"time\":1620813600},{\"aqi\":1,\"concentrations\":{\"o3\":72.24,\"so2\":8.11,\"no2\":10.45,\"co\":223.64,\"pm2_5\":3.83,\"pm10\":6.0},\"time\":1620817200},{\"aqi\":1,\"concentrations\":{\"o3\":72.96,\"so2\":7.51,\"no2\":9.77,\"co\":220.3,\"pm2_5\":2.7,\"pm10\":4.64},\"time\":1620820800},{\"aqi\":2,\"concentrations\":{\"o3\":80.11,\"so2\":6.02,\"no2\":7.54,\"co\":216.96,\"pm2_5\":1.82,\"pm10\":3.46},\"time\":1620824400},{\"aqi\":2,\"concentrations\":{\"o3\":88.69,\"so2\":5.25,\"no2\":6.26,\"co\":211.95,\"pm2_5\":1.44,\"pm10\":2.65},\"time\":1620828000},{\"aqi\":2,\"concentrations\":{\"o3\":91.55,\"so2\":5.07,\"no2\":6.08,\"co\":210.29,\"pm2_5\":1.33,\"pm10\":2.2},\"time\":1620831600},{\"aqi\":2,\"concentrations\":{\"o3\":90.12,\"so2\":5.19,\"no2\":7.45,\"co\":213.62,\"pm2_5\":1.15,\"pm10\":1.73},\"time\":1620835200},{\"aqi\":2,\"concentrations\":{\"o3\":85.12,\"so2\":5.9,\"no2\":10.45,\"co\":216.96,\"pm2_5\":1.05,\"pm10\":1.49},\"time\":1620838800},{\"aqi\":2,\"concentrations\":{\"o3\":80.11,\"so2\":6.74,\"no2\":12.51,\"co\":216.96,\"pm2_5\":1.33,\"pm10\":1.77},\"time\":1620842400},{\"aqi\":1,\"concentrations\":{\"o3\":67.23,\"so2\":10.37,\"no2\":18.68,\"co\":230.31,\"pm2_5\":2.43,\"pm10\":2.99},\"time\":1620846000}]}";
    private static FullData currentData;
    private static FullData historyData;
    private static FullData forecastData;

    @BeforeAll
    static void createData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        currentData = mapper.readValue(currentJson, FullData.class);
        historyData = mapper.readValue(historyJson, FullData.class);
        forecastData = mapper.readValue(forecastJson, FullData.class);
    }

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void getDataFromLocation() {
        Mockito.when(
                cacheService.getDataFromLocation(currentData.getLocation(), currentData.getCountry_code())).
                thenReturn(currentData);

        when().get(String.format(Locale.US, "/api/air_quality/current/location/%s,%s", currentData.getLocation(), currentData.getCountry_code()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(currentData.getLocation()))
                .and().body("country_code", is(currentData.getCountry_code()))
                .and().body("coordinates.lat", is((float) currentData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) currentData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(1));
    }

    @Test
    void getDataFromCoords() {
        Mockito.when(
                cacheService.getDataFromCoords(currentData.getCoordinates().getLat(), currentData.getCoordinates().getLon())).
                thenReturn(currentData);

        when().get(String.format(Locale.US, "/api/air_quality/current/coords/%f,%f", currentData.getCoordinates().getLat(), currentData.getCoordinates().getLon()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(currentData.getLocation()))
                .and().body("country_code", is(currentData.getCountry_code()))
                .and().body("coordinates.lat", is((float) currentData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) currentData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(1));
    }

    @Test
    void getDataFromLocationHistory() {
        Mockito.when(
                cacheService.getDataFromLocationHistory(historyData.getLocation(), historyData.getCountry_code())).
                thenReturn(historyData);

        when().get(String.format(Locale.US, "/api/air_quality/history/location/%s,%s", historyData.getLocation(), historyData.getCountry_code()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(historyData.getLocation()))
                .and().body("country_code", is(historyData.getCountry_code()))
                .and().body("coordinates.lat", is((float) historyData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) historyData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(72));
    }

    @Test
    void getDataFromCoordsHistory() {
        Mockito.when(
                cacheService.getDataFromCoordsHistory(historyData.getCoordinates().getLat(), historyData.getCoordinates().getLon())).
                thenReturn(historyData);

        when().get(String.format(Locale.US, "/api/air_quality/history/coords/%f,%f", historyData.getCoordinates().getLat(), historyData.getCoordinates().getLon()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(historyData.getLocation()))
                .and().body("country_code", is(historyData.getCountry_code()))
                .and().body("coordinates.lat", is((float) historyData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) historyData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(72));
    }

    @Test
    void getDataFromLocationForecast() {
        Mockito.when(
                cacheService.getDataFromLocationForecast(forecastData.getLocation(), forecastData.getCountry_code())).
                thenReturn(forecastData);

        when().get(String.format(Locale.US, "/api/air_quality/forecast/location/%s,%s", forecastData.getLocation(), forecastData.getCountry_code()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(forecastData.getLocation()))
                .and().body("country_code", is(forecastData.getCountry_code()))
                .and().body("coordinates.lat", is((float) forecastData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) forecastData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(72));
    }

    @Test
    void getDataFromCoordsForecast() {
        Mockito.when(
                cacheService.getDataFromCoordsForecast(forecastData.getCoordinates().getLat(), forecastData.getCoordinates().getLon())).
                thenReturn(forecastData);

        when().get(String.format(Locale.US, "/api/air_quality/forecast/coords/%f,%f", forecastData.getCoordinates().getLat(), forecastData.getCoordinates().getLon()))
                .then().assertThat()
                .statusCode(200)
                .and().body("location", is(forecastData.getLocation()))
                .and().body("country_code", is(forecastData.getCountry_code()))
                .and().body("coordinates.lat", is((float) forecastData.getCoordinates().getLat()))
                .and().body("coordinates.lon", is((float) forecastData.getCoordinates().getLon()))
                .and().body("air_pollution", hasSize(72));
    }

    @Test
    void getDataFromCache() {
        CacheData data = new CacheData(currentData, 1);
        FullCacheData fullCacheData = new FullCacheData();
        fullCacheData.setOwm_requests(2);
        fullCacheData.setWb_requests(3);
        fullCacheData.setCache_requests(4);
        fullCacheData.setFailed_requests(5);
        fullCacheData.setCurrent(new CacheData[] {data, data});
        fullCacheData.setHistory(new CacheData[0]);
        fullCacheData.setForecast(new CacheData[0]);
        Mockito.when(
                cacheService.getDataFromCache())
                .thenReturn(fullCacheData);

        when().get("/api/air_quality/cached")
                .then().assertThat()
                .statusCode(200)
                .and().body("owm_requests", is(2))
                .and().body("wb_requests", is(3))
                .and().body("cache_requests", is(4))
                .and().body("failed_requests", is(5))
                .and().body("current", hasSize(2))
                .and().body("current[0].last_query", is(1))
                .and().body("current[0].queries", is(0))
                .and().body("current[0].data.location", is(data.getData().getLocation()))
                .and().body("current[0].data.country_code", is(data.getData().getCountry_code()))
                .and().body("current[0].data.coordinates.lat", is((float) data.getData().getCoordinates().getLat()))
                .and().body("current[0].data.coordinates.lon", is((float) data.getData().getCoordinates().getLon()))
                .and().body("current[0].data.air_pollution", hasSize(1))
                .and().body("history", hasSize(0))
                .and().body("forecast", hasSize(0));
    }

    @Test
    void getDataFromLocationIsNull() {
        Mockito.when(
                cacheService.getDataFromLocation(Mockito.anyString(), Mockito.anyString())).
                thenReturn(null);

        when().get("/api/air_quality/current/location/0,0")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    void getDataFromCoordsIsNull() {
        Mockito.when(
                cacheService.getDataFromCoords(Mockito.anyDouble(), Mockito.anyDouble())).
                thenReturn(null);

        when().get("/api/air_quality/current/coords/0,0")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    void getDataFromLocationHistoryIsNull() {
        Mockito.when(
                cacheService.getDataFromLocationHistory(Mockito.anyString(), Mockito.anyString())).
                thenReturn(null);

        when().get("/api/air_quality/history/location/0,0")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    void getDataFromCoordsHistoryIsNull() {
        Mockito.when(
                cacheService.getDataFromCoordsHistory(Mockito.anyDouble(), Mockito.anyDouble())).
                thenReturn(null);

        when().get("/api/air_quality/history/coords/0,0")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    void getDataFromLocationForecastIsNull() {
        Mockito.when(
                cacheService.getDataFromLocationForecast(Mockito.anyString(), Mockito.anyString())).
                thenReturn(null);

        when().get("/api/air_quality/forecast/location/0,0")
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    void getDataFromCoordsForecastIsNull() {
        Mockito.when(
                cacheService.getDataFromCoordsForecast(Mockito.anyDouble(), Mockito.anyDouble())).
                thenReturn(null);

        when().get("/api/air_quality/forecast/coords/0,0")
                .then().assertThat()
                .statusCode(404);
    }
}