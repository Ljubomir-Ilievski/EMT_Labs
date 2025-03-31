package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.model.dto.CreateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, CreateCountryDto createCountryDto);

    Optional<Country> save(CreateCountryDto createCountryDto);

    Optional<Country> deleteById(Long id);

}
