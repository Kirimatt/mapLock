package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.Place;
import kirimatt.mapLock.mapLock.service.PlaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для отображения мест, где пользователи закрепили замок.
 *
 * @author kirimatt
 */
@RestController
@RequestMapping("/api/v1/places")
public class PlacesRestController {
    private final PlaceService placeService;

    public PlacesRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<Place> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Place getByRelationId(@PathVariable Long id) {
        return placeService.getByRelationId(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:writeCredentials')")
    public Place addPlace(@RequestBody Place place) {
        return placeService.addPlace(place);
    }
}
