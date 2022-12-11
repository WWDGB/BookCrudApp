package com.mycompany.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
