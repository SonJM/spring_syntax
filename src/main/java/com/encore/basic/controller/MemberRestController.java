package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDTO;
import com.encore.basic.domain.MemberResponseDTO;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("members/create")
    public String PostRegister(@RequestBody MemberRequestDTO memberRequestDTO){
        memberService.memberCreate(memberRequestDTO);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDTO> members() {
        List<MemberResponseDTO> memberResponseDTOS = memberService.members();
        return memberResponseDTOS;
    }

    @GetMapping("members/find/{id}")
    public ResponseEntity<Map<String, Object>> getFind(@PathVariable int id){
        MemberResponseDTO memberResponseDTO = null;
        try{
            memberResponseDTO = memberService.findById(id);
            return ResponseEntityController.responseMessage(memberResponseDTO, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntityController.errResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("members/delete/{id}")
    public MemberResponseDTO deleteMember(@PathVariable int id){
        MemberResponseDTO memberResponseDTO = memberService.findById(id);
        memberService.delete(memberResponseDTO);
        return memberResponseDTO;
    }

    @PatchMapping("members/update")
    public MemberResponseDTO modifyMember(@RequestBody MemberRequestDTO memberRequestDTO){
        memberService.update(memberRequestDTO);
        return memberService.findById(memberRequestDTO.getId());
    }
}
