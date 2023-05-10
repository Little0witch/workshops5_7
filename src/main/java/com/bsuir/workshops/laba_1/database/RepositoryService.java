package com.bsuir.workshops.laba_1.database;

import com.bsuir.workshops.laba_1.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryService {
    private Repository repository;

    @Autowired
    public RepositoryService(Repository repository){
        this.repository = repository;
    }

    public void save(Result result){
        repository.save(new DbEntity(result));
    }

    public List<Result> getAll(){
        List<Result> results = new ArrayList<>();
        repository.findAll().forEach(e -> results.add(e.getResult()));
        return results;
    }

}
