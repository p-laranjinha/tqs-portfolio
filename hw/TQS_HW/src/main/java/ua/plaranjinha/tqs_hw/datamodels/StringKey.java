package ua.plaranjinha.tqs_hw.datamodels;

import java.util.Objects;

public class StringKey {
    public final String key1;
    public final String key2;

    public StringKey(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringKey)) return false;
        StringKey key = (StringKey) o;
        return Objects.equals(key1, key.key1) && Objects.equals(key2, key.key2);
    }

    @Override
    public int hashCode() {
        int result = key1 != null ? key1.hashCode() : 0;
        result = 31 * result + (key2 != null ? key2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + key1 + ", " + key2 + "]";
    }
}
