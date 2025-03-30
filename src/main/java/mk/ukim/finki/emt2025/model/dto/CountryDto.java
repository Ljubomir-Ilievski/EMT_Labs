package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;

public class CountryDto {

    String name;
    String continent;

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
