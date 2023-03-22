package org.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webapp.dao.personDAO;
import org.webapp.models.person;

@Controller
@RequestMapping("/admin")
public class adminController {
    private final personDAO personDAO;

    @Autowired
    public adminController(personDAO personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") person person){
        model.addAttribute("people",personDAO.index());

        return "/adminPage";
    }
    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person") person person){
        System.out.println(person.getId());

        return "redirect:/people";
    }

}
