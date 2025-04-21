package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.Enumerations.Role;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.model.exceptions.NoAvailableBooksException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    void addToWishList(String token, Long bookId) throws NoAvailableBooksException;

    List<Book> listWishListed(String token);

    void rentAllWishListed(String token);

    List<User> listUsersLazyWishListed();

}

