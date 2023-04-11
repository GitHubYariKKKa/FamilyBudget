package com.Intership.FamilyBudget.controller;

import com.Intership.FamilyBudget.dto.*;
import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.model.role.Role;
import com.Intership.FamilyBudget.service.FamilyService;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;
    private final UserService userService;

    public FamilyController(FamilyService familyService, UserService userService) {
        this.familyService = familyService;
        this.userService = userService;
    }

    @PostMapping({"/create/family"})
    public ResponseEntity<FamilyResponseDTO> create(@RequestBody FamilyRequestDTO familyRequestDTO){
        System.out.println(familyRequestDTO);
        Family status = familyService.getAll().stream().filter(family -> family.getName().equals(familyRequestDTO.getName()))
                .findFirst()
                .orElse(null);
        if (status==null){
            Family family = new Family();
            family.setName(familyRequestDTO.getName());
            family.setBudget(familyRequestDTO.getBudget());
            family.setUsers(new ArrayList<>());
            familyService.create(family);
            return new ResponseEntity<>(new FamilyResponseDTO(family), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new FamilyResponseDTO(status), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping({"/update/family/{id}"})
    public ResponseEntity<FamilyResponseDTO> update(@PathVariable(value = "id") int id,
                                                  @RequestBody FamilyRequestDTO familyRequestDTO) throws IOException {
      if (familyRequestDTO!=null){
          Family family = new Family();
          family.setId(id);
          family.setName(familyRequestDTO.getName());
          family.setBudget(familyRequestDTO.getBudget());
          familyService.update(family);
            return new ResponseEntity<>(new FamilyResponseDTO(family), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<FamilyResponseDTO>> findFamily(){
        List<FamilyResponseDTO> families = familyService.getAll().stream()
                .map(FamilyResponseDTO::new).toList();

        if (families.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(families, HttpStatus.OK);

    }

    @GetMapping({"/family/check/budget/{family_id}"})
    public ResponseEntity<CheckBudgetDTO> checkFamilyBudget(@PathVariable(value = "family_id") int family_id){
        Family family = familyService.readById(family_id);
        if (family!=null) {
            if (familyService.checkBudget(family.getBudget(), family.getActualBudget())) {
                return new ResponseEntity<>(new CheckBudgetDTO(true, "Увага! Залишилось менше 10% бюджету на місяць"), HttpStatus.OK);
            } else return new ResponseEntity<>(new CheckBudgetDTO(false, ""), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"/family/{family_id}"})
    public ResponseEntity<FamilyResponseDTO> getFamilyById(@PathVariable(value = "family_id") int family_id){
        Family family = familyService.readById(family_id);
        return new ResponseEntity<>(new FamilyResponseDTO(family), HttpStatus.OK);
    }

    @DeleteMapping({"/family/delete/{id}"})
    public void delete(@PathVariable(value = "id") int id){
        familyService.delete(id);
    }
}
