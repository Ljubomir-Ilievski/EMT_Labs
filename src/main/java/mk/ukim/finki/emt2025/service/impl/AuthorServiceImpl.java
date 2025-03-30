package mk.ukim.finki.emt2025.service.impl;


import mk.ukim.finki.emt2025.model.Author;
import mk.ukim.finki.emt2025.model.dto.AuthorDto;
import mk.ukim.finki.emt2025.repository.AuthorRepository;
import mk.ukim.finki.emt2025.service.AuthorService;
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
    public Optional<Author> update(Long id, AuthorDto authorDto) {
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
    public Optional<Author> save(AuthorDto authorDto) {
        if (authorDto.getName() != null && authorDto.getSurname() != null
        && authorDto.getCountry() != null && countryService.findById(authorDto.getCountry()).isPresent()) {
            return Optional.of(authorRepository.save(new Author(authorDto.getName(), authorDto.getSurname(), countryService.findById(authorDto.getCountry()).get())));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
