package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.model.dto.DisplayAuthorDto;
import mk.ukim.finki.emt2025.model.projections.NameSurnameAuthorProjectionDto;
import mk.ukim.finki.emt2025.service.application.AuthorApplicationService;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    public final AuthorService authorService;

    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        return authorService.update(id, createAuthorDto).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        return authorService.save(createAuthorDto).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> deleteById(Long id) {
        return authorService.deleteById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public List<NameSurnameAuthorProjectionDto> NameSurnameProjection() {
        return authorService.NameSurnameProjection();
    }
}
