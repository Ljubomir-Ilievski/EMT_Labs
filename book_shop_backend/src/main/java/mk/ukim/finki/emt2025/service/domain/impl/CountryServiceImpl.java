package mk.ukim.finki.emt2025.service.domain.impl;

import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.model.dto.CreateCountryDto;
import mk.ukim.finki.emt2025.model.events.RefreshAuthorPerCountryViewEvent;
import mk.ukim.finki.emt2025.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emt2025.repository.AuthorsPerCountryRepository;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.service.domain.CountryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final AuthorsPerCountryRepository authorsPerCountryRepository;

    public CountryServiceImpl(CountryRepository countryRepository, AuthorsPerCountryRepository authorsPerCountryRepository) {
        this.countryRepository = countryRepository;
        this.authorsPerCountryRepository = authorsPerCountryRepository;
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
    public Optional<Country> update(Long id, CreateCountryDto createCountryDto) {
        return countryRepository.findById(id)
                .map(country -> {
                    if (createCountryDto.getName() != null || !createCountryDto.getName().isEmpty()){
                        country.setName(createCountryDto.getName());
                    }
                    if (createCountryDto.getContinent() != null || !createCountryDto.getContinent().isEmpty()){
                        country.setName(createCountryDto.getName());
                    }
                    countryRepository.save(country);
                    return country;
                });
    }

    @Override
    public Optional<Country> save(CreateCountryDto createCountryDto) {
        if (createCountryDto.getContinent() != null && createCountryDto.getName() != null){
            return Optional.of(countryRepository.save(new Country(createCountryDto.getName(), createCountryDto.getContinent())));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Country> deleteById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        countryRepository.deleteById(id);
        return optionalCountry;
    }

    @Override
    public List<AuthorsPerCountryView> authorsPerCountry() {
        return authorsPerCountryRepository.findAll();
    }

    @Override
    @EventListener()
    public void RefreshAuthorsPerCountryView(RefreshAuthorPerCountryViewEvent event){
        authorsPerCountryRepository.refreshMaterializedView();
    }

}
