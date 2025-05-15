package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.CreateCountryDto;
import mk.ukim.finki.emt2025.model.dto.DisplayCountryDto;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Manage Countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Add Country", description = "Create a new country entry.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto dto) {
        return countryApplicationService
                .save(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get All Countries", description = "Return all countries.")
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get Country by ID", description = "Return a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit Country", description = "Edit an existing country by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> edit(@PathVariable Long id, @RequestBody CreateCountryDto dto) {
        return countryApplicationService
                .update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete Country", description = "Delete a country by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        countryApplicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
