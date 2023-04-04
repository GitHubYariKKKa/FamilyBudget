package com.Intership.FamilyBudget.controller;


import com.Intership.FamilyBudget.dto.LoginDTO;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/api/auth")
public class LoginController {
//    private final UserRepository userRepository;
//
//    private final WebAuthenticationManager authenticationManager;
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public LoginController(UserRepository userRepository, WebAuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<Object, Object>> login(@RequestBody LoginDTO loginDTO){
//        try{
//            String username = loginDTO.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));
//            User user = userRepository.findUserByEmail(username);
//
//            if(user == null){
//                throw new UsernameNotFoundException("User not found");
//            }
//
//            String token = jwtTokenProvider.createToken(user);
//
//            Map<Object, Object> response = new HashMap<>();
//            response.put("username", username);
//            response.put("token", token);
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (AuthenticationException e){
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
////        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
////            return "redirect:/home";
////        }
////        return "login-form";
//    }

}
