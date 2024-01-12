package com.encore.basic.domain;

import lombok.Data;
// 도메인 package == 엔티티(entity) package


@Data // Getter, setter 및 toString, equals등 사전 구형
public class Hello {
    private String name;
    private String email;
    private String password;
}