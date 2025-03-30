package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsSoftDeleted(Boolean isSoftDeleted);

    Optional<Book> findByIdAndIsSoftDeleted(Long id, Boolean isSoftDeleted);
}
