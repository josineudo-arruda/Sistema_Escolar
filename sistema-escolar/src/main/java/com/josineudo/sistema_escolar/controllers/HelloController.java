package com.josineudo.sistema_escolar.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //Anotação que relembra a aplicação que isso é um controlador
public class HelloController {

    @GetMapping("/hello") //Nome da seção que uma página com hello
    public String hello() {
        return "hello"; // Spring renderiza o arquivo que se chama hello no templates
    }

    @GetMapping("/hello-user")
    public String helloUser(Model model){
        model.addAttribute("nome", "Leonardo"); // adicionar que uma variavels chamada nome irá conter Emily no carregamento
        return "hello";
    }

    @GetMapping("/hello-model")
    public ModelAndView helloModel() {
        ModelAndView mv = new ModelAndView("hello"); // Cria um modelo de dado, um objeto dele
        mv.addObject("nome","Clarice"); // cria uma variavel no modelo com nome:Clarice
        return mv;
    }
}
