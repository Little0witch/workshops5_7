package com.bsuir.workshops.laba_1.entity;

public class Result {

    private String input;
    private ValidationError errors;
    private ValueRandom valueRandom;
    public Result() {
        this.input = new String();
        this.errors = new ValidationError();
        this.valueRandom = new ValueRandom(-1,-1,-1,-1,-1);
    }

    public Result(String Input, ValidationError errors, ValueRandom valueRandom) {
        this.input = Input;
        this.errors = errors;
        this.valueRandom = valueRandom;
    }
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ValidationError getErrors() {
        return errors;
    }

    public void setErrors(ValidationError errors) {
        this.errors = errors;
    }

    public ValueRandom getValueRandom() {
        return valueRandom;
    }

    public void setValueRandom(ValueRandom valueRandom) {
        this.valueRandom = valueRandom;
    }


}
