package mk.ukim.finki.emt2025.repository;


import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.projections.NameSurnameAuthorProjectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT new mk.ukim.finki.emt2025.model.projections.NameSurnameAuthorProjectionDto(a.name, a.surname) " +
            "FROM Author a")
    public List<NameSurnameAuthorProjectionDto> nameSurnameProjection();

}
