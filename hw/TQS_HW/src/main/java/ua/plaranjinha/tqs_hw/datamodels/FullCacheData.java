package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class FullCacheData {
    private CacheData[] current;
    private CacheData[] history;
    private CacheData[] forecast;
}
