package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!");  //data 부분에 hello가 들어
        return "hello"; //컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리함
                        //ex)hello면 hello.html 파일을 resources 밑에서 찾아옴
    } //-> 웹 어플리케이션에서 /hello 호출하면 이 메서드 호출해줌

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //@requestparam으로 하면 required 값이 필요한데 기본이 true라서 아무것도 지정하지 않거나 true로 지정하면 url에 ?name=~ 하고 매핑 해줘야함, required = false 로 지정하면 url 뒤에 매핑하지 않아도 됨
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 body 부분에 return 값에 해당하는 것을 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //json 방식으로 반환

    /*웹 브라우저에서 http://localhost:8080/hello-api 입력
    → 내장 톰캣 서버가 스프링에게 넘김
    → helloController에서 hello-api 찾음
    → @ResponseBody가 있음
    → HttpMessageConverter가 동작
    → 단순 문자면 StringConverter가 동작, 객체면 JsonConverter가 동작
    → Json 스타일로 바꿈
    → Json스타일로 바꾼 것을 요청했던 서버나 웹 브라우저에 보냄*/
}
