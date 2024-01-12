package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 모든 요청에 ResponseBody를 붙이고 싶다면, RestController사용
@RequestMapping("/hello")
@Controller
// 클래스 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
public class HelloController {
    // @responseBody가 없고,
    // return타입이 String이면 templates밑에 html파일 리턴(약속!)
    // data만을 return할 때는 @ResponseBody를 붙인다.
//    @GetMapping("string")
    @RequestMapping(value = "screen", method = RequestMethod.GET)
    // 컨트롤러에 있는 메소드는 사용자와 가장 가까이 있기 때문에 호출이 되는 경우가 거의 없다.
    // 고로 네이밍만 의미있게 잘~해주면 된다.
    public String helloString(){
        return "screen";
    }

    @GetMapping("json")
    @ResponseBody
    public Hello helloJson(){
        Hello hello = new Hello();
        hello.setName("son");
        hello.setEmail("son@naver.com");
        hello.setPassword("1234");
        return hello;
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
        model.addAttribute("myData", id);
        return "screen";
    }

    // Form태그로 x-www 데이터 처리
    @GetMapping("form-screen")
    public String formScreen(){return "hello-form-screen";}
    @PostMapping("form-post-handle")
    @ResponseBody
    // body의 데이터 형태가 ?key1=value1&key2=value2
    public String formPostHandle(@RequestParam(value = "name")String name,
                                 @RequestParam(value = "email")String email,
                                 @RequestParam(value = "password")String password){
        System.out.println("이름 : " + name);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        return "정상처리";
    }
    @PostMapping("form-post-handle2")
    @ResponseBody
    // Spring에서 Hello클래스의 인스턴스를 자동 매핑하여 생성
    // form-data형싱 즉, x-www-url인코딩 형식의 경우 사용
    // 이를 데이터 바인딩이라 부른다.(Hello 클래스에 setter 필수)
    public String formPostHandle2(Hello hello){
        System.out.println(hello);
        return "정상처리";
    }
    // json데이터 처리
    @GetMapping("json-screen")
    public String jsonScreen(){
        return "hello-json-screen";
    }
    @PostMapping("/json-post-handle1")
    @ResponseBody
    // @RequestBody는 json으로 post요청이 들어왔을때 body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body){
        System.out.println("이름 : " + body.get("name"));
        System.out.println("email : " + body.get("email"));
        System.out.println("password : " + body.get("password"));
        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setEmail(body.get("email"));
        hello.setPassword(body.get("password"));

        return "ok";
    }
    @PostMapping("/json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body){
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());
        return "ok";
    }
    @PostMapping("/json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello){
        System.out.println(hello);
        return "ok";
    }
}
