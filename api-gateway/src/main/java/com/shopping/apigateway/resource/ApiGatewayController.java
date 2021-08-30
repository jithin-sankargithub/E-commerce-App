package com.shopping.apigateway.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@RequestMapping("/api")
public class ApiGatewayController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("hello")
	public String hello() {
		return "Hello World";
	}
	
	@PostMapping("/authenticate")
	 public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{

        System.out.println("-----------------------done --------------");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword())

            );System.out.println("-----------------------done --------------");
        } catch (BadCredentialsException e){throw  new Exception("INVALID CREDENTIALS", e);}

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JWTResponse(token);
    }

}