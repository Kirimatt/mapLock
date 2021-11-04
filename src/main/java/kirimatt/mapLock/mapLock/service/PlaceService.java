package kirimatt.mapLock.mapLock.service;

import kirimatt.mapLock.mapLock.exceptions.PlaceNotFoundException;
import kirimatt.mapLock.mapLock.model.Place;
import kirimatt.mapLock.mapLock.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервис для взаимодействия с репозиторием координат
 *
 * @author kirimatt
 */
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    public Place getByRelationId(Long id) {
        return placeRepository.findByRelationId(id)
                .orElseThrow(() -> new PlaceNotFoundException("Place with id " + id + " not found"));
    }

    public Place addPlace(Place place) {
        return placeRepository.saveAndFlush(place);
    }
}
