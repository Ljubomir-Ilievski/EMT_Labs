package mk.ukim.finki.emt2025.model.views;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT * FROM public.number_of_authors_per_country")
public class AuthorsPerCountryView {

    @Id
    @JsonProperty("country_id")
    Long id;

    @JsonProperty("country_name")
    String name;

    @JsonProperty("number_of_authors")
    int number_of_authors;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber_of_authors() {
        return number_of_authors;
    }
}
