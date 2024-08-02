package com.kumar.springboot.Buzz.backend.Mapper;

import com.kumar.springboot.Buzz.backend.DTO.UserDTO;
import com.kumar.springboot.Buzz.backend.Entity.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapper {

    private static List<UserDTO> toUserDTOs(List<Users> followers){

        List<UserDTO> userDTOs = new ArrayList<>();

        for(Users user:followers){
            UserDTO userDTO=new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullName(user.getFullName());
            userDTO.setImage(user.getImage());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public static UserDTO mapToUserDTO(Users user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setBio(user.getBio());
        userDTO.setFullName(user.getFullName());
        userDTO.setImage(user.getImage());
        userDTO.setBackgroundImage(user.getBackgroundImage());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setMobile(user.getMobile());
        userDTO.setLocation(user.getLocation());
        userDTO.setWebsite(user.getWebsite());
        userDTO.setFollowers(toUserDTOs(user.getFollowers()));
        userDTO.setFollowing(toUserDTOs(user.getFollowing()));
        userDTO.setLogin_with_google(user.isLogin_with_google());
//        userDTO.setVerified(false);



        return userDTO;

    }

}
