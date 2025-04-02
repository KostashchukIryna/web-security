package edu.lab.security.book;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  @author   kosta
  @project   security
  @class  BookRestController
  @version  1.0.0 
  @since 20.03.2025 - 00.17
*/

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private final BookService service;

    @GetMapping
    public List<Book> getBooks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @GetMapping("/hello-user")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("/hello-admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello-unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }
}
