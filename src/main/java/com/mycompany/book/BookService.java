package com.mycompany.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired private BookRepository repo;

    public List<Book> listAll(){
        return (List<Book>) repo.findAll();
    }

    public void save(Book book) {
        repo.save(book);
    }

    public Book get(Integer id) throws BookSecondaryNotFoundException {
        Optional<Book> result = repo.findById(id);
        if(result.isPresent())
            return result.get();
        else
            throw new BookSecondaryNotFoundException("Could not find book with id = " + id);
    }

    public void delete(Integer id) throws BookSecondaryNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0)
            throw new BookSecondaryNotFoundException("Could not find book with id = " + id);
        repo.deleteById(id);
    }
}
