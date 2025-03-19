package ru.vasa.springapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasa.springapp.dao.PersonDAO;
import ru.vasa.springapp.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/index")
    public String index(Model model) {
        //получим всех людей из DAO и отобразим их
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //получим чееловаека по ид и отобразим

        model.addAttribute("people",personDAO.show(id));
        //System.out.println(personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person",new Person());

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
//        System.out.println("Mail--"+person.getMail());
//        System.out.println("name--"+person.getName());

        if (bindingResult.hasErrors()) {

            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        System.out.println(String.format("worknul /{%s}/edit",id));

        model.addAttribute("person",personDAO.show(id));
        System.out.println("Mail--"+personDAO.show(id).getMail());
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id,person);
        return "redirect:/people/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people/index";
    }


    @GetMapping("/ds")
    public String ds() {
        System.out.println("worknul  /ds+=/new");
        return "redirect:/people/new";
    }
}
