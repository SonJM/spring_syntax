package com.encore.basic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ToString
public class MemberResponseDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;
}
