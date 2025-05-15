package mk.ukim.finki.emt2025.model.dto;


public class CreateAuthorDto {


    String name;
    String surname;
    Long country;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
