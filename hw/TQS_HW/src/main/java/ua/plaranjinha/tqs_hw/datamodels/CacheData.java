package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class CacheData {
    private FullData data;
    private long lastQuery;
    private int queries;

    public CacheData(FullData data, long lastQuery) {
        this.data = data;
        this.lastQuery = lastQuery;
        this.queries = 1;
    }
}
