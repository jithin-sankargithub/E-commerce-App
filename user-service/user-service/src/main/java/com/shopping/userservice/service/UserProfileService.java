package com.shopping.userservice.service;

import java.util.Optional;

import com.shopping.userservice.model.UserProfile;

public interface UserProfileService {
	
	public void addNewUserProfile(UserProfile userProfile);
	public Optional<UserProfile> getById(String id);
	public void updateProfile(UserProfile userProfile);
	public Optional<UserProfile> getUserByEmail(String email);



}
