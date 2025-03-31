package mk.ukim.finki.emt2025.service.application;


import mk.ukim.finki.emt2025.model.dto.CreateUserDto;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.model.dto.DisplayUserDto;
import mk.ukim.finki.emt2025.model.dto.LoginUserDto;
import mk.ukim.finki.emt2025.model.exceptions.NoAvailableBooksException;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    void addToWishList(String username, Long bookId) throws NoAvailableBooksException;

    List<DisplayBookDto> listWishListed(String username);

    void rentAllWishListed(String username);
}

