package com.bsuir.workshops.laba_1.controller;

import com.bsuir.workshops.laba_1.database.RepositoryService;
import com.bsuir.workshops.laba_1.entity.*;
import com.bsuir.workshops.laba_1.memory.InMemoryStorage;
import com.bsuir.workshops.laba_1.service.CounterService;
import com.bsuir.workshops.laba_1.service.GenerateService;
import com.bsuir.workshops.laba_1.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private GenerateService generateService = mock(GenerateService.class);

    @Mock
    private Validator validator = mock(Validator.class);

    @Mock
    private InMemoryStorage inMemoryStorage = mock(InMemoryStorage.class);

    @Mock
    private CounterService counterService = mock(CounterService.class);

    @Mock
    private RepositoryService repositoryService = mock(RepositoryService.class);

    @InjectMocks
    private Controller controller = new Controller(generateService, validator, inMemoryStorage, counterService, repositoryService);

    @Test
    public void testGoodResult(){
        String inputString = "80";
        ValueRandom valueRandom = new ValueRandom(21,3,17,37,5);
        ValueRandom valueRandomExc = new ValueRandom(-1,-1,-1,-1,-1);
        ValidationError errorsMes = new ValidationError();
        when(validator.validateInputNum(inputString)).thenReturn(errorsMes);
        when(generateService.generateNum(Integer.parseInt(inputString))).thenReturn(valueRandom);
        ResponseEntity<Object> response = controller.resultRandom(inputString);
        Result result = (Result) response.getBody();
        assertNotNull(response);
        assertNotEquals(valueRandomExc, result.getValueRandom());
    }

    @Test
    public void testBadRequest(){
        String inputString = "F";
        ValueRandom valueRandom = new ValueRandom(-1,-1,-1,-1,-1);
        ValidationError errorsMes = new ValidationError();
        errorsMes.addErrorMessage("Передано не число!");
        when(validator.validateInputNum(inputString)).thenReturn(errorsMes);
        ResponseEntity<Object> response = controller.resultRandom(inputString);
        Result result = (Result) response.getBody();
        assertNotNull(result);
        assertEquals(result.getErrors(), errorsMes);
        assertEquals(result.getValueRandom(), valueRandom);
    }
    @Test
    public void testException(){
        String inputString = "666";
        ValidationError errorsMes = new ValidationError();
        ValueRandom valueRandom = new ValueRandom(-1,-1,-1,-1,-1);
        when(validator.validateInputNum(inputString)).thenReturn(new ValidationError());
        when(generateService.generateNum(Integer.parseInt(inputString))).thenThrow(RuntimeException.class);
        ResponseEntity<Object> response = controller.resultRandom(inputString);
        Result result = (Result) response.getBody();
        assertNotNull(result);
        assertEquals(result.getValueRandom(), valueRandom);
    }

    @Test
    public void testGetMemoryResult()
    {
        when(inMemoryStorage.getList()).thenReturn(null);
        ResponseEntity<Object> response = controller.randomHistory();
        Object result = response.getBody();
        assertNull(result);
    }

    @Test
    public void testGetMemorySize()
    {
        when(inMemoryStorage.size()).thenReturn(0);
        ResponseEntity<Object> response = controller.randomHistorySize();
        MyInteger result = (MyInteger) response.getBody();
        assertEquals(new MyInteger(0), result);
    }

}
