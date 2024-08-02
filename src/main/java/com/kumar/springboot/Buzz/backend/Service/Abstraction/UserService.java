package com.kumar.springboot.Buzz.backend.Service.Abstraction;

import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;

import java.util.List;


public interface UserService {
    public Users findUserById(Long userId) throws UserException;

    public Users findUserProfileByJwt(String jwt) throws UserException;

    public Users updateUser(Long userId,Users user) throws UserException;

    public Users followUser(Long userId,Users user) throws UserException;

    public List<Users> searchUser(String query);





}
