package com.mycompany;
import org.hamcrest.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.book.Book;
import com.mycompany.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BookRepositoryTests {
    @Autowired private BookRepository repo;

    @Test
    public void testAddNew(){
        Book book = new Book();
        book.setTitle("Hamlet");
        book.setAuthor("William Shakespeare");
        book.setGenre("Drama");

        Book savedBook = repo.save(book);

        Assertions.assertNotNull(savedBook);
        Assertions.assertTrue(savedBook.getId() > 0);
    }

    @Test
    public void testListAll(){
        Iterable<Book> books = repo.findAll();
        Assertions.assertTrue(books.iterator().hasNext());

        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void testUpdate(){
        Integer bookId = 4;
        Optional<Book> optionalBook = repo.findById(bookId);
        Book book = optionalBook.get();
        book.setTitle("Test Title");
        repo.save(book);

        Book updatedBook = repo.findById(bookId).get();
        Assertions.assertEquals("Test Title", updatedBook.getTitle());
    }

    @Test
    public void testGet(){
        Integer bookId = 2;
        Optional<Book> optionalBook = repo.findById(bookId);
        Assertions.assertTrue(optionalBook.isPresent());
        System.out.println(optionalBook.get());
    }

    @Test
    public void testDelete(){
        Integer bookId = 3;
        repo.deleteById(bookId);

        Optional<Book> optionalBook = repo.findById(bookId);
        Assertions.assertTrue(optionalBook.isEmpty());
    }
}
