package edu.lab.security.book;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/*
  @author   kosta
  @project   security
  @class  Book
  @version  1.0.0 
  @since 20.03.2025 - 00.09
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends AuditMetaData{
    @Id
    private String id;
    private String author;
    private String description;

    public Book(String author, String description) {
        this.author = author;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) {
            return false;
        }
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
