package com.example.SlotSwapperBackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SlotSwapperBackend.Model.Event;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findByUserId(String userId);
}
