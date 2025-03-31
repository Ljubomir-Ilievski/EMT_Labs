package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.model.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> deleteById(Long id);
}
