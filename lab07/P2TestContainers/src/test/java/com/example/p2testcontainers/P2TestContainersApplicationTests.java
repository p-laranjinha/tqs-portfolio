package com.example.p2testcontainers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class P2TestContainersApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    void saveBook() {
        Book book = new Book();
        book.setTitle("Testcontainers");
        bookRepository.save(book);
    }

    @Test
    @Order(2)
    void listBooks() {
        assertThat(bookRepository.findAll().size(), is(2));
    }
}
