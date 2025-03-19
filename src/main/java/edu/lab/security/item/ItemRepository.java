package edu.lab.security.item;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
  @author   kosta
  @project   security
  @class  ItemRepository
  @version  1.0.0 
  @since 20.03.2025 - 00.15
*/public interface ItemRepository extends MongoRepository<Item, String> {
}
