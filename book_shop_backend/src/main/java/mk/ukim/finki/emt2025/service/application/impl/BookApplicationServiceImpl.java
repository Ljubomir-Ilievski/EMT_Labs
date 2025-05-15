package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.dto.CreateBookDto;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.service.application.BookApplicationService;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookApplicationServiceImpl  implements BookApplicationService {

    private final BookService bookService;

    public BookApplicationServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        return bookService.update(id, createBookDto).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        return bookService.save(createBookDto).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> softDeleteById(Long id) {
        return bookService.softDeleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findByIsSoftDeleted() {
        return DisplayBookDto.from(bookService.findByIsSoftDeleted());
    }

    @Override
    public List<DisplayBookDto> findNewest10Books() {
        return DisplayBookDto.from(bookService.findNewest10Books());
    }
}
