package com.shopping.userservice.service;

import com.shopping.userservice.model.UserProfile;

public interface UserProfileService {
	
	public void addNewUserProfile(UserProfile userProfile);
	public UserProfile getById(String id);
	public void updateProfile(UserProfile userProfile);
//	public UserProfile getByUserName(String name);
	public void addNewMerchantProfile(UserProfile userprofile);
	public UserProfile getUserByEmail(String email);
	public UserProfile getUserByMobileNo(String mobileNo);


}
