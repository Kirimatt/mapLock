package kirimatt.mapLock.mapLock.repository;

import kirimatt.mapLock.mapLock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author azamat
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}