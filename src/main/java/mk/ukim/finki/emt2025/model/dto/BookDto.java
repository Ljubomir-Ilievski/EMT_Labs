package mk.ukim.finki.emt2025.model.dto;
import lombok.Data;
import mk.ukim.finki.emt2025.model.Author;
import mk.ukim.finki.emt2025.model.Enumerations.CategoryBook;

public class BookDto {

    String name;

    CategoryBook category;

    Long author;

    Integer availableCopies;

    public String getName() {
        return name;
    }

    public CategoryBook getCategory() {
        return category;
    }

    public Long getAuthor() {
        return author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(CategoryBook category) {
        this.category = category;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
