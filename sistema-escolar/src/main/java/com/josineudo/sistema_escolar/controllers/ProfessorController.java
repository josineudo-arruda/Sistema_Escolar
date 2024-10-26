package com.josineudo.sistema_escolar.controllers;

import com.josineudo.sistema_escolar.models.Professor;
import com.josineudo.sistema_escolar.models.StatusProfessor;
import com.josineudo.sistema_escolar.models.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class ProfessorController {
    @Autowired // Cria e injeta automaticamente como dependencia
    private ProfessorRepository professorRepository;

    @GetMapping("/")
    public String index() {
        return "professores/index";
    }

    @GetMapping("/professores")
    public ModelAndView professorsList() {
        List<Professor> professores = this.professorRepository.findAll();


        ModelAndView mv = new ModelAndView("professores/list");
        mv.addObject("professores",professores);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView professorCreate() {
        ModelAndView mv = new ModelAndView("professores/new");

        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

}
