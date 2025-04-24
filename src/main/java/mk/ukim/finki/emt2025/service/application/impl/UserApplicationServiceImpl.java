package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.dto.*;
import mk.ukim.finki.emt2025.model.exceptions.NoAvailableBooksException;
import mk.ukim.finki.emt2025.security.JwtHelper;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
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
    public Optional<LoginRegisterResponseDto> login(LoginUserDto loginUserDto) {
        User currentUser = userService.findByUsername(loginUserDto.username());



        String token = userService.login(loginUserDto.username(), loginUserDto.password());



        return Optional.of(new LoginRegisterResponseDto(jwtHelper.generateToken(currentUser)));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public void addToWishList(String token, Long bookId) throws NoAvailableBooksException {
        userService.addToWishList(token, bookId);
    }

    @Override
    public List<DisplayBookDto> listWishListed(String token) {
        return DisplayBookDto.from(userService.listWishListed(token));
    }

    @Override
    public void rentAllWishListed(String token) {
        userService.rentAllWishListed(token);
    }

    @Override
    public List<DisplayUserDto> listUsersLazyWishListed() {
        return DisplayUserDto.from(userService.listUsersLazyWishListed());
    }

    @Override
    public List<DisplayTokenInfoDto> listTokenLogsList() {
        return DisplayTokenInfoDto.from(userService.listTokenLogsList());
    }


}
