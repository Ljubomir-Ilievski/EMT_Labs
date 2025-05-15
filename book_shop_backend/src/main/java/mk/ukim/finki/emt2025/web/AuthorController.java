package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.model.dto.DisplayAuthorDto;
import mk.ukim.finki.emt2025.model.events.RefreshAuthorPerCountryViewEvent;
import mk.ukim.finki.emt2025.model.projections.NameSurnameAuthorProjectionDto;
import mk.ukim.finki.emt2025.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emt2025.service.application.AuthorApplicationService;
import mk.ukim.finki.emt2025.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/authors")
@Tag(name = "Author API", description = "Manipulate with Authors")
public class AuthorController{

    private final CountryService countryService;
    private final AuthorApplicationService authorApplicationService;

    private final ApplicationEventPublisher applicationEventPublisher;


    public AuthorController(CountryService countryService, AuthorApplicationService authorApplicationService, ApplicationEventPublisher applicationEventPublisher) {
        this.countryService = countryService;
        this.authorApplicationService = authorApplicationService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Operation(summary = "List Books", description = "List all of the books in the DB, is the response is empty, there are no books in the DB")
    @PostMapping(value = "/add")
    public ResponseEntity<DisplayAuthorDto> addAuthor(@RequestBody CreateAuthorDto createAuthorDto){
        ResponseEntity<DisplayAuthorDto> displayAuthorDtoResponseEntity = authorApplicationService.save(createAuthorDto).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
        applicationEventPublisher.publishEvent(new RefreshAuthorPerCountryViewEvent());

        return displayAuthorDtoResponseEntity;
    }

    @Operation(summary = "Get authorsPerCountry", description = "Get the number of authors per country")
    @GetMapping(value = "/by-country")
    public List<AuthorsPerCountryView> authorsPerCountryViews(){

        return countryService.authorsPerCountry();

    }

    @GetMapping(value = "/names")
    public List<NameSurnameAuthorProjectionDto> nameSurnameProjection(){
        return authorApplicationService.NameSurnameProjection();
    }








}
