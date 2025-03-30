package mk.ukim.finki.emt2025.service;

import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, CountryDto countryDto);

    Optional<Country> save(CountryDto countryDto);

    void deleteById(Long id);

}
