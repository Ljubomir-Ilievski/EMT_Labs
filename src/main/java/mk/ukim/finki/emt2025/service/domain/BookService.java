package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.dto.CreateBookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {


    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, CreateBookDto book);

    Optional<Book> save(CreateBookDto book);

    Optional<Book> deleteById(Long id);

    Optional<Book> softDeleteById(Long id);

    List<Book> findByIsSoftDeleted();


}
