package mk.ukim.finki.emt2025.model.dto;

import jakarta.persistence.ManyToOne;
import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(
        Long id,
        String name,
        String surname,
        Long country) {

    public static DisplayAuthorDto from(Author author){
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public static List<DisplayAuthorDto> from(List<Author> authors){
        return authors.stream().map(DisplayAuthorDto::from)
                .collect(Collectors.toList());
    }

}
