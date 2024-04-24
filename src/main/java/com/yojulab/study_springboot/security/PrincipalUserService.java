package com.yojulab.study_springboot.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.yojulab.study_springboot.service.UsersService;

@Service
public class PrincipalUserService implements UserDetailsService {

    @Autowired
    UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map dataMap = new HashMap<>();
        dataMap.put("EMAIL", username);
        Map resultMap = (Map) usersService.selectByUIDWithAuths(dataMap);

        PrincipalUser principalUser = new PrincipalUser(resultMap);

        return principalUser;
    }
}
