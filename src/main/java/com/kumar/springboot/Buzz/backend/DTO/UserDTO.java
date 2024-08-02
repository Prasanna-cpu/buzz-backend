package com.kumar.springboot.Buzz.backend.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDTO {


    private Long id;

    private String fullName;

    private String email;

    private String image;

    private String location;

    private String website;

    private String birthDate;

    private String mobile;

    private String backgroundImage;

    private String bio;

    private boolean req_user;

    private boolean login_with_google;

    private List<UserDTO> followers=new ArrayList<>();

    private List<UserDTO> following=new ArrayList<>();

    private boolean followed;

    private boolean isVerified;




}
