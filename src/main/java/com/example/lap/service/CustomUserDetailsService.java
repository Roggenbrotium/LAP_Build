package com.example.lap.service;

import com.example.lap.dao.WebUser;
import com.example.lap.dao.WebUserRepository;
import com.example.lap.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Used to overwrite the default UserDetailsService
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WebUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser user = userRepo.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

}
