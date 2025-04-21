package mk.ukim.finki.emt2025.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt2025.model.views.AuthorsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthorsPerCountryRepository extends JpaRepository<AuthorsPerCountryView, Long> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.number_of_authors_per_country", nativeQuery = true)
    public void refreshMaterializedView();



}
