package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// Starts the real app on a random port and sends real HTTP requests to it
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class AddressBookIntegrationTest {
    @Autowired
    private RestTestClient client;

    @Test
    void createAddressBookReturnsEmptyBook() {
        client.post().uri("/api/addressbooks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.buddies").isEmpty();
    }

    @Test
    void createBuddyReturnsBuddyAsJson() {
        client.post().uri("/api/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new BuddyInfo("Jane", "5 Pine St", "613-555-1234","jane@email.com"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.name").isEqualTo("Jane");
    }

    @Test
    void addBuddyToAddressBook() {
        AddressBook book = client.post().uri("/api/addressbooks")
                .exchange()
                .expectBody(AddressBook.class).returnResult().getResponseBody();
        BuddyInfo buddy = client.post().uri("/api/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new BuddyInfo("Jane", "5 Pine St", "613-555-1234","jane@email.com"))
                .exchange()
                .expectBody(BuddyInfo.class).returnResult().getResponseBody();
        assertNotNull(book);
        assertNotNull(buddy);

        client.post().uri("/api/addressbooks/{bookId}/buddies/{buddyId}", book.getId(), buddy.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.buddies[0].name").isEqualTo("Jane");
    }
}