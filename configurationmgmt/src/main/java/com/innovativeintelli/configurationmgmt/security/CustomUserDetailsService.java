package com.innovativeintelli.configurationmgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.innovativeintelli.configurationmgmt.payload.GlobalRequest;
import com.innovativeintelli.configurationmgmt.payload.GlobalResponse;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	/*
	 * @Autowired UserRepository userRepository;
	 */

	@Autowired
	RestTemplate restTemplate;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// Let people login with either username or email
		GlobalRequest request = new GlobalRequest();
		request.setRequest(usernameOrEmail);
		GlobalResponse response = restTemplate.postForObject("http://localhost:5000/api/auth/getUserDetails", request, GlobalResponse.class);
		return (UserDetails) response.getData();
	}

	public UserDetails loadUserById(Long id) {
		GlobalRequest request = new GlobalRequest();
		request.setRequest(id);
		GlobalResponse response = restTemplate.postForObject("http://localhost:5000/api/auth/getUserDetails", request, GlobalResponse.class);
		return (UserDetails) response.getData();
	}
}