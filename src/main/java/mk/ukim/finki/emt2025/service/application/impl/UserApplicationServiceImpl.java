package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.dto.CreateUserDto;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.model.dto.DisplayUserDto;
import mk.ukim.finki.emt2025.model.dto.LoginUserDto;
import mk.ukim.finki.emt2025.model.exceptions.NoAvailableBooksException;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public void addToWishList(String username, Long bookId) throws NoAvailableBooksException {
        userService.addToWishList(username, bookId);
    }

    @Override
    public List<DisplayBookDto> listWishListed(String username) {
        return DisplayBookDto.from(userService.listWishListed(username));
    }

    @Override
    public void rentAllWishListed(String username) {
        userService.rentAllWishListed(username);
    }


}
