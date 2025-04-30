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
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("/hello-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("/hello/superadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String helloSuperAdmin() {
        return "Hello SuperAdmin!";
    }

    @GetMapping("hello-unknown")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @GetMapping("/view/profile")
    public String viewProfile() {
        return "This is your profile information. (Access granted for USER, ADMIN, SUPERADMIN)";
    }

    @GetMapping("/view/dashboard")
    public String viewDashboard() {
        return "Welcome to the dashboard. (Access granted for USER, ADMIN)";
    }

    @PreAuthorize("hasRole('SUPERADMIN')")
    @GetMapping("/view/stats")
    public String viewStats() {
        return "Here are the detailed stats. (Access granted only for SUPERADMIN)";
    }

    @GetMapping("hello/stranger")
    public String helloStranger() {
        return "Hello Stranger!";
    }
}
