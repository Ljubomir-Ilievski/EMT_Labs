package mk.ukim.finki.emt2025.model.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM public.number_of_books_by_author")
@Immutable
@Data
public class BooksPerAuthorView {

    @Id
    @JsonProperty("author_id")
    Long id;

    @JsonProperty("author_name")
    String name;

    @JsonProperty("author_surname")
    String surname;

    @JsonProperty("number_of_books")
    int number_of_books;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNumber_of_books() {
        return number_of_books;
    }
}
