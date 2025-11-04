package com.example.SlotSwapperBackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SlotSwapperBackend.Model.SwapRequest;

@Repository
public interface SwapRepository extends MongoRepository<SwapRequest, String> {
}
