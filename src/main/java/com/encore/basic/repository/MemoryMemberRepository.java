package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private final List<Member> memberDB;
    public MemoryMemberRepository(){
        memberDB = new ArrayList<>();
    }

    // 1) List<Member>를 리턴
    @Override
    public List<Member> members(){
        return memberDB;
    }

    // 2) Member등록 메서드
    @Override
    public void memberCreate(Member member){
        memberDB.add(member);
    }

    @Override
    public Member findMemberById(int id) {
        for(Member member : memberDB){
            if(member.getId() == id) return member;
        }
        return null;
    }
}
