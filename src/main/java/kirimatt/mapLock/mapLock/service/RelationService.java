package kirimatt.mapLock.mapLock.service;

import kirimatt.mapLock.mapLock.exceptions.RelationNotFoundException;
import kirimatt.mapLock.mapLock.model.Relation;
import kirimatt.mapLock.mapLock.repository.RelationshipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для отображения отношений
 * @author kirimatt
 */
@Service
public class RelationService {
    private final RelationshipRepository relationshipRepository;

    public RelationService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public List<Relation> getAll() {
        return relationshipRepository.findAll();
    }

    /**
     * Метод для нахождения отношений по ID пользователей
     *
     * @param firstId  первый ID
     * @param secondId второй ID
     * @return Возвращает закрепленные отношения по отсортированным ID из пары.
     */
    public List<Relation> getAllByUsersId(Long firstId, Long secondId) {
        Long minId = Math.min(firstId, secondId);
        Long maxId = Math.max(firstId, secondId);

        return relationshipRepository.findAllByFirstUserIdAndSecondUserId(minId, maxId)
                .orElseThrow(() -> new RelationNotFoundException("Relation with ID " + minId + " and " +
                        maxId + " not found"));
    }


    /**
     * Метод для нахождения отношений по ID пользователя
     * Принимает только один параметр и подставляет под два случая пользователей в таблице.
     * @param id  идентификатор пользователя
     * @return Возвращает закрепленные отношения данного пользователя.
     */
    public List<Relation> getAllByOneUserId(Long id) {
        return relationshipRepository.findAllByFirstUserIdOrSecondUserId(id, id)
                .orElseThrow(() -> new RelationNotFoundException("Relation with ID " + id + " not found"));
    }

    /**
     * Добавляет отношение, если такового нет и выставляет подтвержденность отклоненной.
     * В случае если пара отношений отсылает два одинаковых запроса, между ними появляется связь на все отношения и
     * выставляет подтвержденность успешной.
     *
     * @param relation Отношение
     * @return Возвращает список отношений между двумя пользователями.
     */
    public List<Relation> addRelation(Relation relation) {
        //TODO: Проверить работоспособность. Возможно, данный алгоритм не учитывает множественной связи.
        try {
            Optional<List<Relation>> relations = relationshipRepository
                    .findAllByFirstUserIdAndSecondUserId(
                            relation.getFirstUserId(),
                            relation.getSecondUserId());

            if (relations.isEmpty() || relations.get().size() == 0)
                throw new RelationNotFoundException("Relation with that credentials not found" + relation);
            else
                relations.get().forEach(relation1 -> relation1.setApproved(true));

            return relationshipRepository.saveAll(relations.get());

        } catch (RelationNotFoundException e) {

            relation.setDate(LocalDateTime.now());
            relation.setApproved(false);

            return List.of(relationshipRepository.saveAndFlush(relation));
        }
    }

}
