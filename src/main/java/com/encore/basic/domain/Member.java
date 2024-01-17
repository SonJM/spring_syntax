package com.encore.basic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
// entity어노테이션을 통해 mariaDB의 테이블 및 컬럼용 자동생성
// class명은 테이블명, 변수명은 컬럼명
@Entity
@NoArgsConstructor
public class Member {
    @Setter
    @Id
    // identity = auto_increment 설정, auto = JPA구현체가 자동으로 적절한 키생성 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // String은 DB의 varchar로 변환
    @Setter
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;

    @Column(name = "created_time") // name옵션을 통해 DB의 컬럼명 별도 지정 가능
    @ColumnDefault("current_timestamp")
    @CreationTimestamp
    private LocalDateTime create_time;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public void updateMember(String name, String password){
        this.name = name;
        this.password = password;
    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
