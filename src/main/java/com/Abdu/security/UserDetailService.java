package com.Abdu.security;

import com.Abdu.account.models.AccountEntity;
import com.Abdu.account.models.AppUser;
import com.Abdu.account.models.Role;
import com.Abdu.account.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountEntity user = userRepository.findByEmail(userName);
        AppUser userDetails = new AppUser();
        userDetails.setUsername(user.getEmail());
        userDetails.setAccountNonExpired(true);
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(true);
        userDetails. setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        userDetails.setAuthorities(authorities);
        return userDetails;
    }


}
