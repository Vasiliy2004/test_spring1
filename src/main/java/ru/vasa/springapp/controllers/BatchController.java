package ru.vasa.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vasa.springapp.dao.PersonDAO;

//Для тестирования пакетной вставки
@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    @GetMapping()
    public String index(){
        return "batch/index";
    }

    private final PersonDAO personDAO;
    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/without")
    public String withoutBatch(){
        //personDAO.testMultipleUpdate();
        return "redirect:/people/index";
    }

    @GetMapping("/with")
    public String withBatch(){
        //personDAO.testMultipleUpdate();
            return "redirect:/people/index";
    }
}
