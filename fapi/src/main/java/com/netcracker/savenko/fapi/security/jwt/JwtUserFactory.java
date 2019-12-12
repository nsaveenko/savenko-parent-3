package com.netcracker.savenko.fapi.security.jwt;


import com.netcracker.savenko.fapi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFlName(),
                user.getPassword(),
                user.getStatusUserByIdStatus(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(Arrays.asList("ROLE_" + user.getRoleUserByIdRole())))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream().map(SimpleGrantedAuthority::new
        ).collect(Collectors.toList());
    }

}
