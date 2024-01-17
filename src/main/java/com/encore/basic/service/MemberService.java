package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDTO;
import com.encore.basic.domain.MemberResponseDTO;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
// service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
// 스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC컨테이너가 스프링빈을 관리(빈을 생성, 의존성 주입)
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository){
        this.memberRepository = springDataJpaMemberRepository;
    }

    // 1) List<Member>를 리턴
    public List<MemberResponseDTO> members(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
            memberResponseDTO.setId(member.getId());
            memberResponseDTO.setName(member.getName());
            memberResponseDTO.setEmail(member.getEmail());
            memberResponseDTO.setPassword(member.getPassword());
            memberResponseDTO.setCreate_time(member.getCreate_time());
            memberResponseDTOS.add(memberResponseDTO);
        }
        return memberResponseDTOS;
    }

    // Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction적용
    // Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    // 2) Member등록 메서드
    @Transactional
    public void memberCreate(MemberRequestDTO memberRequestDTO){
            Member member = new Member(
                    memberRequestDTO.getName(),
                    memberRequestDTO.getEmail(),
                    memberRequestDTO.getPassword()
            );
            memberRepository.save(member);
        // Transection 테스트
//        Member member = new Member(
//                memberRequestDTO.getName(),
//                memberRequestDTO.getEmail(),
//                memberRequestDTO.getPassword()
//        );
//        memberRepository.save(member);
//        if(member.getName().equals("kim")){
//            throw new IllegalArgumentException();
//        }
    }

    // 3) Member조회 메서드
    public MemberResponseDTO findById(int id) throws EntityNotFoundException {
        Member member =  memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(member.getId());
        memberResponseDTO.setName(member.getName());
        memberResponseDTO.setEmail(member.getEmail());
        memberResponseDTO.setPassword(member.getPassword());
        memberResponseDTO.setCreate_time(member.getCreate_time());
        return memberResponseDTO;
    }

    public void delete(MemberResponseDTO memberDTO){
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }

    public void update(MemberRequestDTO memberRequestDTO){
        Member member = memberRepository.findById(memberRequestDTO.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateMember(memberRequestDTO.getName(), memberRequestDTO.getPassword());
        memberRepository.save(member);
    }
}
