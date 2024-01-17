package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// spring data jpa의 기본기능을 쓰기 위해서는 JPARepository를 상속해야함
// 상속시에 entity명과 해당 entity의 pk타입을 명시
// 구현클래스와 스펙은 SimpleJpaRepository 클래스에 있고,
// 실질적인 구동상황에서 hibernate 구현체에 동작 위임.
public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {
}
