package org.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.webapp.dao.personDAO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class batchController {

    private final personDAO personDAO;

    @Autowired
    public batchController(personDAO personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(){
        return "batch/index";
    }

    @GetMapping("/with")
    public String withBatch(){
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
    @GetMapping("/delete")
    public String deleteBatch(){
        personDAO.testBatchDelete();
        return "redirect:/people";
    }

}
