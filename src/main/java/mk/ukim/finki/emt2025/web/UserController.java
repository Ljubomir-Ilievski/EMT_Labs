package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt2025.model.dto.*;
import mk.ukim.finki.emt2025.model.exceptions.*;
import mk.ukim.finki.emt2025.security.JwtFilter;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;


    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )


    @PostMapping("/login")
    public ResponseEntity<LoginRegisterResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PutMapping("/wishlist/{id}")
    ResponseEntity<Void> addToWishList(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {

       try {
           userApplicationService.addToWishList(token, id);
           return ResponseEntity.ok().build();

       }
       catch (UsernameNotFoundException | BookNotFoundExepction usernameNotFoundException){
           return ResponseEntity.notFound().build();
       }
       catch (NoAvailableBooksException e) {
          return ResponseEntity.badRequest().build();
       }

    }

    @GetMapping("/wishlist")
    ResponseEntity<List<DisplayBookDto>> listWishListed(@RequestHeader(value = "Authorization", required = false) String token) {

        try {
            List<DisplayBookDto> displayBookDtoList =  userApplicationService.listWishListed(token);
            return ResponseEntity.ok(displayBookDtoList);

        }
        catch (UsernameNotFoundException usernameNotFoundException){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/wishlist/rentAll")
    ResponseEntity<List<DisplayBookDto>> rentAllWishListed(@RequestHeader(value = "Authorization", required = false) String token) {

        try {
            userApplicationService.rentAllWishListed(token);
            return ResponseEntity.ok().build();

        }
        catch (UsernameNotFoundException usernameNotFoundException){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping()
    List<DisplayUserDto> userList() {

        return userApplicationService.listUsersLazyWishListed();

    }

    @GetMapping("/auth-logs")
    public List<DisplayTokenInfoDto> tokenInfoDtoList(){

        return userApplicationService.listTokenLogsList();

    }

}

