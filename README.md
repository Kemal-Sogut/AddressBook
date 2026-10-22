# AddressBook

![Java CI with Maven](https://github.com/Kemal-Sogut/AddressBook/actions/workflows/maven.yml/badge.svg)

**Name:** Kemal Sogut (101280677)
**Course:** SYSC 4806 – Lab 6

## Azure deployment

https://addressbookkemal-ehb7ckg6fqbkc9h3.northcentralus-01.azurewebsites.net/

## About

A Spring Boot MVC address book app. It provides REST endpoints for creating address books and buddies and adding buddies to address books, plus Thymeleaf pages to list address books, create new ones, and add buddies to them.

| Method | URL | Description |
|---|---|---|
| POST | `/api/addressbooks` | Create an address book |
| POST | `/api/buddies` | Create a buddy (JSON body) |
| POST | `/api/addressbooks/{bookId}/buddies/{buddyId}` | Add a buddy to an address book |
| GET | `/api/addressbooks/{id}` | Get an address book as JSON |
| GET | `/` | List all address books, with a button to create one (HTML) |
| POST | `/addressbook` | Create an address book (form), redirects to `/` |
| GET | `/addressbook/{id}` | View an address book's buddies, with a form to add one (HTML) |
| POST | `/addressbook/{id}/buddies` | Add a buddy (form), redirects back to the book |

## Build and test

```bash
mvn test
```

GitHub Actions builds the app and runs the tests on every push to `main`, and Azure redeploys it automatically.
