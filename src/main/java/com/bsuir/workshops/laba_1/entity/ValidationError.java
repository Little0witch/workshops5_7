package com.bsuir.workshops.laba_1.entity;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private List<String> errorList = new ArrayList<String>();
    private String errorMessage;
    public void addErrorMessage(String Error){
        errorList.add(Error);
    }
    public List<String> getErrorList() {
        return errorList;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
