package com.cisco.clmsbackend.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.clmsbackend.model.Admin;
import com.cisco.clmsbackend.model.User;
import com.cisco.clmsbackend.repository.AdminRepository;
import com.cisco.clmsbackend.security.UserPrincipal;

@Service("customerUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null) {
        	user.setRoles(new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        } else {
        	user.setRoles(new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        }
        return UserPrincipal.create(user);
    }
}