package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.dto.*;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import mk.ukim.finki.emt2025.service.domain.BookService;
import mk.ukim.finki.emt2025.service.domain.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService.update(id, createCountryDto).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> deleteById(Long id) {
        return countryService.deleteById(id).map(DisplayCountryDto::from);
    }
}
