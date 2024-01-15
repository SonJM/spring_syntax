package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public class JpaMemberRepository implements MemberRepository{
    @Override
    public List<Member> members() {
        return null;
    }

    @Override
    public void memberCreate(Member member) {

    }

    @Override
    public Member findMemberById(int id) {
        return null;
    }
}
