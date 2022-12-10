package com.mycompany;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;
import com.mycompany.book.Book;
import com.mycompany.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BookRepositoryTests {
    @Autowired private BookRepository repo;

    @Test
    public void testAddNew(){
        Book book = new Book();
        book.setTitle("The Divine Comedy");
        book.setAuthor("Dante Alighieri");
        book.setGenre("Poetry");

        Book savedBook = repo.save(book);

        Assertions.assertNotNull(savedBook);
        Assertions.assertTrue(savedBook.getId() > 0);
    }
}
