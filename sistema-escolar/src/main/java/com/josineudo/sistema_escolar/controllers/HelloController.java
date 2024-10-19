package com.josineudo.sistema_escolar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Anotação que relembra a aplicação que isso é um controlador
public class HelloController {

    @GetMapping("/hello") //Nome da seção que uma página com hello
    public String hello() {
        return "hello"; // Spring renderiza o arquivo que se chama hello no templates
    }
}
