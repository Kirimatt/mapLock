package kirimatt.mapLock.mapLock.сontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author azamat
 */
@Deprecated
@RestController
@RequestMapping("")
public class MapLockController {

    @GetMapping("")
    public ResponseEntity<?> getLock() {
        try {
            return ResponseEntity.ok("РАБОТАЕМ!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
