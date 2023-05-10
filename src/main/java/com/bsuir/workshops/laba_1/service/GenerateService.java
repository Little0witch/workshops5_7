package com.bsuir.workshops.laba_1.service;

import com.bsuir.workshops.laba_1.entity.ValueRandom;
import org.springframework.stereotype.Service;

@Service
public class GenerateService {
    public GenerateService() {}

    public ValueRandom generateNum(int inputNum){
        if (inputNum==666) throw new RuntimeException("666 значение запрещенное");
        int num1 = (int) (Math.random()*(inputNum+1));
        int num2 = (int) (Math.random()*(inputNum+1));
        int num3 = (int) (Math.random()*(inputNum+1));
        int num4 = (int) (Math.random()*(inputNum+1));
        int num5 = (int) (Math.random()*(inputNum+1));
        return new ValueRandom(num1,num2,num3,num4,num5);
    }
}