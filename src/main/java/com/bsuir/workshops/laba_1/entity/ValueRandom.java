package com.bsuir.workshops.laba_1.entity;

import org.springframework.boot.env.ConfigTreePropertySource;

import java.util.Objects;

public class ValueRandom {

    private Integer num1;
    private Integer num2;
    private Integer num3;
    private Integer num4;
    private Integer num5;

    public ValueRandom() {
        this.num1 = Integer.valueOf(0);
        this.num2 = Integer.valueOf(0);
        this.num3 = Integer.valueOf(0);
        this.num4 = Integer.valueOf(0);
        this.num5 = Integer.valueOf(0);
    }
     public ValueRandom(Integer num1, Integer num2, Integer num3, Integer num4, Integer num5) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
    }


    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public Integer getNum3() {
        return num3;
    }

    public void setNum3(Integer num3) {
        this.num3 = num3;
    }

    public Integer getNum4() {
        return num4;
    }

    public void setNum4(Integer num4) {
        this.num4 = num4;
    }

    public Integer getNum5() {
        return num5;
    }

    public void setNum5(Integer num5) {
        this.num5 = num5;
    }
    public double getMiddleValue(){
        return (num1 + num2 + num3 + num4 + num5)/5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueRandom that = (ValueRandom) o;

        if (!Objects.equals(num1, that.num1)) return false;
        if (!Objects.equals(num2, that.num2)) return false;
        if (!Objects.equals(num3, that.num3)) return false;
        if (!Objects.equals(num4, that.num4)) return false;
        return Objects.equals(num5, that.num5);
    }
}
