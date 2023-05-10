package com.bsuir.workshops.laba_1.service;

import com.bsuir.workshops.laba_1.entity.ValueRandom;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest
{
    private final GenerateService generateService = new GenerateService();


    @Test
    public void testRandom()
    {
        String inputString = "666";
        Throwable thrown = assertThrows(RuntimeException.class,
                () -> generateService.generateNum(Integer.parseInt(inputString)));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testEmptyValueRandom()
    {
        String inputString = "100";
        ValueRandom resultValueRandom = generateService.generateNum(Integer.parseInt(inputString));
        ValueRandom valueNull = new ValueRandom(-1,-1,-1,-1,-1);
        assertNotEquals(valueNull, resultValueRandom);
    }
}

