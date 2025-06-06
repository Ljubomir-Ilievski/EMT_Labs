package mk.ukim.finki.emt2025.model.dto;

import mk.ukim.finki.emt2025.model.Enumerations.CategoryBook;
import mk.ukim.finki.emt2025.model.domain.Book;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String name,
        CategoryBook category,
        Long author,
        Integer availableCopies,
        LocalDate datePublished) {

    
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies(), book.getDatePublished());
    }
   /* public static DisplayBookDto from(Book book){
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies(), book.getDatePublished());
    }*/

    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from)
                .collect(Collectors.toList());
    }


    public static List<DisplayBookDto> from(Page<Book> paginatedBooks) {
        return paginatedBooks.getContent()
                .stream()
                .map(DisplayBookDto::from)
                .collect(Collectors.toList());
    }
}
