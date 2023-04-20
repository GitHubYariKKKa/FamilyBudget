package com.Intership.FamilyBudget.controller;

import com.Intership.FamilyBudget.dto.*;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.model.role.Role;
import com.Intership.FamilyBudget.service.RoleService;
import com.Intership.FamilyBudget.service.ShoppingHistoryService;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ShoppingHistoryService shoppingHistoryService;

    public UserController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder, ShoppingHistoryService shoppingHistoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingHistoryService = shoppingHistoryService;
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
            user.setAddress(userRequestDTO.getAddress());
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            user.setBudget(0);
            user.setEmail(userRequestDTO.getEmail());
                Role role = roleService.readById(1);
            user.setRole(role);
            byte[] img = new byte[4];
            user.setProfileImage(img);
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setPostalIndex(userRequestDTO.getPostalIndex());
            userService.create(user);
            return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new UserResponseDTO(status), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('USER') and authentication.details.id == #id")
    @PatchMapping({"/update/user/{id}"})
    public ResponseEntity<UserResponseDTO> update(@PathVariable(value = "id") int id,
                                                  @RequestBody UserRequestDTO userRequestDTO) throws IOException {
        if (userRequestDTO!=null){
            System.out.println(userRequestDTO);
            System.out.println("ID is:"+id);
            User user = new User();
            user.setId(id);
            user.setName(userRequestDTO.getName());
            user.setLastName(userRequestDTO.getLastName());
            user.setSurName(userRequestDTO.getSurName());
            user.setAddress(userRequestDTO.getAddress());
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setEmail(userRequestDTO.getEmail());
            user.setRole(roleService.readById(1));
            user.setFamily(userService.readById(id).getFamily());
            user.setPostalIndex(userRequestDTO.getPostalIndex());
            user.setProfileImage(new byte[4]);
            userService.update(user);
            return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping({"/check_data"})
    public ResponseEntity<ErrorResponseDTO> checkError(@RequestBody @Valid UserRequestDTO userRequestDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Validation failed. " +
                    "Please check the request parameters and try again.", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(errorResponseDTO);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/user/{user_id}"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "user_id") int user_id){
        User user = userService.readById(user_id);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/user/email/{email}"})
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable(value = "email") String email){
        User user = userService.readByEmail(email);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findUsersOutOffFamily(){
        List<UserResponseDTO> users = userService.getAllOutOffFamily().stream()
                .map(UserResponseDTO::new).collect(Collectors.toList());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/inFamily/{family_id}"})
    public ResponseEntity<List<UserResponseDTO>> findUsersInFamily(@PathVariable(value = "family_id") int family_id){
        List<UserResponseDTO> users = userService.getAllInFamily(family_id).stream()
                .map(UserResponseDTO::new).collect(Collectors.toList());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping({"/people/delete/{id}"})
    public void delete(@PathVariable(value = "id") int id){
        userService.delete(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping({"/user/{user_id}/remover_from_family"})
    public void removerUserFromFamily(@PathVariable(value = "user_id") int id){
        userService.removeUserFromThisFamily(id);
        shoppingHistoryService.clearShopping(id);
    }

    @PreAuthorize("hasAuthority('PARENT')")
    @PostMapping({"/user/{user_id}/add_to_family/{family_id}"})
    public void addUserToFamily(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "family_id") int family_id){
        System.out.println(user_id);
        System.out.println(family_id);
        userService.addUserToThisFamily(user_id,family_id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/user/{user_id}/spending"})
    public ResponseEntity<List<SpendPerDayDTO>> getMySpending(@PathVariable(value = "user_id") int id){
        List<SpendPerDayDTO> spendPerDayDTOs = shoppingHistoryService.getUsersSpendingPerDayStatistic(id);
        if (spendPerDayDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spendPerDayDTOs,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/{family_id}/spending"})
    public ResponseEntity<List<SortedUsersBySpendingDTO>> getUsersAndSpending(@PathVariable(value = "family_id") int id){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(30);
        List<SortedUsersBySpendingDTO> spendPerDayDTOs = userService.getSortedUsersBySpending(startDate,endDate,id);
        if (spendPerDayDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spendPerDayDTOs,HttpStatus.OK);
    }
}
