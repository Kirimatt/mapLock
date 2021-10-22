package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author azamat
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {
    private List<Developer> developers = Stream.of(
            new Developer(1L, "kirimatt", "radical"),
            new Developer(2L, "seal", "seal")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Developer getById(@PathVariable Long id) {
        return developers.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public Developer create(@RequestBody Developer developer) {
        this.developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable long id) {
        this.developers.removeIf(developer -> developer.getId().equals(id));
    }


}
