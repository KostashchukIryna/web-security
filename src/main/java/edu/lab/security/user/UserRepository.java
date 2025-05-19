package edu.lab.security.user;

/*
  @author   kosta
  @project   security
  @class  UserRepository
  @version  1.0.0 
  @since 19.05.2025 - 23:25
*/
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
