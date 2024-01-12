package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 회원가입(글쓰기), 회원목록조회(글 목록 조회) + 회원상세조회(글 상세 조회)
// + 회원수정 + 회원삭제
@Controller
public class MemberController {
    @GetMapping("/members")
    public String searchMembers(){
        return "member-list";
    }

    @GetMapping("/member/create/screen")
    public String Register(){
        return "/member/member-create";
    }

    @PostMapping("/member/create")
    public String getUserInfo(Member member){
        System.out.println(member);
        return "ok";
    }
}
