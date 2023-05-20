package com.bsuir.workshops.laba_1.controller;

import com.bsuir.workshops.laba_1.database.DbEntity;
import com.bsuir.workshops.laba_1.database.MyRepository;
import com.bsuir.workshops.laba_1.memory.InMemoryStorage;
import com.bsuir.workshops.laba_1.entity.*;
import com.bsuir.workshops.laba_1.service.CounterService;
import com.bsuir.workshops.laba_1.service.GenerateService;
import com.bsuir.workshops.laba_1.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/lab1")
public class Controller{
    private Logger logger = LogManager.getLogger(Controller.class);
    private GenerateService generateServiceRandomNum;
    private Validator validator;
    private InMemoryStorage inMemoryStorage;

    private CounterService counterService;

    private MyRepository myRepository;

    @Autowired
    public Controller(GenerateService generateServiceRandomNum, Validator validator,
                      InMemoryStorage inMemoryStorage, CounterService counterService, MyRepository myRepository) {
        this.generateServiceRandomNum = generateServiceRandomNum;
        this.validator = validator;
        this.inMemoryStorage = inMemoryStorage;
        this.counterService = counterService;
        this.myRepository = myRepository;
    }
    // лаба 7

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> resultRandom(@RequestParam("inputNum") String inputNum)
    {
        counterService.increment();
        counterService.incrementSimpleCount();
        Result result = new Result();
        result.setInput(inputNum);
        logger.info("1.Validation");
        result.setErrors(validator.validateInputNum(inputNum));
        if (!result.getErrors().getErrorList().isEmpty())
        {
            result.getErrors().setErrorMessage("BAD_REQUEST");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        ValueRandom valueRandom;
        Integer value = Integer.parseInt(inputNum);
        try
        {
            logger.info("2.GenerateService random num");
            valueRandom = generateServiceRandomNum.generateNum(value);
            result.setValueRandom(valueRandom);
            result.getErrors().setErrorMessage("OK");
            myRepository.save(result);
        }
        catch (RuntimeException exc)
        {
            result.getErrors().setErrorMessage("INTERNAL_SERVER_ERROR");
            result.getErrors().addErrorMessage(exc.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("3.Add memory");
        inMemoryStorage.addToMemoryStorage(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> randomHistory()
    {
        return new ResponseEntity<>(inMemoryStorage.getList(), HttpStatus.OK);
    }

    @GetMapping("/history/size")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> randomHistorySize()
    {
      return new ResponseEntity<>(new MyInteger(inMemoryStorage.size()), HttpStatus.OK);
    }

    //lab4
    @GetMapping("/counterCalls")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> CounterCalls(){
        logger.info("Return result counterService");
        return new ResponseEntity<>(counterService, HttpStatus.OK);
    }


    /**********************************************************************************************************/


//lab 5
    @PostMapping("/bulkRandom")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BulkResult> postRandom(@RequestBody List<BulkParam> paramList){

        logger.info("1. bulk");
        List<ResultList> resultsList = new ArrayList<>();
        List<String> errorList = new ArrayList<String>();

        paramList.forEach((currentElement)-> {
            Result result = new Result();
            result.setInput(currentElement.getInputString());
            logger.info("2. validation on bulk");
            errorList.addAll(validator.validateInputNum(currentElement.getInputString()).getErrorList());
            if (errorList.isEmpty())
            {
                Integer tmp;
                tmp = Integer.parseInt(currentElement.getInputString());
                logger.info("3. GenerateService random num");
                ValueRandom tmpValueRandom = new ValueRandom();
                tmpValueRandom = generateServiceRandomNum.generateNum(tmp);
                result.setValueRandom(tmpValueRandom);
                logger.info("4. Add memory");
                myRepository.save(myRepository.getLastId()+1,result);
                inMemoryStorage.addToMemoryStorage(result);
                resultsList.add(new ResultList(result));
            }
        });

        double minInput = getMinMiddleValue(resultsList);
        double maxInput = getMaxMiddleValue(resultsList);
        double middleInput = getMiddleInput(resultsList);
        return new ResponseEntity<>(new BulkResult(resultsList, minInput, maxInput, middleInput, errorList), HttpStatus.CREATED);
    }


    // лаба 6
    private Comparator<ResultList> comparator = new Comparator<ResultList>(){
        @Override
        public int compare(ResultList r1, ResultList r2){
            double d1 = r1.getValues().getMiddleValue();
            double d2 = r2.getValues().getMiddleValue();
            return Double.compare(d1, d2);
        }
    };



    public double getMinMiddleValue(List<ResultList> results){
        return results.stream().min(comparator).get().getValues().getMiddleValue();
    }

    public double getMaxMiddleValue(List<ResultList> results){
        return results.stream().max(comparator).get().getValues().getMiddleValue();
    }

    public double getMiddleInput(List<ResultList> results){
        double sum = 0;
        for(ResultList result:results){
            sum += result.getValues().getMiddleValue();
        }
        return sum / results.size();
    }

    @GetMapping("/database")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Result>> getDatabase(){
        return new ResponseEntity<>(myRepository.getAll(), HttpStatus.CREATED);
    }

    @GetMapping("/asyncRandom")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getAsyncId(String inputNum){

        logger.info("1. Validation");
        Result result = new Result();
        result.setErrors(validator.validateInputNum(inputNum));
        if (!result.getErrors().getErrorList().isEmpty())
        {
            return new ResponseEntity<>(result.getErrors(), HttpStatus.BAD_REQUEST);
        }
        logger.info("Генерация предварительного Id");
        long lastId = myRepository.getLastId() + 1;
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run(){
                logger.info("Запущена генерация random");
                ValueRandom valueRandom = generateServiceRandomNum.generateNum(Integer.parseInt(inputNum));
                result.setInput(inputNum);
                result.setValueRandom(valueRandom);
                logger.info("Сохранение в БД");
                myRepository.save(lastId, result);
            }
        });
        logger.info("Вывод предварительного id");
        return new ResponseEntity<>(lastId, HttpStatus.CREATED);
    }

    @GetMapping("/findByPredId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Result> findByPredId(long predId)
    {
        return new ResponseEntity<>(myRepository.findById(predId),HttpStatus.OK);
    }

}
