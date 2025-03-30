package mk.ukim.finki.emt2025.service;

import mk.ukim.finki.emt2025.model.Author;
import mk.ukim.finki.emt2025.model.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, AuthorDto authorDto);

    Optional<Author> save(AuthorDto authorDto);

    void deleteById(Long id);

}
