package edu.lab.security.book;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
  @author   kosta
  @project   security
  @class  BookService
  @version  1.0.0 
  @since 20.03.2025 - 00.16
*/
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private List<Book> books;

    @PostConstruct
    void init() {
        if (bookRepository.count() == 0) {
            books.add(new Book("4", "name4", "description4"));
            books.add(new Book("5", "name5", "description5"));
            books.add(new Book("6", "name6", "description6"));
            bookRepository.saveAll(books);
        }
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
