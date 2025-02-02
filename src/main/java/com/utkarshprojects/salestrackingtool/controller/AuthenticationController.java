package com.utkarshprojects.salestrackingtool.controller;

import com.utkarshprojects.salestrackingtool.dbmodel.User;
import com.utkarshprojects.salestrackingtool.dtomodel.UserSignInRequest;
import com.utkarshprojects.salestrackingtool.dtomodel.UserSignInResponse;
import com.utkarshprojects.salestrackingtool.dtomodel.UserSignUpRequest;
import com.utkarshprojects.salestrackingtool.repository.UserRepository;
import com.utkarshprojects.salestrackingtool.response.ServiceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8080/"})
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private static final Logger log = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(path = "/sign-up")
    public ServiceResponse signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (userRepository.findByEmail(userSignUpRequest.getEmail()).isPresent()) {
            serviceResponse.setSuccess(false);
            serviceResponse.setResponse("User already exists");
            return serviceResponse;
        }
        User newUser = new User();
        newUser.setUsername(userSignUpRequest.getUsername());
        newUser.setEmail(userSignUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        userRepository.save(newUser);
        serviceResponse.setSuccess(true);
        serviceResponse.setResponse("Sign up successful");
        return serviceResponse;
    }

    @PostMapping(path = "/sign-in")
    public @ResponseBody UserSignInResponse signIn(@RequestBody UserSignInRequest userSignInRequest) {
        UserSignInResponse userSignInResponse = new UserSignInResponse();
        Optional<User> userOptional = userRepository.findByEmail(userSignInRequest.getEmail());
        if (userOptional.isPresent() && passwordEncoder.matches(userSignInRequest.getPassword(), userOptional.get().getPassword())) {
            userSignInResponse.setSuccess(true);
            userSignInResponse.setMessage("Login successful");
            userSignInResponse.setEmail(userSignInRequest.getEmail());
        } else {
            userSignInResponse.setSuccess(false);
            userSignInResponse.setMessage("Incorrect username or password");
        }
        return userSignInResponse;
    }
}

