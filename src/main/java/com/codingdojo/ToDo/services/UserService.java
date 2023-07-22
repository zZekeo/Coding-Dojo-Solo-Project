package com.codingdojo.ToDo.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.ToDo.models.LoginUser;
import com.codingdojo.ToDo.models.User;
import com.codingdojo.ToDo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User register(User newUser, BindingResult results) {
		
		// check to see if the email already exist in the database
		if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
			results.rejectValue("email", "Duplicate", "Email already exist");
		}
		
		if(results.hasErrors()) {
			return null;
		}
        
		// check to see if password and confirm password does not match
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			results.rejectValue("confirm", "Confirm", "Password and confirm password must match");
		}

		if(results.hasErrors()) {
			return null;
		}
		
		// hash and set password, save user to DB
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		return userRepository.save(newUser);
    }
	
    public User login(LoginUser newLoginObject, BindingResult results) {
        // FIND THE USER IN THE DB
    	if (!this.checkEmail(newLoginObject.getEmail())) {
    		results.rejectValue("email", "Non-existant", "INVALID CREDENTIALS");
    	}
    	
    	if (results.hasErrors()) {
    		return null;
    	}
        
    	// CHECK TO SEE IF PASSWORD MATCHES
    	User user = userRepository.findByEmail(newLoginObject.getEmail()).orElse(null);
    	
    	if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    		results.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
    	if (results.hasErrors()) {
    		return null;
    	}
    	
    	return user;
    }
    
    public boolean checkEmail(String email) {
    	Optional<User> user = userRepository.findByEmail(email);
    	
    	if (user.isPresent()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public User getOneUser(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
}