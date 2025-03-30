package mk.ukim.finki.emt2025.service.impl;

import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, CountryDto countryDto) {
        return countryRepository.findById(id)
                .map(country -> {
                    if (countryDto.getName() != null || !countryDto.getName().isEmpty()){
                        country.setName(countryDto.getName());
                    }
                    if (countryDto.getContinent() != null || !countryDto.getContinent().isEmpty()){
                        country.setName(countryDto.getName());
                    }
                    countryRepository.save(country);
                    return country;
                });
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        if (countryDto.getContinent() != null && countryDto.getName() != null){
            return Optional.of(countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent())));
        }
        return Optional.empty();

    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
