package com.kumar.springboot.Buzz.backend.Service.Implementation;

import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;
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
    /**
     * @param userId
     * @return
     * @throws UserException
     */
    @Override
    public Users findUserById(Long userId) throws UserException {
        return null;
    }

    /**
     * @param jwt
     * @return
     * @throws UserException
     */
    @Override
    public Users findUserProfileByJwt(String jwt) throws UserException {
        return null;
    }

    /**
     * @param userId
     * @param user
     * @return
     * @throws UserException
     */
    @Override
    public Users updateUser(Long userId, Users user) throws UserException {
        return null;
    }

    /**
     * @param userId
     * @param user
     * @return
     * @throws UserException
     */
    @Override
    public Users followUser(Long userId, Users user) throws UserException {
        return null;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public List<Users> searchUser(String query) {
        return List.of();
    }
}
