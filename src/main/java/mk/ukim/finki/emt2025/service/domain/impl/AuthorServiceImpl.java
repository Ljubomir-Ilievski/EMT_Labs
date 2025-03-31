package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.repository.AuthorRepository;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryServiceImpl countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryServiceImpl countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, CreateAuthorDto authorDto) {
        return authorRepository.findById(id)
                .map(author -> {
                    if (authorDto.getName() != null){
                        author.setName(authorDto.getName());
                    }
                    if (authorDto.getSurname() != null){
                        author.setSurname(authorDto.getSurname());
                    }
                    if (authorDto.getCountry() != null && countryService.findById(authorDto.getCountry()).isPresent()){
                        author.setCountry(countryService.findById(authorDto.getCountry()).get());
                    }
                    authorRepository.save(author);
                    return author;
                });
    }

    @Override
    public Optional<Author> save(CreateAuthorDto authorDto) {
        if (authorDto.getName() != null && authorDto.getSurname() != null
        && authorDto.getCountry() != null && countryService.findById(authorDto.getCountry()).isPresent()) {
            return Optional.of(authorRepository.save(new Author(authorDto.getName(), authorDto.getSurname(), countryService.findById(authorDto.getCountry()).get())));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Author> deleteById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        authorRepository.deleteById(id);
        return authorOptional;
    }
}
