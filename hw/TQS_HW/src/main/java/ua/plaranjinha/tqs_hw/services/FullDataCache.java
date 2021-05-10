package ua.plaranjinha.tqs_hw.services;

import ua.plaranjinha.tqs_hw.datamodels.CacheData;
import ua.plaranjinha.tqs_hw.datamodels.DoubleKey;
import ua.plaranjinha.tqs_hw.datamodels.FullData;
import ua.plaranjinha.tqs_hw.datamodels.StringKey;

import java.util.HashMap;

public class FullDataCache {
    private final HashMap<StringKey, CacheData> locationCache = new HashMap<>();
    private final HashMap<DoubleKey, CacheData> coordsCache = new HashMap<>();
    private static final long TTL = 60 * (long) Math.pow(10, 9); //60 seconds

    public CacheData get(StringKey key) {
        CacheData data = locationCache.get(key);
        if (expired(data)) {
            if (data != null) remove(key);
            return null;
        }
        data.setQueries(data.getQueries()+1);
        data.setLast_query(System.nanoTime());
        return data;
    }

    public CacheData get(DoubleKey key) {
        CacheData data = coordsCache.get(key);
        if (expired(data)) {
            if (data != null) remove(key);
            return null;
        }
        data.setQueries(data.getQueries()+1);
        data.setLast_query(System.nanoTime());
        return data;
    }

    public void put(FullData data) {
        StringKey locationKey = new StringKey(data.getLocation(), data.getCountry_code());
        DoubleKey coordsKey = new DoubleKey(data.getCoordinates().getLat(), data.getCoordinates().getLon());
        CacheData cacheData = new CacheData(data, System.nanoTime());
        locationCache.put(locationKey, cacheData);
        coordsCache.put(coordsKey, cacheData);
    }

    public CacheData remove(StringKey key) {
        CacheData data = locationCache.remove(key);
        coordsCache.remove(new DoubleKey(data.getData().getCoordinates().getLat(), data.getData().getCoordinates().getLon()));
        return data;
    }

    public CacheData remove(DoubleKey key) {
        CacheData data = coordsCache.remove(key);
        locationCache.remove(new StringKey(data.getData().getLocation(), data.getData().getCountry_code()));
        return data;
    }

    public CacheData[] getAll() {
        clearExpired();
        return coordsCache.values().toArray(new CacheData[0]);
    }

    public boolean containsKey(StringKey key) {
        return locationCache.containsKey(key);
    }

    public boolean containsKey(DoubleKey key) {
        return coordsCache.containsKey(key);
    }

    private boolean expired(CacheData data) {
        return (data == null) || ((System.nanoTime() - data.getLast_query()) > TTL);
    }

    private void clearExpired() {
        for (DoubleKey key : coordsCache.keySet())
            if (expired(coordsCache.get(key)))
                remove(key);
    }
}
