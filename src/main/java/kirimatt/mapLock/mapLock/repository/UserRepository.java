package kirimatt.mapLock.mapLock.repository;

import kirimatt.mapLock.mapLock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс репозитория пользователя
 *
 * @author azamat
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByActivationCode(String code);

    @Override
    <S extends User> S saveAndFlush(S entity);
}
