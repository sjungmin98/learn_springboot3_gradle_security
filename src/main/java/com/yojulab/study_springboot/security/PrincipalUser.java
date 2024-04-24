package com.yojulab.study_springboot.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalUser implements UserDetails {

    private Map userInfo;

    public String getMemberName() {
        return (String) userInfo.get("NAME");
    }

    public PrincipalUser(Map userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        List<Map<String, Object>> resultList = (List) userInfo.get("resultList");
        for (Map item : resultList) {
            collections.add(new SimpleGrantedAuthority((String) item.get("AUTH_NAME")));
        }
        return collections;
    }

    @Override
    public String getPassword() {
        return (String) userInfo.get("PASSWORD");
    }

    @Override
    public String getUsername() {
        return (String) userInfo.get("EMAIL");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
