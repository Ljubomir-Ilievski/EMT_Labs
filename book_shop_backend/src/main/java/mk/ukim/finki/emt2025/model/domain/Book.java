package mk.ukim.finki.emt2025.model.domain;


import jakarta.persistence.*;
import mk.ukim.finki.emt2025.model.Enumerations.CategoryBook;

import java.time.LocalDate;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @Enumerated
    CategoryBook category;

    @ManyToOne
    Author author;

    Integer availableCopies;

    public void setSoftDeleted(Boolean softDeleted) {
        isSoftDeleted = softDeleted;
    }

    public Boolean getSoftDeleted() {
        return isSoftDeleted;
    }

    Boolean isSoftDeleted;

    LocalDate datePublished;


    public Book(String name, CategoryBook category, Integer availableCopies, Author author, LocalDate datePublished) {
        this.name =  name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
        this.isSoftDeleted = false;
        this.datePublished = datePublished;
    }
    public Book(String name, CategoryBook category, Integer availableCopies, Author author) {
        this.name =  name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
        this.isSoftDeleted = false;
    }

    public Book() {

    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryBook getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(CategoryBook category) {
        this.category = category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
