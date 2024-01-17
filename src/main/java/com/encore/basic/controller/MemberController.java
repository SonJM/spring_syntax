package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDTO;
import com.encore.basic.domain.MemberResponseDTO;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

// 회원가입(글쓰기), 회원목록조회(글 목록 조회) + 회원상세조회(글 상세 조회)
// + 회원수정 + 회원삭제
@Controller
public class MemberController {
    /*@Autowired
    // 의존성 주입(DI) 방법 1 => 필드 주입방식
    // 초기화를 시킬 수 없기 때문에 final 키워드를 사용하지 못한다.
    private MemberService memberService;*/

    // 의존성 주입 방법 2 => 생성자 주입 방식이고, 가장 많이 사용하는 방법
    // 장점 : final 키워드를 통해 상수로 사용 가능, **다형성 구현 가능, 순환참조방지
    // 생성자가 1개밖에 없을 때에는 Autowired 생략가능
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    // 의존성 주입 방법 3 => @RequiredArgsConstructor를 이용한 방식
    // @RequiredArgsConstructor : @NonNull 어노테이션이 붙어있는 필드 또는 초기화되지 않은 final 필드를 대상으로 생성자 생성
    /*private final MemberService memberService;*/

    @GetMapping("/")
    public String home(){
        return "member/header";
    }

    @GetMapping("members")
    public String members(Model model) {
        model.addAttribute("memberList", memberService.members());
        return "member/member-list";
    }

    @GetMapping("members/create")
    public String GetRegister(){
        return "member/member-create";
    }

    @PostMapping("members/create")
    public String PostRegister(MemberRequestDTO memberRequestDTO){
        memberService.memberCreate(memberRequestDTO);
        return "redirect:/members";
        // 트랜잭션 및 예외처리 테스트
//        try{
//            memberService.memberCreate(memberRequestDTO);
//            // url 리다이렉트
//            return "redirect:/members";
//        } catch(IllegalArgumentException e){
//            return "member/404-error-page";
//        }
    }

    @GetMapping("members/find")
    public String getFind(@RequestParam(value = "id")int id, Model model){
        try{
            MemberResponseDTO memberResponseDTO = memberService.findById(id);
            model.addAttribute("findData",  memberResponseDTO);
            return "member/member-find";
        } catch (EntityNotFoundException e){
            return "member/404-error-page";
        }
    }

    @GetMapping("members/delete")
    public String deleteMember(@RequestParam(value = "id")int id){
        try{
            MemberResponseDTO memberResponseDTO = memberService.findById(id);
            memberService.delete(memberResponseDTO);
            return "redirect:/members";
        }catch(EntityNotFoundException e){
            return "member/404-error-page";
        }
    }

    @PostMapping("members/update")
    public String modifyMember(MemberRequestDTO memberRequestDTO){
        memberService.update(memberRequestDTO);
        return "redirect:/members/find?id=" + memberRequestDTO.getId();
    }
}
