package com.kumar.springboot.Buzz.backend.Service.Implementation;

import com.kumar.springboot.Buzz.backend.Configuration.JwtProvider;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;
import com.kumar.springboot.Buzz.backend.Repository.UserRepository;
import com.kumar.springboot.Buzz.backend.Service.Abstraction.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    /**
     * @param userId
     * @return
     * @throws UserException
     */
    @Override
    public Users findUserById(Long userId) throws UserException {
        Users user=userRepository.findById(userId).orElseThrow(()->new UserException("User not found with id:"+userId));
        return user;
    }

    /**
     * @param jwt
     * @return
     * @throws UserException
     */
    @Override
    public Users findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromToken(jwt);
        Users user=userRepository.findByEmail(email).orElseThrow(()->new UserException("User not found with email:"+email));
        return user;
    }

    /**
     * @param userId
     * @param user
     * @return
     * @throws UserException
     */
    @Override
    public Users updateUser(Long userId, Users user) throws UserException {
        Users target=findUserById(userId);

        if(user.getFullName()!=null){
            target.setFullName(user.getFullName());
        }
        if(user.getBirthDate()!=null){
            target.setBirthDate(user.getBirthDate());
        }

        if(user.getBackgroundImage()!=null){
            target.setBackgroundImage(user.getBackgroundImage());
        }

        if(user.getImage()!=null){
            target.setBackgroundImage(user.getBackgroundImage());
        }

        if(user.getLocation()!=null){
            target.setLocation(user.getLocation());
        }

//        if(user.getMobile()!=null){
//            target.setMobile(user.getImage());
//        }

        if(user.getWebsite()!=null){
            target.setWebsite(user.getWebsite());
        }

        if(user.getBio()!=null){
            target.setBio(user.getBio());
        }

        userRepository.save(target);

        return target;

    }

    /**
     * @param userId
     * @param user
     * @return
     * @throws UserException
     */
    @Override
    public Users followUser(Long userId, Users user) throws UserException {
        Users followToUser=findUserById(userId);

        if(user.getFollowing().contains(followToUser) && followToUser.getFollowers().contains(user)){
            user.getFollowing().remove(followToUser);
            followToUser.getFollowers().remove(user);
        }
        else{
            user.getFollowing().add(followToUser);
            followToUser.getFollowers().add(user);
        }


        userRepository.save(user);
        userRepository.save(followToUser);
        return followToUser;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public List<Users> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
