package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.dto.CreateBookDto;
import mk.ukim.finki.emt2025.repository.BookRepository;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorServiceImpl authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findByIsSoftDeleted(false);
    }

    @Override
    public Optional<Book> findById(Long id) {
       return bookRepository.findByIdAndIsSoftDeleted(id, false);
    }

    @Override
    public Optional<Book> update(Long id, CreateBookDto createBookDto) {
        return findById(id)
                .map(book -> {
                    if (createBookDto.getName() != null){
                        book.setName(createBookDto.getName());
                    }
                    if (createBookDto.getAvailableCopies() != null){
                        book.setAvailableCopies(createBookDto.getAvailableCopies());
                    }
                    if (createBookDto.getAuthor() != null && authorService.findById(createBookDto.getAuthor()).isPresent()){
                        book.setAuthor(authorService.findById(createBookDto.getAuthor()).get());
                    }
                    if (createBookDto.getCategory() != null){
                        book.setCategory(createBookDto.getCategory());
                    }
                    bookRepository.save(book);
                    return book;
                });
    }

    @Override
    public Optional<Book> save(CreateBookDto createBookDto) {
        if (createBookDto.getCategory() != null
        && createBookDto.getName() != null
        && createBookDto.getAvailableCopies() !=null
        && createBookDto.getAuthor() != null
        && authorService.findById(createBookDto.getAuthor()).isPresent()){
           return Optional.of(bookRepository.save(new Book(createBookDto.getName(), createBookDto.getCategory(), createBookDto.getAvailableCopies(),
                   authorService.findById(createBookDto.getAuthor()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> deleteById(Long id) {

        if (findById(id).isPresent()) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            bookRepository.deleteById(id);
            return optionalBook;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> softDeleteById(Long id) {
        if (findById(id).isPresent()){
            Optional<Book> optionalBook = bookRepository.findById(id);
            optionalBook.get().setSoftDeleted(Boolean.TRUE);
            bookRepository.save(optionalBook.get());
            return optionalBook;
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByIsSoftDeleted() {
        return bookRepository.findByIsSoftDeleted(false);
    }
}
