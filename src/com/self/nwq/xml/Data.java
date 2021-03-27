package com.self.nwq.xml;

import java.util.Objects;

public class Data {
    private String keyStr;
    private String valueStr;

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    @Override
    public String toString() {
        return "Data{" +
                "keyStr='" + keyStr + '\'' +
                ", valueStr='" + valueStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return Objects.equals(keyStr, data.keyStr) &&
                Objects.equals(valueStr, data.valueStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyStr, valueStr);
    }
}
