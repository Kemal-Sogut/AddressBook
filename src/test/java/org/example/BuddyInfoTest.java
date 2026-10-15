package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuddyInfoTest {
    private BuddyInfo buddy;

    @BeforeEach
    void setUp() {
        buddy = new BuddyInfo("Alice", "123 Bank St", "613-555-0101","alice@email.com");
    }

    @Test
    void testConstructorSetsFields() {
        assertEquals("Alice", buddy.getName());
        assertEquals("123 Bank St", buddy.getAddress());
        assertEquals("613-555-0101", buddy.getPhoneNumber());
    }

    @Test
    void testSetters() {
        buddy.setName("Alicia");
        buddy.setPhoneNumber("613-555-9999");
        assertEquals("Alicia", buddy.getName());
        assertEquals("613-555-9999", buddy.getPhoneNumber());
    }

    @Test
    void testToString() {
        assertEquals("Alice, 123 Bank St, 613-555-0101", buddy.toString());
    }
}