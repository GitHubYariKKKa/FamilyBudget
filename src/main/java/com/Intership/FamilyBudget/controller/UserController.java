package com.Intership.FamilyBudget.controller;

import com.Intership.FamilyBudget.dto.UserRequestDTO;
import com.Intership.FamilyBudget.dto.UserResponseDTO;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.model.role.Role;
import com.Intership.FamilyBudget.service.RoleService;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping({"/create/user"})
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequestDTO){
        User status = userService.getAll().stream().filter(user -> user.getEmail().equals(userRequestDTO.getEmail()))
                .findFirst()
                .orElse(null);
        if (status==null){
            User user = new User();
            user.setName(userRequestDTO.getName());
            user.setLastName(userRequestDTO.getLastName());
            user.setSurName(userRequestDTO.getSurName());
            user.setBudget(0);
            user.setEmail(userRequestDTO.getEmail());
                Role role = roleService.readById(1);
            user.setRole(role);
            user.setPassword(userRequestDTO.getPassword());
            userService.create(user);
            return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new UserResponseDTO(status), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping({"/update/user/{id}"})
    public ResponseEntity<UserResponseDTO> update(@PathVariable(value = "id") int id,
                                                  @RequestBody UserRequestDTO userRequestDTO){
        if (userRequestDTO!=null){
            User user = new User();
            user.setId(id);
            user.setName(userRequestDTO.getName());
            user.setLastName(userRequestDTO.getLastName());
            user.setSurName(userRequestDTO.getSurName());
            user.setBudget(userRequestDTO.getBudget());
            user.setPassword(userRequestDTO.getPassword());
            user.setEmail(userRequestDTO.getEmail());
            user.setRole(roleService.readById(1));
            userService.update(user);
            return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
        }
        else {
             throw new NullPointerException();
        }
    }

    @GetMapping({"/user/{user_id}"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "user_id") int user_id){
        User user = userService.readById(user_id);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findUser(@RequestParam(value = "family_id") int family_id,
                                                         @RequestParam(value = "isOrdered") boolean isOrdered, @RequestParam(value = "name") String name,
                                                         @RequestParam(value = "surName") String surName, @RequestParam(value = "lastName") String lastName){
        List<UserResponseDTO> users;
            if(family_id != 0) {
                if (isOrdered) {
                    users = userService.getAllOrderedByBudget(family_id).stream()
                            .map(UserResponseDTO::new).collect(Collectors.toList());
                } else {
                    users = userService.getAllByFamilyId(family_id).stream()
                            .map(UserResponseDTO::new).collect(Collectors.toList());
                }
            }
            else if (name!=null && surName==null && lastName==null){
                users = userService.readByName(name).stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }
            else if (name==null && surName!=null && lastName==null){
                users = userService.readByLastName(surName).stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }
            else if (name==null && surName==null && lastName!=null){
                users = userService.readByThirdName(lastName).stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }
            else if (name!=null && surName!=null && lastName==null){
                users = userService.readByNameAndSurName(name, surName).stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }
            else if (name == null && surName != null){
                users = userService.readBySurNameAndLastName(surName, lastName).stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }else {
                users = userService.getAll().stream()
                        .map(UserResponseDTO::new).collect(Collectors.toList());
            }
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @DeleteMapping({"/people/delete/{id}"})
    public void delete(@PathVariable(value = "id") int id){
        userService.delete(id);
    }




}
