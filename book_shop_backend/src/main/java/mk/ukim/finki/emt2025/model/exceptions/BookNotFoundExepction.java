package mk.ukim.finki.emt2025.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundExepction extends RuntimeException {

    public BookNotFoundExepction(Long id) {
        super(String.format("Category with id: %d is not found", id));
    }
}