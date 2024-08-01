package com.kumar.springboot.Buzz.backend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Post post;

//    @CreatedDate
//    @Column(name = "created_at",nullable = false,updatable = false)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at",nullable = false)
//    private Date updatedAt;
}
