package mk.ukim.finki.emt2025.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt2025.model.Enumerations.Role;
import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.model.Enumerations.CategoryBook;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.repository.AuthorRepository;
import mk.ukim.finki.emt2025.repository.BookRepository;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostConstruct
    public void init() {
        // Initialize countries
        Country usa = countryRepository.save(new Country("USA", "North America"));
        Country uk = countryRepository.save(new Country("UK", "Europe"));
        Country germany = countryRepository.save(new Country("Germany", "Europe"));
        Country france = countryRepository.save(new Country("France", "Europe"));
        Country italy = countryRepository.save(new Country("Italy", "Europe"));

        // Initialize authors
        Author author1 = authorRepository.save(new Author("John", "Doe", usa));
        Author author2 = authorRepository.save(new Author("Emily", "Smith", uk));
        Author author3 = authorRepository.save(new Author("Hans", "Müller", germany));
        Author author4 = authorRepository.save(new Author("Pierre", "Dupont", france));
        Author author5 = authorRepository.save(new Author("Giovanni", "Rossi", italy));

        // Initialize books
        bookRepository.save(new Book("The Great Adventure", CategoryBook.NOVEL, 0, author1, LocalDate.of(2022, 5, 2)));
        bookRepository.save(new Book("History of Europe", CategoryBook.HISTORY, 5, author1, LocalDate.of(2015, 5, 2)));
        bookRepository.save(new Book("A little bit of drama", CategoryBook.DRAMA, 8, author1, LocalDate.of(2012, 2, 2)));
        bookRepository.save(new Book("Alien War", CategoryBook.FANTASY, 12, author4, LocalDate.of(2010, 2, 2)));
        bookRepository.save(new Book("The Art of War", CategoryBook.CLASSICS, 7, author5, LocalDate.of(2005, 2, 2)));


        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Ljubomir",
                "Ilievski",
                Role.ROLE_ADMIN
        ));

    }
}