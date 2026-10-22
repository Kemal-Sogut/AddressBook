package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AddressBookViewController {
    private final AddressBookRepository bookRepo;

    public AddressBookViewController(AddressBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Home page: table of all address books
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "index"; // resolves to templates/index.html
    }

    // Create a new, empty address book, then go back to the home page
    @PostMapping("/addressbook")
    public String createAddressBook() {
        bookRepo.save(new AddressBook());
        return "redirect:/";
    }

    @GetMapping("/addressbook/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", findBook(id));
        model.addAttribute("buddy", new BuddyInfo("", "", "", ""));
        return "addressbook"; // resolves to templates/addressbook.html
    }

    // Create a buddy from the form fields and add it to the address book
    @PostMapping("/addressbook/{id}/buddies")
    public String addBuddy(@PathVariable("id") long id, @ModelAttribute BuddyInfo buddy) {
        AddressBook book = findBook(id);
        book.addBuddy(buddy);
        bookRepo.save(book); // cascade saves the new buddy
        return "redirect:/addressbook/" + id;
    }

    private AddressBook findBook(long id) {
        AddressBook book = bookRepo.findById(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address book " + id + " not found");
        }
        return book;
    }
}
