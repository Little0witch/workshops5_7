package com.bsuir.workshops.laba_1.entity;

import java.util.List;

public class BulkResult {
    private List<ResultList> results;
    private double minMiddleValue;
    private double maxMiddleValue;
    private double middleMiddleValue;
    private  List<String> errorList;

    public BulkResult(List<ResultList> results, double minMiddleValue, double maxMiddleValue,
                      double middleMiddleValue, List<String> errorList) {
        this.results = results;
        this.minMiddleValue = minMiddleValue;
        this.maxMiddleValue = maxMiddleValue;
        this.middleMiddleValue = middleMiddleValue;
        this.errorList = errorList;
    }

    public List<ResultList> getResults() {
        return results;
    }

    public void setResults(List<ResultList> results) {
        this.results = results;
    }

    public double getMinMiddleValue() {
        return minMiddleValue;
    }

    public void setMinMiddleValue(double minMiddleValue) {
        this.minMiddleValue = minMiddleValue;
    }

    public double getMaxMiddleValue() {
        return maxMiddleValue;
    }

    public void setMaxMiddleValue(double maxMiddleValue) {
        this.maxMiddleValue = maxMiddleValue;
    }

    public double getMiddleMiddleValue() {
        return middleMiddleValue;
    }

    public void setMiddleMiddleValue(double middleMiddleValue) {
        this.middleMiddleValue = middleMiddleValue;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
