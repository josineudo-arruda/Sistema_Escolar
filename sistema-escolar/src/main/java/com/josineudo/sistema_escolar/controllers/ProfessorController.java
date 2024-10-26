package com.josineudo.sistema_escolar.controllers;

import com.josineudo.sistema_escolar.models.Professor;
import com.josineudo.sistema_escolar.models.StatusProfessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {

    @GetMapping("/")
    public String index() {
        return "professores/index";
    }

    @GetMapping("/professores")
    public ModelAndView professorsList() {
        Professor gabriel = new Professor("Gabriel Azevedo", new BigDecimal(1500), StatusProfessor.ATIVO);
        gabriel.setId(1L);
        Professor julio = new Professor("Júlio Moura", new BigDecimal(8000), StatusProfessor.ATIVO);
        julio.setId(2L );

        List<Professor> professores = Arrays.asList(gabriel, julio);
        ModelAndView mv = new ModelAndView("professores/list");
        mv.addObject("professores",professores);
        return mv;
    }

}
