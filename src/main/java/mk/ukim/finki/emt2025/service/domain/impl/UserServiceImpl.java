package mk.ukim.finki.emt2025.service.domain.impl;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.domain.TokenLog;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.Enumerations.Role;
import mk.ukim.finki.emt2025.model.exceptions.*;
import mk.ukim.finki.emt2025.repository.BookRepository;
import mk.ukim.finki.emt2025.repository.TokenLogRepository;
import mk.ukim.finki.emt2025.repository.UserRepository;
import mk.ukim.finki.emt2025.security.JwtConstants;
import mk.ukim.finki.emt2025.security.JwtHelper;
import mk.ukim.finki.emt2025.service.domain.BookService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;
    private final BookRepository bookRepository;

    private final TokenLogRepository tokenLogRepository;

    private final JwtHelper jwtHelper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, BookService bookService, BookRepository bookRepository, TokenLogRepository tokenLogRepository, JwtHelper jwtHelper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookService = bookService;

        this.bookRepository = bookRepository;
        this.tokenLogRepository = tokenLogRepository;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }



    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {



        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        User user = userRepository.findByUsername(username).orElseThrow(InvalidUsernameOrPasswordException::new);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidUserCredentialsException();

        String token = jwtHelper.generateToken(user);
        tokenLogRepository.save(new TokenLog(token, jwtHelper.extractIssuedAtDate(token), jwtHelper.extractExpiration(token), user));


        return token;
    }


    @Override
    public void addToWishList(String token, Long bookId) throws NoAvailableBooksException {
       String username = jwtHelper.extractUsername(token.substring(JwtConstants.TOKEN_PREFIX.length()));

       User user =  findByUsername(username);
       Book book =  bookService.findById(bookId).orElseThrow(() -> new BookNotFoundExepction(bookId));

       if (book.getAvailableCopies() == 0){
           throw new NoAvailableBooksException();
       }

       user.getWishListedBooks().add(book);
       userRepository.save(user);

    }

    @Override
    public List<Book> listWishListed(String token) {
        String username = jwtHelper.extractUsername(token.substring(JwtConstants.TOKEN_PREFIX.length()));
       User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
       return user.getWishListedBooks();
    }

    @Override
    public void rentAllWishListed(String token) {
        String username = jwtHelper.extractUsername(token.substring(JwtConstants.TOKEN_PREFIX.length()));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        user.getWishListedBooks().forEach(book -> {book.setAvailableCopies(book.getAvailableCopies() - 1);
                                                    bookRepository.save(book);});


    }

    @Override
    public List<User> listUsersLazyWishListed() {
        List<User> userlist = userRepository.findAllLazyWishListed();
        return userRepository.findAllLazyWishListed();
    }

    @Override
    public List<TokenLog> listTokenLogsList() {
        return tokenLogRepository.findAll();
    }


}
