package com.Intership.FamilyBudget.security.jwt;

import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if(user==null) throw new UsernameNotFoundException("User with this email does not exists");
        return new SecurityUser(user);
    }
}
