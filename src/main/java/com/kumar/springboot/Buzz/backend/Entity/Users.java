package com.kumar.springboot.Buzz.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name="location")
    private String location;

    @Column(name="birth_date")
    private String birthDate;

    @Column(name="website")
    private String website;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="image")
    private String image;

    @Column(name="mobile")
    private String mobile;

    @Column(name="background_image")
    private String backgroundImage;

    @Column(name="bio")
    private String bio;


    private boolean req_user;

    private boolean login_with_google;

    private boolean is_req_user=false;

    @JsonIgnore
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Post> post=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="user_followers",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="follower_id")
    )
    private List<Users> followers=new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private List<Users> following=new ArrayList<>();

    @Embedded
    private Verification verification;

//    @CreatedDate
//    @Column(name = "created_at",nullable = false,updatable = false)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at",nullable = false)
//    private Date updatedAt;





}
