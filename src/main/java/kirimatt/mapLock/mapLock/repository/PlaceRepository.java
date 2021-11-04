package kirimatt.mapLock.mapLock.repository;

import kirimatt.mapLock.mapLock.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс репозитория координат
 *
 * @author kirimatt
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByRelationId(Long id);

}
