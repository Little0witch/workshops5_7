package com.bsuir.workshops.laba_1.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CounterService {

    Integer count = Integer.valueOf(0);
    Integer simpleCount = Integer.valueOf(0);

    public synchronized void increment (){
        count++;
    }
     public void incrementSimpleCount() {
        simpleCount++;
     }

    public Integer getCount() {
        return count;
    }

    public Integer getSimpleCount() {
        return simpleCount;
    }

    public void cleanCounterService() {
        count = Integer.valueOf(0);
        simpleCount = Integer.valueOf(0);
    }

}
