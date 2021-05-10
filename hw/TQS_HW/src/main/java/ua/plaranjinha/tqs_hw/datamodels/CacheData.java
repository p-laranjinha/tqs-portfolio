package ua.plaranjinha.tqs_hw.datamodels;

import lombok.Data;

@Data
public class CacheData {
    private FullData data;
    private long last_query;
    private int queries;

    public CacheData(FullData data, long last_query) {
        this.data = data;
        this.last_query = last_query;
        this.queries = 0;
    }
}
