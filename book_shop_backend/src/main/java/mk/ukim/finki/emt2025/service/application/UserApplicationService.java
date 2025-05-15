package mk.ukim.finki.emt2025.service.application;


import mk.ukim.finki.emt2025.model.dto.*;
import mk.ukim.finki.emt2025.model.exceptions.NoAvailableBooksException;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginRegisterResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    void addToWishList(String token, Long bookId) throws NoAvailableBooksException;

    List<DisplayBookDto> listWishListed(String token);

    void rentAllWishListed(String token);

    List<DisplayUserDto> listUsersLazyWishListed();

    List<DisplayTokenInfoDto> listTokenLogsList();
}

