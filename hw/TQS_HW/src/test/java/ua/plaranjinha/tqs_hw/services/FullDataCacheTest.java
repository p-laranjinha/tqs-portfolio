package ua.plaranjinha.tqs_hw.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.plaranjinha.tqs_hw.datamodels.CacheData;
import ua.plaranjinha.tqs_hw.datamodels.DoubleKey;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.datamodels.StringKey;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class FullDataCacheTest {

    FullDataCache cache;

    private final static String resultJson = "{\"location\":\"London\",\"country_code\":\"GB\",\"coordinates\":{\"lon\":-0.09184,\"lat\":51.51279},\"air_pollution\":[{\"aqi\":30,\"concentrations\":{\"o3\":65.6247,\"so2\":1.6354,\"no2\":18.7214,\"co\":310.004,\"pm2_5\":2.98503,\"pm10\":8.33251},\"time\":1620588231}]}";
    private static FullData resultData;

    @BeforeAll
    static void createData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        resultData = mapper.readValue(resultJson, FullData.class);
    }

    @BeforeEach
    void setUp() {
        cache = new FullDataCache();
        cache.put(resultData);
    }

    @Test
    void getOnNewCacheString() {
        cache = new FullDataCache();
        CacheData data = cache.get(Mockito.mock(StringKey.class));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getOnNewCacheDouble() {
        cache = new FullDataCache();
        CacheData data = cache.get(Mockito.mock(DoubleKey.class));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getExistingString() {
        CacheData data = cache.get(new StringKey(resultData.getLocation(), resultData.getCountry_code()));
        assertThat(data.getQueries()).isEqualTo(1);
        assertThat(data.getData()).isEqualTo(resultData);
    }

    @Test
    void getExistingDouble() {
        CacheData data = cache.get(new DoubleKey(resultData.getCoordinates().getLat(), resultData.getCoordinates().getLon()));
        assertThat(data.getQueries()).isEqualTo(1);
        assertThat(data.getData()).isEqualTo(resultData);
    }

    @Test
    void getNonExistingString() {
        CacheData data = cache.get(new StringKey("", ""));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getNonExistingDouble() {
        CacheData data = cache.get(new DoubleKey(1000d, 1000d));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getRemovedString() {
        CacheData data = cache.remove(new StringKey(resultData.getLocation(), resultData.getCountry_code()));
        assertThat(data.getQueries()).isEqualTo(0);
        assertThat(data.getData()).isEqualTo(resultData);
        data = cache.get(new StringKey(resultData.getLocation(), resultData.getCountry_code()));
        assertThat(data).isEqualTo(null);
        data = cache.get(new DoubleKey(resultData.getCoordinates().getLat(), resultData.getCoordinates().getLon()));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getRemovedDouble() {
        CacheData data = cache.remove(new DoubleKey(resultData.getCoordinates().getLat(), resultData.getCoordinates().getLon()));
        assertThat(data.getQueries()).isEqualTo(0);
        assertThat(data.getData()).isEqualTo(resultData);
        data = cache.get(new DoubleKey(resultData.getCoordinates().getLat(), resultData.getCoordinates().getLon()));
        assertThat(data).isEqualTo(null);
        data = cache.get(new StringKey(resultData.getLocation(), resultData.getCountry_code()));
        assertThat(data).isEqualTo(null);
    }

    @Test
    void getAllOnNewCache() {
        cache = new FullDataCache();
        CacheData[] data = cache.getAll();
        assertThat(data.length).isEqualTo(0);
    }

    @Test
    void getAll() {
        CacheData[] data = cache.getAll();
        assertThat(data.length).isEqualTo(1);
        assertThat(data[0].getData()).isEqualTo(resultData);
    }

    @Test
    void getExpired() throws InterruptedException, ReflectiveOperationException {
        setFinalStaticField(FullDataCache.class, "TTL", (long) Math.pow(10, 9));
        assertThat(cache.getAll().length).isEqualTo(1);
        TimeUnit.MILLISECONDS.sleep(1100);
        assertThat(cache.getAll().length).isEqualTo(0);
    }

    private static void setFinalStaticField(Class<?> clazz, String fieldName, Object value)
            throws ReflectiveOperationException {
        // https://dzone.com/articles/how-to-change-private-static-final-fields

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, value);
    }
}