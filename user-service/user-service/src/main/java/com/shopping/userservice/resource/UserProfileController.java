package com.shopping.userservice.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.userservice.model.UserProfile;
import com.shopping.userservice.service.UserProfileServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserProfileController {
	
	
	@Autowired
	private UserProfileServiceImpl userService;
	
	@PostMapping("/adduser")
	@ApiOperation(value = "Register a user",
	notes = "Registering a user to the database")
	public void addNewUserProfile(@RequestBody UserProfile userProfile) {
		  userService.addNewUserProfile(userProfile);
	}
	
	@PutMapping("/edituser")
	@ApiOperation(value = "Updating a user",
	notes = "Updating a user to the database")
	public void editUser(@RequestBody UserProfile userProfile) {
		   userService.updateProfile(userProfile);
	}
	
	@GetMapping("/finduser/{id}")
	@ApiOperation(value = "Finding a user",
	notes = "Finding a user by using id")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		try {
		Optional<UserProfile> user = userService.getById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("finduserbyemail/{email}")
	@ApiOperation(value = "Finding a user",
	notes = "Finding a user by using email")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		try {
			Optional<UserProfile> user = userService.getUserByEmail(email);
			return new ResponseEntity<>(user,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
		
	}
	
	
	
	
	



