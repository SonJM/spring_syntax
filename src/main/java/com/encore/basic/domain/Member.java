package com.encore.basic.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private String name;
    private String email;
    private String password;
}
