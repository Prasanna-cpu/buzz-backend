package com.kumar.springboot.Buzz.backend.Entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;


public class Verification {


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private final boolean status=false;
    private LocalDateTime startedAt;
    private LocalDateTime endsAt;
    private String planType;

}
