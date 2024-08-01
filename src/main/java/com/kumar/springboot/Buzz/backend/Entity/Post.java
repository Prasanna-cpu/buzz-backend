package com.kumar.springboot.Buzz.backend.Entity;


import jakarta.persistence.*;
import lombok.*;
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
@Entity(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users users;

    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @OneToMany
    private List<Post> replies=new ArrayList<>();

    @ManyToMany
    private List<Users> repostUser=new ArrayList<>();

    @ManyToOne
    private Post replyFor;

    private String image;

    private String video;

    @Column(name="is_reply")
    private boolean isReply;

    @Column(name="is_post")
    private boolean isPost;

//    @CreatedDate
//    @Column(name = "created_at",nullable = false,updatable = false)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at",nullable = false)
//    private Date updatedAt;




}
