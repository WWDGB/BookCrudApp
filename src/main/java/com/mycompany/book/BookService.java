package com.mycompany.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book get(Integer id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void delete(Integer id) throws BookNotFoundException {
        Long count = bookRepository.countById(id);
        if (count == null || count == 0) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
