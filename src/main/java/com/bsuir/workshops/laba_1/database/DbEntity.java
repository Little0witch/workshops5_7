package com.bsuir.workshops.laba_1.database;

import com.bsuir.workshops.laba_1.entity.Result;
import com.bsuir.workshops.laba_1.entity.ValueRandom;

import javax.persistence.*;

@Entity
@Table(name="mytable")
public class DbEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="sequence", sequenceName = "id_generator", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence")
    private int Id;


    @Column(name="input_number")
    private String inputNumber;

   @Column(name="random1")
    private int random1;

    @Column(name="random2")
    private int random2;

    @Column(name="random3")
    private int random3;

    @Column(name="random4")
    private int random4;

    @Column(name="random5")
    private int random5;

    public DbEntity(){

    }

    public DbEntity(Result result){
        inputNumber = result.getInput();
        random1 = result.getValueRandom().getNum1();
        random2 = result.getValueRandom().getNum2();
        random3 = result.getValueRandom().getNum3();
        random4 = result.getValueRandom().getNum4();
        random5 = result.getValueRandom().getNum5();
    }

    public Result getResult(){
        ValueRandom values = new ValueRandom(random1, random2, random3, random4, random5);
        Result result = new Result();
        result.setInput(inputNumber);
        result.setValueRandom(values);
        return result;
    }
}
