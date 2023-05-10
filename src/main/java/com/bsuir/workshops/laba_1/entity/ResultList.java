package com.bsuir.workshops.laba_1.entity;

public class ResultList {

    private String input;
    private ValueRandom values;

    public ResultList(String input, ValueRandom values) {
        this.input = input;
        this.values = values;
    }

    public ResultList(Result result){
        input = result.getInput();
        values = result.getValueRandom();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ValueRandom getValues() {
        return values;
    }

    public void setValues(ValueRandom values) {
        this.values = values;
    }
}
