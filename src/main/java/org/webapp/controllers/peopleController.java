package org.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webapp.dao.personDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.webapp.models.person;
import org.webapp.util.personValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class peopleController {

    private final personDAO personDAO;
    private final personValidator personValidator;

    @Autowired
    public peopleController(personDAO personDAO, personValidator personValidator){
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){

        model.addAttribute("person",new person());
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "/people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid person person,BindingResult bindingResult,@PathVariable("id") int id){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "/people/edit";
        }
        personDAO.update(id,person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }


}
