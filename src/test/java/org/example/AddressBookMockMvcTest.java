package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Same app, but requests go through a mock instead of a real server (faster)
@SpringBootTest
@AutoConfigureMockMvc
class AddressBookMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepository bookRepo;

    @Test
    void createBuddyReturnsJson() throws Exception {
        mockMvc.perform(post("/api/buddies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sam\",\"address\":\"9 Birch St\",\"phoneNumber\":\"613-555-7777\",\"email\":\"test@emil.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sam"))
                .andExpect(jsonPath("$.phoneNumber").value("613-555-7777"))
                .andExpect(jsonPath("$.email").value("test@emil.com"));
    }

    @Test
    void viewShowsBuddiesOfAddressBook() throws Exception {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Carol", "3 Maple St", "613-555-0003","test2@email.com"));
        book = bookRepo.save(book);

        mockMvc.perform(get("/addressbook/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Carol")))
                .andExpect(content().string(containsString("3 Maple St")));
    }

    @Test
    void homeListsAddressBooksWithLinks() throws Exception {
        AddressBook book = bookRepo.save(new AddressBook());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("href=\"/addressbook/" + book.getId() + "\"")));
    }

    @Test
    void createAddressBookRedirectsToHome() throws Exception {
        long before = bookRepo.count();

        mockMvc.perform(post("/addressbook"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        assertEquals(before + 1, bookRepo.count());
    }

    @Test
    void addBuddyFormAddsBuddyAndRedirects() throws Exception {
        AddressBook book = bookRepo.save(new AddressBook());

        mockMvc.perform(post("/addressbook/" + book.getId() + "/buddies")
                        .param("name", "Dave")
                        .param("address", "4 Cedar St")
                        .param("phoneNumber", "613-555-0004")
                        .param("email", "dave@email.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/addressbook/" + book.getId()));

        mockMvc.perform(get("/addressbook/" + book.getId()))
                .andExpect(content().string(containsString("Dave")))
                .andExpect(content().string(containsString("dave@email.com")));
    }

    @Test
    void unknownAddressBookReturns404() throws Exception {
        mockMvc.perform(get("/addressbook/999999"))
                .andExpect(status().isNotFound());
    }
}