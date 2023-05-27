package com.viktor.recipebackend.structures;

public class QueryResultData<T> {
    private T data;
    private long recordsLength;

    public QueryResultData() {
    }

    public QueryResultData(T data, long recordsLength) {
        this.data = data;
        this.recordsLength = recordsLength;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getRecordsLength() {
        return recordsLength;
    }

    public void setRecordsLength(long recordsLength) {
        this.recordsLength = recordsLength;
    }
}