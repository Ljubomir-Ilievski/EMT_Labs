package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);



    @EntityGraph(attributePaths = {}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT lu FROM User lu")
    List<User> findAllLazyWishListed();


}
