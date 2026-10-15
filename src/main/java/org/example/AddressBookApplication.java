package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {
    private static final Logger log = LoggerFactory.getLogger(AddressBookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository buddyRepo, AddressBookRepository bookRepo) {
        return args -> {
            // Persist buddies
            buddyRepo.save(new BuddyInfo("Tom", "123 Main St", "613-555-5555"));
            buddyRepo.save(new BuddyInfo("Tommy", "321 Other St", "613-555-5556"));

            log.info("All buddies via findAll():");
            buddyRepo.findAll().forEach(b -> log.info(b.getId() + ": " + b));

            log.info("findByName(\"Tom\"):");
            buddyRepo.findByName("Tom").forEach(b -> log.info(b.toString()));

            // Persist an AddressBook (cascade saves its buddies)
            AddressBook book = new AddressBook();
            book.addBuddy(new BuddyInfo("Alice", "1 Elm St", "613-555-0001"));
            book.addBuddy(new BuddyInfo("Bob", "2 Oak St", "613-555-0002"));
            bookRepo.save(book);

            log.info("All address books:");
            bookRepo.findAll().forEach(ab -> log.info("Book " + ab.getId() + ":\n" + ab));
        };
    }
}