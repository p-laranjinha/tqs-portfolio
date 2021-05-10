package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class FullCacheData {
    private int owm_requests;
    private int wb_requests;
    private int cache_requests;
    private int failed_requests;
    private CacheData[] current;
    private CacheData[] history;
    private CacheData[] forecast;
}
