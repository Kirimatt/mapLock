package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.User;
import kirimatt.mapLock.mapLock.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для отображения всех пользователей
 *
 * @author azamat
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    @Deprecated
    @PreAuthorize("hasAuthority('users:write')")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }


}
