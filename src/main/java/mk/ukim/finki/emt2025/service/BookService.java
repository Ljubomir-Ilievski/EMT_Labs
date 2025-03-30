package mk.ukim.finki.emt2025.service;

import mk.ukim.finki.emt2025.model.Book;
import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.dto.BookDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface BookService {


    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, BookDto bookDto);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> deleteById(Long id);

    Optional<Book> softDeleteById(Long id);

    List<Book> findByIsSoftDeleted();


}
