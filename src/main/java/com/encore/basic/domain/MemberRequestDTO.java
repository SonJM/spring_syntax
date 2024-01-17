package com.encore.basic.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberRequestDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_time;
}
