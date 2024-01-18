package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/response/entity")
public class ResponseEntityController {

    // @ResponseStatus 어노테이션 방식
    @GetMapping("responseStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus(){
        return "OK";
    }

    // @ResponseStatus 어노테이션을 사용하면 상태 코드를 강제할 수 있다.
    @GetMapping("responseStatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        Member member = new Member("name", "name@naver.com","123456");
        return member;
    }

    // ResponseEntity객체를 직접 생성한 방식
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
    Member member = new Member("name", "name@naver.com", "123456");
    return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    // ResponseEntity<String>일 경우 text/html로 설정
    @GetMapping("custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 ID입니다.</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    // map형태의 메시지 커스텀
    public static ResponseEntity<Map<String, Object>> errResponse(HttpStatus httpStatus, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("status message", httpStatus.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, httpStatus);
    }

    // status 201, message : 객체
    public static ResponseEntity<Map<String, Object>> responseMessage(Object object, HttpStatus httpStatus){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("message", object);
        return new ResponseEntity<>(body, httpStatus);
    }

    // 메서드 체이닝 : ResponseEntity의 클래스메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<Member> chaining2(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
