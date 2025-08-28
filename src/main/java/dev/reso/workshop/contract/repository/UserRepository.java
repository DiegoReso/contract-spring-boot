package dev.reso.workshop.contract.repository;

import dev.reso.workshop.contract.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User>findByEmail(String email);

}
