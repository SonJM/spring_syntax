package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 모든 요청에 ResponseBody를 붙이고 싶다면, RestController사용
@RequestMapping("hello")
@Controller
// 클래스 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
public class HelloController {
    // @responseBody가 없고,
    // return타입이 String이면 templates밑에 html파일 리턴(약속!)
    // data만을 return할 때는 @ResponseBody를 붙인다.
    @GetMapping("string")
    @ResponseBody
    // 컨트롤러에 있는 메소드는 사용자와 가장 가까이 있기 때문에 호출이 되는 경우가 거의 없다.
    // 고로 네이밍만 의미있게 잘~해주면 된다.
    public String helloString(){
        return "hello_string";
    }

    @GetMapping("json")
    public Hello helloJson(){
        return "screen";
    }

    // 1) parameter방식 : localhost:8080/hello/screen-model?key=value&key=value
    // 2) pathvariable방식 : localhost:8080/hello/screen-model/hongildong
    // 요즘은 2번의 방식이 좀 더 restful하다라고 평가받는다. (RestAPI의 동작방식에 좀 더 알맞은? 방법이다)
    @GetMapping("screen-model-param")
    // ?name=hongildong의 방식으로 호출(파라미터 호출 방식)
    public String helloScreenModelParam(@RequestParam(value = "name")String name, Model model){
        // 화면에 data를 넘기고싶을때는 model객체 사용
        // model에 key:value형식으로 전달
        model.addAttribute("myData", name);
        return "screen";
    }

    // pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어, 좀 더 Restful하다.(= RestFul API 디자인에 적합)
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model){
        int num = 1;
        model.addAttribute("myData", id+num);
        return "screen";
    }
}
