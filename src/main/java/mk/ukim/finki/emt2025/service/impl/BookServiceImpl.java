package mk.ukim.finki.emt2025.service.impl;


import mk.ukim.finki.emt2025.model.Book;
import mk.ukim.finki.emt2025.model.dto.BookDto;
import mk.ukim.finki.emt2025.repository.BookRepository;
import mk.ukim.finki.emt2025.service.BookService;
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
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
       return bookRepository.findByIdAndIsSoftDeleted(id, false);
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        return bookRepository.findByIdAndIsSoftDeleted(id, false)
                .map(book -> {
                    if (bookDto.getName() != null){
                        book.setName(bookDto.getName());
                    }
                    if (bookDto.getAvailableCopies() != null){
                        book.setAvailableCopies(bookDto.getAvailableCopies());
                    }
                    if (bookDto.getAuthor() != null && authorService.findById(bookDto.getAuthor()).isPresent()){
                        book.setAuthor(authorService.findById(bookDto.getAuthor()).get());
                    }
                    if (bookDto.getCategory() != null){
                        book.setCategory(bookDto.getCategory());
                    }
                    bookRepository.save(book);
                    return book;
                });
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        if (bookDto.getCategory() != null
        && bookDto.getName() != null
        && bookDto.getAvailableCopies() !=null
        && bookDto.getAuthor() != null
        && authorService.findById(bookDto.getAuthor()).isPresent()){
           return Optional.of(bookRepository.save(new Book(bookDto.getName(), bookDto.getCategory(), bookDto.getAvailableCopies(),
                   authorService.findById(bookDto.getAuthor()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> deleteById(Long id) {

        if (bookRepository.findByIdAndIsSoftDeleted(id, false).isPresent()) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            bookRepository.deleteById(id);
            return optionalBook;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> softDeleteById(Long id) {
        if (bookRepository.findByIdAndIsSoftDeleted(id, false).isPresent()){
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
