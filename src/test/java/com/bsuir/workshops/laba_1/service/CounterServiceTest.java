package com.bsuir.workshops.laba_1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterServiceTest {
    private CounterService counterService = new CounterService();

    @Test
    public void CountersTest(){
        counterService.increment();
        counterService.incrementSimpleCount();
        Assertions.assertEquals(Integer.valueOf(1), counterService.getCount());
        Assertions.assertEquals(Integer.valueOf(1), counterService.getSimpleCount());

        counterService.cleanCounterService();
        Assertions.assertEquals(Integer.valueOf(0), counterService.getCount());
        Assertions.assertEquals(Integer.valueOf(0), counterService.getSimpleCount());
    }
}
