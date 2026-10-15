package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    private AddressBook book;
    private BuddyInfo alice;
    private BuddyInfo bob;

    @BeforeEach
    void setUp() {
        book = new AddressBook();
        alice = new BuddyInfo("Alice", "123 Bank St", "613-555-0101","alice@email.com");
        bob = new BuddyInfo("Bob", "45 Rideau St", "613-555-0202","bob@email.com");
    }

    @Test
    void testAddBuddy() {
        book.addBuddy(alice);
        book.addBuddy(bob);
        assertEquals(2, book.size());
    }

    @Test
    void testAddNullBuddyIgnored() {
        book.addBuddy(null);
        assertEquals(0, book.size());
    }

    @Test
    void testRemoveBuddy() {
        book.addBuddy(alice);
        book.addBuddy(bob);
        assertEquals(alice, book.removeBuddy(0));
        assertEquals(1, book.size());
        assertNull(book.removeBuddy(5)); // out of range
    }


}