package com.shopping.userservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.userservice.model.UserProfile;
import com.shopping.userservice.service.UserProfileServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class UserProfileController {
	
	
	@Autowired
	private UserProfileServiceImpl userService;
	
	@PostMapping("/adduser")
	public void addNewUserProfile(@RequestBody UserProfile userProfile) {
		  userService.addNewUserProfile(userProfile);
	}
	
//	@PostMapping("/addmerchant")
//	public UserProfile addNewMerchantProfile(@RequestBody UserProfile userProfile) {
//		return userService.addNewCustomerProfile(userProfile);
//	}
	
//	@GetMapping("/viewusers")
//	public List<UserProfile> viewAllUsers(){
//		return userService.viewAllUsers();
//	}
	
	@PutMapping("/edituser")
	public void editUser(@RequestBody UserProfile userProfile) {
		   userService.updateProfile(userProfile);
	}
	
	@GetMapping("/finduser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		try {
		UserProfile user = userService.getById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("finduserbyemail/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		try {
			UserProfile user = userService.getUserByEmail(email);
			return new ResponseEntity<>(user,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	

	@GetMapping("finduserbyphone/{mobileNo}")
	public ResponseEntity<?> getUserByPhone(@PathVariable("mobileNo") String mobileNo) {
		try {
			UserProfile user = userService.getUserByMobileNo(mobileNo);
			return new ResponseEntity<>(user,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


		
		
	}
	
	
	
	
	



