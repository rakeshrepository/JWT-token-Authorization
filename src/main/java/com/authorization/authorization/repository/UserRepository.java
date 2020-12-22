package com.authorization.authorization.repository;

import com.authorization.authorization.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

	public User findByUserName(String userName);
}
