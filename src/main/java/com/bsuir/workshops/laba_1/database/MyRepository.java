package com.bsuir.workshops.laba_1.database;

import com.bsuir.workshops.laba_1.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyRepository {
    private Repository repository;

    @Autowired
    public MyRepository(Repository repository){
        this.repository = repository;
    }

    public void save(Result result){
       repository.save(new DbEntity(result));
    }

    public void save(long pred_id, Result result){
        repository.save(new DbEntity(pred_id, result));
    }

    public List<Result> getAll(){
        List<Result> results = new ArrayList<>();
        repository.findAll().forEach(e -> results.add(e.getResult()));
        return results;
    }

    public long getLastId()
    {
        return repository.count();
//        if (repository.count() == 0)
//        {
//            return 0;
//        }
//        DbEntity ent = repository.findAll().get((int) (repository.count()-1));
//        return ent.getId();
    }

    public Result findById(long predId)
    {
        for(DbEntity entity: repository.findAll())
        {
            if (entity.getPredId() == predId)
                return entity.getResult();
        }
        return null;
    }


}
