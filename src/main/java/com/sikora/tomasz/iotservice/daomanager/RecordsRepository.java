package com.sikora.tomasz.iotservice.daomanager;

import com.sikora.tomasz.iotservice.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomasz Sikora for personal use.
 */
public interface RecordsRepository extends MongoRepository<Record, Integer>
{
    List<Record> findTop10ByDevIdOrderByIdDesc(int devId);
}
