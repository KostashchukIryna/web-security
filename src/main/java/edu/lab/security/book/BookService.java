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
        books.add(new Book("1", "name1", "description"));
        books.add(new Book("2", "name2", "description2"));
        books.add(new Book("3", "name3", "description3"));
        bookRepository.saveAll(books);
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
