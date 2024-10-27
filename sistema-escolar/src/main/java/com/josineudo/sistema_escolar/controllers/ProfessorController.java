package com.josineudo.sistema_escolar.controllers;

import com.josineudo.sistema_escolar.dto.RequisicaoNovoProfessor;
import com.josineudo.sistema_escolar.models.Professor;
import com.josineudo.sistema_escolar.models.StatusProfessor;
import com.josineudo.sistema_escolar.models.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired // Cria e injeta automaticamente como dependencia
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView professorsList() {
        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("/professores/list");
        mv.addObject("professores",professores);
        mv.addObject("actualPage","professores");
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView professorCreate(RequisicaoNovoProfessor request) {
        ModelAndView mv = new ModelAndView("/professores/new");

        mv.addObject("listaStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores") // quando entra em professores por POST acionar isso
    public ModelAndView create(@Valid RequisicaoNovoProfessor request, BindingResult bindingResult, RedirectAttributes redirectAttributes) { // DTO cria uma classe de uma requisição contra ataques de insergurança
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("/professores/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            return mv;
        } else {
            Professor professor = request.toProfessor();
            this.professorRepository.save(professor);

            ModelAndView mv = new ModelAndView("redirect:/professores");
            mv.addObject("actualPage","professores");

            return mv;
        }
    }
}