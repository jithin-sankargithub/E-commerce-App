package com.shopping.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopping.userservice.model.UserProfile;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
	public Optional<UserProfile> getUserByEmail(String email);
	

}
