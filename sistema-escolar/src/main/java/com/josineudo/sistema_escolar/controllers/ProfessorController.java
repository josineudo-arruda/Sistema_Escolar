package com.josineudo.sistema_escolar.controllers;

import com.josineudo.sistema_escolar.dto.RequisicaoNovoProfessor;
import com.josineudo.sistema_escolar.models.Professor;
import com.josineudo.sistema_escolar.models.StatusProfessor;
import com.josineudo.sistema_escolar.models.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfessorController {
    @Autowired // Cria e injeta automaticamente como dependencia
    private ProfessorRepository professorRepository;

    @GetMapping("/professors")
    public ModelAndView professorsList() {
        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("/professors/list");
        mv.addObject("professores",professores);
        mv.addObject("actualPage","professores");
        return mv;
    }

    @GetMapping("/professors/create")
    public ModelAndView professorCreate(RequisicaoNovoProfessor request) {
        ModelAndView mv = new ModelAndView("/professors/new");

        mv.addObject("listaStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professors") // quando entra em professores por POST acionar isso
    public ModelAndView create(@Valid RequisicaoNovoProfessor request, BindingResult bindingResult) { // DTO cria uma classe de uma requisição contra ataques de insergurança
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("/professors/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            return mv;
        } else {
            Professor professor = request.toProfessor();
            this.professorRepository.save(professor);

            ModelAndView mv = new ModelAndView("redirect:/professors/" + professor.getId() +"/show");
            mv.addObject("actualPage","Professor");

            return mv;
        }
    }

    @GetMapping("professors/{id}/show")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Professor> optional = this.professorRepository.findById(id); // Optional é apenas um objeto para caso a busca seja nula, não causar problema

        if(optional.isPresent()) {
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("professors/show");
            mv.addObject("professor", professor);
            mv.addObject("actualPage","Professor");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("professors/");
            mv.addObject("actualPage","Professor");
            return mv;
        }
    }

    @GetMapping("/professors/{id}/edit")
    public ModelAndView professorEdit(@PathVariable Long id,RequisicaoNovoProfessor request) {
        Optional<Professor> optional = professorRepository.findById(id);

        if(optional.isPresent()) {
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("/professors/edit");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            mv.addObject("professor", professor);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("/professors/");
            mv.addObject("actualPage","Professor");

            return mv;
        }
    }

    @PostMapping("/professors/{id}/show")
    public ModelAndView edit(@PathVariable Long id,@Valid RequisicaoNovoProfessor request, BindingResult bindingResult) {
        Optional<Professor> optional = professorRepository.findById(id);

        if(bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("/professors/edit");
            mv.addObject("actualPage","Professor");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            Professor professor = optional.get();
            mv.addObject("professor", professor);
            return mv;
        } else {
            if(optional.isPresent()) {
                Professor professor = optional.get();

                professor.setNome(request.getNome());
                professor.setSalario(request.getSalario());
                professor.setStatusProfessor(request.getStatusProfessor());

                professorRepository.save(professor);
                ModelAndView mv = new ModelAndView("redirect:/professors/"+ professor.getId() +"/show");
                mv.addObject("actualPage","Professor");
                return mv;
            } else {
                ModelAndView mv = new ModelAndView("/professors/list");
                mv.addObject("actualPage","Professor");
                return mv;
            }
        }
    }
}