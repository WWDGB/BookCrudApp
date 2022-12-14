package com.mycompany.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/books")
    public String showBookList(Model model) {
        List<Book> bookList = service.findAll();
        model.addAttribute("bookList", bookList);
        return "books";
    }

    @GetMapping("/books/new")
    public String showNewForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        return "book_form";
    }

    @PostMapping("/books/save")
    public String saveUser(Book book, RedirectAttributes ra) {
        service.save(book);
        ra.addFlashAttribute("message", "The book has been saved successfully!");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Book book = service.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");
            return "book_form";
        } catch (BookNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/books";
        }
    }

    @GetMapping("/books/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The book id " + id + " has been deleted!");
        } catch (BookNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/books";
    }
}
