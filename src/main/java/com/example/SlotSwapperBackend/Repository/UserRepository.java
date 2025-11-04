package com.example.SlotSwapperBackend.Repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.SlotSwapperBackend.Model.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
