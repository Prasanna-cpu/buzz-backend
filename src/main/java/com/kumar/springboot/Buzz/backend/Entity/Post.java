package com.kumar.springboot.Buzz.backend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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

    private boolean is_liked = false;
    private boolean is_repost=false;

    private LocalDateTime createdAt;

//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    public void prePersist() {
//        createdAt = LocalDateTime.now();
//        updatedAt = createdAt;
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedAt = LocalDateTime.now();
//    }




}
