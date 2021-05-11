package com.andriosi.fabio.firstspring;

import com.andriosi.fabio.firstspring.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GreetingController {
    private static final String template= "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "http://localhost:9000")
    @ResponseBody
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required = false,defaultValue="Stranger") String name) {
        System.out.println("=====in greeting====");
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @ResponseBody
    @GetMapping("/greeting-javaconfig")
    public Greeting greetingWithJavaConfig(@RequestParam(value="name", required = false,defaultValue="Stranger") String name) {
        System.out.println("=====in greeting====");
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
