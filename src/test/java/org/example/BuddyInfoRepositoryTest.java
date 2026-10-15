package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BuddyInfoRepositoryTest {
    @Autowired
    BuddyInfoRepository repo;

    @Test
    void saveAndFind() {
        repo.save(new BuddyInfo("TestBuddy", "1 Test St", "613-555-9999"));
        assertEquals(1, repo.findByName("TestBuddy").size());
    }
}
