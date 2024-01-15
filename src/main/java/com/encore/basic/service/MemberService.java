package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDTO;
import com.encore.basic.domain.MemberResponseDTO;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
// service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
// 스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC컨테이너가 스프링빈을 관리(빈을 생성, 의존성 주입)
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository){
        this.memberRepository = memoryMemberRepository;
    }
    static int total_id = 1;

    // 1) List<Member>를 리턴
    public List<MemberResponseDTO> members(){
        List<Member> members = memberRepository.members();
        List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
            memberResponseDTO.setId(member.getId());
            memberResponseDTO.setName(member.getName());
            memberResponseDTO.setEmail(member.getEmail());
            memberResponseDTO.setPassword(member.getPassword());
            memberResponseDTOS.add(memberResponseDTO);
        }
        return memberResponseDTOS;
    }

    // 2) Member등록 메서드
    public void memberCreate(MemberRequestDTO memberRequestDTO){
        LocalDateTime now = LocalDateTime.now();
        total_id += 1;
        Member member = new Member(
                    total_id,
                    memberRequestDTO.getName(),
                    memberRequestDTO.getEmail(),
                    memberRequestDTO.getPassword(),
                    now
        );
        memberRepository.memberCreate(member);
    }

    // 3) Member조회 메서드
    public MemberResponseDTO findMemberById(int id){
        Member member =  memberRepository.findMemberById(id);
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(member.getId());
        memberResponseDTO.setName(member.getName());
        memberResponseDTO.setEmail(member.getEmail());
        memberResponseDTO.setPassword(member.getPassword());
        memberResponseDTO.setCreate_time(member.getCreate_time());
        return memberResponseDTO;
    }
}
