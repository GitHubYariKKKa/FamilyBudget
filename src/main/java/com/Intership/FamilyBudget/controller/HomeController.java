package com.Intership.FamilyBudget.controller;

import com.Intership.FamilyBudget.dto.UserResponseDTO;
import com.Intership.FamilyBudget.forecast.WeatherService;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;

    private final WeatherService weatherService;

    public HomeController(UserService userService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> home() {
        List<UserResponseDTO> users = userService.getAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
