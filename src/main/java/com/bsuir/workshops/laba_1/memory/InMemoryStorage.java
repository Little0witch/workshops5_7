package com.bsuir.workshops.laba_1.memory;

import com.bsuir.workshops.laba_1.entity.Result;
import com.bsuir.workshops.laba_1.entity.ValueRandom;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryStorage
{
    private List<Result> list = new ArrayList<Result>();

    public InMemoryStorage(List<Result> list){
        this.list = list;
    }
    public List<Result> getList() {
        return list;
    }

    public void setList(List<Result> list) {
        this.list = list;
    }
    public synchronized void addToMemoryStorage(Result result) {
        list.add(result);
    }

    public Integer size(){
        return list.size();
    }


}
