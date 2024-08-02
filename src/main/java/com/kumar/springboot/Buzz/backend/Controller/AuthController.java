package com.kumar.springboot.Buzz.backend.Controller;


import com.kumar.springboot.Buzz.backend.Configuration.JwtProvider;
import com.kumar.springboot.Buzz.backend.DTO.Response.AuthResponse;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Entity.Verification;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;
import com.kumar.springboot.Buzz.backend.Repository.UserRepository;
import com.kumar.springboot.Buzz.backend.Service.Implementation.CustomUserDetailsServiceImplementation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final CustomUserDetailsServiceImplementation customUserDetailsServiceImplementation;


    private Authentication authenticate(String username,String password){
        UserDetails userDetails=customUserDetailsServiceImplementation.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());



    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Users user) throws UserException {

        System.out.println(user);

        String email=user.getEmail();
        String password=user.getPassword();
        String fullName= user.getFullName();
        String birthDate=user.getBirthDate();

        Users existingUser=userRepository.findByEmail(email).orElse(null);

        if(existingUser!=null){
            throw new UserException("Email is already taken");
        }

        Users newUser=new Users();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        newUser.setBirthDate(birthDate);
        newUser.setVerification(new Verification());

        Users savedUser=userRepository.save(newUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token= jwtProvider.generateToken(authentication);

        AuthResponse response=new AuthResponse();
        response.setJwt(token);
        response.setStatus(true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> SignInHandler(@RequestBody Users user){
        String username=user.getEmail();
        String password=user.getPassword();

        Authentication authentication=authenticate(username,password);

        String token= jwtProvider.generateToken(authentication);

        AuthResponse response=new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(response,HttpStatus.ACCEPTED);
    }




}
