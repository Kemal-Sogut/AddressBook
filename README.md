# AddressBook

![Java CI with Maven](https://github.com/Kemal-Sogut/AddressBook/actions/workflows/maven.yml/badge.svg)

**Name:** Kemal Sogut (101280677)
**Course:** SYSC 4806 – Lab 5

## Azure deployment

https://addressbookkemal-ehb7ckg6fqbkc9h3.northcentralus-01.azurewebsites.net/addressbook/1

## About

A Spring Boot MVC address book app. It provides REST endpoints for creating address books and buddies and adding buddies to address books, plus a Thymeleaf page that lists the buddies in an address book.

| Method | URL | Description |
|---|---|---|
| POST | `/api/addressbooks` | Create an address book |
| POST | `/api/buddies` | Create a buddy (JSON body) |
| POST | `/api/addressbooks/{bookId}/buddies/{buddyId}` | Add a buddy to an address book |
| GET | `/api/addressbooks/{id}` | Get an address book as JSON |
| GET | `/addressbook/{id}` | View an address book's buddies (HTML) |

## Build and test

```bash
mvn test
```

GitHub Actions builds the app and runs the tests on every push to `main`, and Azure redeploys it automatically.
