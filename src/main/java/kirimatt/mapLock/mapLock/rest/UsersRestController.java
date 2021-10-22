package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.User;
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
    private List<User> users = Stream.of(
            new User(1L, "kirimatt", "radical"),
            new User(2L, "seal", "seal")
    ).collect(Collectors.toList());

    @GetMapping
    public List<User> getAll() {
        return users;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public User getById(@PathVariable Long id) {
        return users.stream().filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public User create(@RequestBody User user) {
        this.users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable long id) {
        this.users.removeIf(user -> user.getId().equals(id));
    }


}
