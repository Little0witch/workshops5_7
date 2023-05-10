package com.bsuir.workshops.laba_1.entity;

import java.util.Objects;

public class MyInteger {
    private Integer size;

    public MyInteger(Integer size) {
        this.size = size;
    }

    public Integer getInteger() {
        return size;
    }

    public void setInteger(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyInteger myInteger = (MyInteger) o;

        return Objects.equals(size, myInteger.size);
    }

}
