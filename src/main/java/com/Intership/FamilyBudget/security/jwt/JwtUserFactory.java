package com.Intership.FamilyBudget.security.jwt;


import com.Intership.FamilyBudget.model.User;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static SecurityUser create(User user){
        return new SecurityUser(user);
    }

}
