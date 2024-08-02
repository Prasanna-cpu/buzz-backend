package com.kumar.springboot.Buzz.backend.Service.Implementation;

import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepository.findByEmail(username).orElse(null);

        if(user==null || user.isLogin_with_google()){
            throw new UsernameNotFoundException("User not found: "+username);
        }
        else{
            List<GrantedAuthority> authorities=new ArrayList<>();
            return new User(user.getEmail(),user.getPassword(),authorities);
        }
    }
}
