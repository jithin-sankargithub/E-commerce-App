package com.shopping.userservice;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shopping.userservice.model.Address;
import com.shopping.userservice.model.UserProfile;
import com.shopping.userservice.repository.UserProfileRepository;
import com.shopping.userservice.service.UserProfileServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserProfileServiceImpl userProfileServiceImpl;
	
	@MockBean
	private UserProfileRepository profileRepository;
	
	@Test
	public void addUserTest() {
		Address address = new Address("12A","Howr","Qaz","test","453344"); 
		UserProfile user = new UserProfile("123","test123","test","test@test.com","748474747",address,"customer");
		userProfileServiceImpl.addNewUserProfile(user);
		verify(profileRepository,times(1)).save(user);
		}
	
	@Test
	public void getUserByIdTest() {
		Address address = new Address("12A","Howr","Qaz","test","453344");
		Optional<UserProfile> user= Optional.of(new UserProfile("231","Mona","mona","mona@mail.com","9898889",address,"customer"));
		String userId="231";
		when(profileRepository.findById(userId)).thenReturn(user);
		
	}
	
	@Test
	public void updateUserTest() {
		Address address = new Address("123E","jawr","aswa","test","653344"); 
		UserProfile user = new UserProfile("123","abul123","test","abul@test.com","748474747",address,"customer");
		userProfileServiceImpl.updateProfile(user);
		verify(profileRepository,times(1)).save(user);
	}
	
	@Test
	public void getUserByEmailTest() {
		Address address = new Address("674W","Ajman","Hanisto","rory","95858");
		Optional<UserProfile> user= Optional.of(new UserProfile("5748484","Rithwik","rith","rithwik@mail.com","9898889",address,"customer"));
		String email ="rithwik@mail.com";
		when(profileRepository.getUserByEmail(email)).thenReturn(user);
	}

}
