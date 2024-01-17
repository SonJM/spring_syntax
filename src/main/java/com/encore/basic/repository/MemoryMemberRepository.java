package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    static int total_id;
    private final List<Member> memberDB;
    public MemoryMemberRepository(){
        memberDB = new ArrayList<>();
    }

    // 1) List<Member>를 리턴
    @Override
    public List<Member> findAll(){
        return memberDB;
    }

    // 2) Member등록 메서드
    @Override
    public Member save(Member member) {
        memberDB.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        for(Member member : memberDB){
            if(member.getId() == id) {
                return Optional.ofNullable(member);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Member member) {

    }
}
