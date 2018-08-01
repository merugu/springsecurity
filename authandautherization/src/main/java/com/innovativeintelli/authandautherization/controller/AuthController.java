package com.innovativeintelli.authandautherization.controller;

import java.net.URI;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innovativeintelli.authandautherization.exception.AppException;
import com.innovativeintelli.authandautherization.model.Role;
import com.innovativeintelli.authandautherization.model.RoleName;
import com.innovativeintelli.authandautherization.model.User;
import com.innovativeintelli.authandautherization.payload.ApiResponse;
import com.innovativeintelli.authandautherization.payload.GlobalRequest;
import com.innovativeintelli.authandautherization.payload.GlobalResponse;
import com.innovativeintelli.authandautherization.payload.JwtAuthenticationResponse;
import com.innovativeintelli.authandautherization.payload.LoginRequest;
import com.innovativeintelli.authandautherization.payload.SignUpRequest;
import com.innovativeintelli.authandautherization.payload.ValidateTokenRequest;
import com.innovativeintelli.authandautherization.repository.RoleRepository;
import com.innovativeintelli.authandautherization.repository.UserRepository;
import com.innovativeintelli.authandautherization.security.CustomUserDetailsService;
import com.innovativeintelli.authandautherization.security.JwtTokenProvider;
import com.innovativeintelli.authandautherization.security.UserPrincipal;
import com.innovativeintelli.authandautherization.util.MessageConstants;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

  
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, MessageConstants.USERNAME_ALREADY_EXIST),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, MessageConstants.EMAIL_ALREADY_EXIST),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException(MessageConstants.USER_ROLE_NOT_SET));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, MessageConstants.REGISTERED_SUCCESSFULLY));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/validateToken")
    public ResponseEntity<?> getTokenByCredentials(@Valid @RequestBody ValidateTokenRequest validateToken) {
    	 String username = null;
    	 String jwt =validateToken.getToken();

         if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
             Long userId = tokenProvider.getUserIdFromJWT(jwt);

             UserDetails userDetails = customUserDetailsService.loadUserById(userId);
             username = userDetails.getUsername();
             return ResponseEntity.ok(new ApiResponse(Boolean.TRUE,MessageConstants.VALID_TOKEN + username));
           }else {
        	   return new ResponseEntity(new ApiResponse(false, MessageConstants.INVALID_TOKEN),
                       HttpStatus.BAD_REQUEST);
           }
         
    }
}