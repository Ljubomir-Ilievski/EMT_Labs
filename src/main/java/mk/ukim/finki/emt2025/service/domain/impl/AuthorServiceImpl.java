package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.model.projections.NameSurnameAuthorProjectionDto;
import mk.ukim.finki.emt2025.model.views.BooksPerAuthorView;
import mk.ukim.finki.emt2025.repository.AuthorRepository;
import mk.ukim.finki.emt2025.repository.BooksPerAuthorRepository;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryServiceImpl countryService;
    private final BooksPerAuthorRepository booksPerAuthorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryServiceImpl countryService, BooksPerAuthorRepository booksPerAuthorRepository) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.booksPerAuthorRepository = booksPerAuthorRepository;
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

    @Override
    public List<BooksPerAuthorView> bookCountPerAuthor() {
        List<BooksPerAuthorView> booksPerAuthorViews = booksPerAuthorRepository.findAll();
        return booksPerAuthorRepository.findAll();
    }

    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void RefreshMaterialisedBookCounts() {
        booksPerAuthorRepository.refreshMaterializedView();
    }

    @Override
    public List<NameSurnameAuthorProjectionDto> NameSurnameProjection() {
        return authorRepository.nameSurnameProjection();
    }

}
