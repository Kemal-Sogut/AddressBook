package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressBookRestController {
    private final AddressBookRepository bookRepo;
    private final BuddyInfoRepository buddyRepo;

    public AddressBookRestController(AddressBookRepository bookRepo, BuddyInfoRepository buddyRepo) {
        this.bookRepo = bookRepo;
        this.buddyRepo = buddyRepo;
    }

    // Create an address book
    @PostMapping("/addressbooks")
    public AddressBook createAddressBook() {
        return bookRepo.save(new AddressBook());
    }

    // Create a buddy from a JSON body
    @PostMapping("/buddies")
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddy) {
        return buddyRepo.save(buddy);
    }

    // Add an existing buddy to an existing address book
    @PostMapping("/addressbooks/{bookId}/buddies/{buddyId}")
    public AddressBook addBuddy(@PathVariable("bookId") long bookId,
                                @PathVariable("buddyId") long buddyId) {
        AddressBook book = bookRepo.findById(bookId);
        book.addBuddy(buddyRepo.findById(buddyId));
        return bookRepo.save(book);
    }

    // Read an address book as JSON
    @GetMapping("/addressbooks/{id}")
    public AddressBook getAddressBook(@PathVariable("id") long id) {
        return bookRepo.findById(id);
    }
}