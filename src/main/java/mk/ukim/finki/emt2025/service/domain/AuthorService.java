package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, CreateAuthorDto authorDto);

    Optional<Author> save(CreateAuthorDto authorDto);

    Optional<Author> deleteById(Long id);

}
