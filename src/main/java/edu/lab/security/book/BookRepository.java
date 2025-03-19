package edu.lab.security.book;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
  @author   kosta
  @project   security
  @class  BookRepository
  @version  1.0.0 
  @since 20.03.2025 - 00.15
*/public interface BookRepository extends MongoRepository<Book, String> {
}
