package ru.vasa.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vasa.springapp.dao.PersonDAO;
import ru.vasa.springapp.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String makeAdminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.index());
        return "/adminPage";
    }

    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person)
    {
        System.out.println(person.getId());
        return "redirect:/people";

    }


}


