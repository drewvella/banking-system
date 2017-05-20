package com.demo.bank.service.impl;

import com.demo.bank.data.model.BankingUser;
import com.demo.bank.data.repository.BankingUserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author andrewvella
 * @since  21/05/2017.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    BankingUserRepository bankingUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Search for banking user in db
        BankingUser bankingUser = bankingUserRepository.findByUsername(username);
        if(bankingUser == null) {
            //Throw not found exception if it doesnt exist
            throw new UsernameNotFoundException(username + " does not exist");
        }
        //Map all banking user roles to spring security authorities
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        bankingUser.getRoles().forEach(bankingUserRole -> grantedAuthorities.add(new SimpleGrantedAuthority(bankingUserRole.getRole())));
        //map all data to spring security user
        return new User(bankingUser.getUsername(),bankingUser.getPassword(),bankingUser.getEnabled(),bankingUser.getEnabled(),bankingUser.getEnabled(),bankingUser.getEnabled(),grantedAuthorities);
    }
}
