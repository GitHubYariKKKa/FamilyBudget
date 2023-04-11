package com.Intership.FamilyBudget.controller;

import com.Intership.FamilyBudget.dto.ShoppingRequestDTO;
import com.Intership.FamilyBudget.dto.ShoppingResponseDTO;
import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.service.FamilyService;
import com.Intership.FamilyBudget.service.ShoppingHistoryService;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final ShoppingHistoryService shoppingHistoryService;
    private final UserService userService;
    private final FamilyService familyService;

    public ShoppingController(ShoppingHistoryService shoppingHistoryService, UserService userService, FamilyService familyService) {
        this.shoppingHistoryService = shoppingHistoryService;
        this.userService = userService;
        this.familyService = familyService;
    }

    @PostMapping({"/create/{forWho}"})
    public ResponseEntity<ShoppingResponseDTO> create(@PathVariable(value = "forWho") int forWho, @RequestBody ShoppingRequestDTO shoppingRequestDTO){
        System.out.println(shoppingRequestDTO);
        if (shoppingRequestDTO!=null){
            ShoppingHistory shoppingHistory = new ShoppingHistory();
            shoppingHistory.setProductName(shoppingRequestDTO.getProductName());
            shoppingHistory.setPrice(shoppingRequestDTO.getPrice());
            shoppingHistory.setUser(userService.readById(forWho));
            shoppingHistory.setBuyDate(shoppingRequestDTO.getBuyDate());
            shoppingHistory.setDone(false);
            shoppingHistoryService.create(shoppingHistory);
            return new ResponseEntity<>(new ShoppingResponseDTO(shoppingHistory), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping({"/update/shopping/{id}"})
    public ResponseEntity<ShoppingResponseDTO> update(@PathVariable(value = "id") int id) throws IOException {
        System.out.println(id);
        ShoppingHistory shoppingHistory = shoppingHistoryService.readByIdInThisFamily(id);
        if (shoppingHistory != null) {
            if (!shoppingHistory.isDone()) {
                shoppingHistory.setDone(true);
                shoppingHistoryService.update(shoppingHistory);
                Family family = familyService.getFamilyByShoppingId(id);
                int newBudget = family.getBudget() - shoppingHistory.getPrice();
                family.setActualBudget(newBudget);
                familyService.update(family);
                return new ResponseEntity<>(new ShoppingResponseDTO(shoppingHistory), HttpStatus.OK);
            }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping({"/delete/{id}"})
    public void delete(@PathVariable(value = "id") int id){
        shoppingHistoryService.delete(id);
    }
}
