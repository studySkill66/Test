package com.yobtc.common;

import java.util.List;

public class DataList<T> {
    private List<T> resultSet;
    private int count;
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<T> resultSet) {
        this.resultSet = resultSet;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "resultSet=" + resultSet +
                ", count=" + count +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
