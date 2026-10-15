package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressBookViewController {
    private final AddressBookRepository bookRepo;

    public AddressBookViewController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/addressbook/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookRepo.findById(id));
        return "addressbook"; // resolves to templates/addressbook.html
    }
}