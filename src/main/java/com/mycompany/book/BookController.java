package com.mycompany.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired private BookService service;

    @GetMapping("/books")
    public String showBookList(Model model){
        List<Book> listBooks = service.listAll();
        model.addAttribute("listBooks", listBooks);
        return "books";
    }

    @GetMapping("/books/new")
    public String showNewForm(Model model){
        model.addAttribute("book", new Book());
        return "book_form";
    }

    @PostMapping("/books/save")
    public String saveUser(Book book){
        service.save(book);
        return "redirect:/books";
    }
}
