package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.Place;
import kirimatt.mapLock.mapLock.model.Relation;
import kirimatt.mapLock.mapLock.service.PlaceService;
import kirimatt.mapLock.mapLock.service.RelationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для отображения отношений между пользователями
 *
 * @author kirimatt
 */
@RestController
@RequestMapping("/api/v1/relations")
public class RelationshipsRestController {
    private final RelationService relationService;

    public RelationshipsRestController(RelationService relationService) {
        this.relationService = relationService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<Relation> getAll() {
        return relationService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public List<Relation> getByUserId(@PathVariable Long id) {
        return relationService.getAllByOneUserId(id);
    }

    @GetMapping("/firstUser/{firstId}/secondUser/{secondId}")
    @PreAuthorize("hasAuthority('users:read')")
    public List<Relation> getByUserId(@PathVariable Long firstId, @PathVariable Long secondId) {
        return relationService.getAllByUsersId(firstId, secondId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:writeCredentials')")
    public List<Relation> addRelation(@RequestBody Relation relation) {

        return relationService.addRelation(relation);
    }
}
