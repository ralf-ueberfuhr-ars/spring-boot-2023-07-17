package de.huk.schulung.spring.blog.boundary;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping (produces = MediaType.TEXT_PLAIN_VALUE)// "hello"
    public String sayHello(
            @RequestParam(name="name", defaultValue = "World")
            String name
    ) {
        return "<h1>Hello " + name + "</h1>";
    }

    @GetMapping("/2") // "hello/2"
    public String sayHello2() {
        return "";
    }

}
