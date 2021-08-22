package com.shopping.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.userservice.model.UserProfile;
import com.shopping.userservice.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public void addNewUserProfile(UserProfile userProfile) {
		   userProfileRepository.save(userProfile);
	}

	@Override
	public UserProfile getById(String id) {
		return userProfileRepository.findById(id).get();
	}

	@Override
	public void updateProfile(UserProfile userProfile) {
		 userProfileRepository.save(userProfile);
	}
	
	@Override
	public UserProfile getUserByEmail(String email) {
		return userProfileRepository.getUserByEmail(email);
	}

	@Override
	public UserProfile getUserByMobileNo(String mobileNo) {
		 return userProfileRepository.getUserByMobileNo(mobileNo);
	}

//	@Override
//	public UserProfile getByUserName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void addNewMerchantProfile(UserProfile userprofile) {
		  userProfileRepository.save(userprofile);
	}

	


//
//	@Override
//	public List<UserProfile> viewAllUsers() {
//		
//		return userProfileRepository.findAll();
//	}

}
