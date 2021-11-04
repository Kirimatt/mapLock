package kirimatt.mapLock.mapLock.repository;

import kirimatt.mapLock.mapLock.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author kirimatt
 */
public interface RelationshipRepository extends JpaRepository<Relation, Long> {
    Optional<Relation> findById(Long id);

    Optional<List<Relation>> findAllByFirstUserIdOrSecondUserId(Long id, Long sameId);

    Optional<List<Relation>> findAllByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId);

}
