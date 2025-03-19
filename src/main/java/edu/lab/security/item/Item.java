package edu.lab.security.item;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/*
  @author   kosta
  @project   security
  @class  Item
  @version  1.0.0 
  @since 20.03.2025 - 00.09
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    private String id;
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) {
            return false;
        }
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
