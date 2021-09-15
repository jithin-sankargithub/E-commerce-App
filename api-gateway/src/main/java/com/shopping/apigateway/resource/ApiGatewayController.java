package com.shopping.apigateway.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.apigateway.model.JWTRequest;
import com.shopping.apigateway.model.JWTResponse;
import com.shopping.apigateway.service.UserService;
import com.shopping.apigateway.utility.JWTUtility;

@RestController
public class ApiGatewayController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("hello")
	public String hello() {
		return "Hello World";
	}
	
	@PostMapping("/encode")
	public String encode(@RequestBody String pass) {
		String encodePassword = bCryptPasswordEncoder.encode(pass);
		return encodePassword;
	}
	
	@PostMapping("/authenticate")
	 public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{

       

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword())

            );
        } catch (BadCredentialsException e){throw  new Exception("INVALID CREDENTIALS", e);}

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JWTResponse(token);
    }

}
