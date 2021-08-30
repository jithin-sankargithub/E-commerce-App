package com.shopping.apigateway.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopping.apigateway.model.UserProfile;
@Repository
public interface UserRepository extends MongoRepository<UserProfile, String>{
	
	Optional<UserProfile> findByUserName(String UserName);
	Optional<UserProfile> findByEmail(String email);

}
