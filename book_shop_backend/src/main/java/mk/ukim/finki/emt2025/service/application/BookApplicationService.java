package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.dto.CreateBookDto;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> findById(Long id);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);

    Optional<DisplayBookDto> save(CreateBookDto createBookDto);

    Optional<DisplayBookDto> deleteById(Long id);

    Optional<DisplayBookDto> softDeleteById(Long id);

    List<DisplayBookDto> findByIsSoftDeleted();

    List<DisplayBookDto> findNewest10Books();

    List<DisplayBookDto> getPaginatedUsers(int page, int size);

}
