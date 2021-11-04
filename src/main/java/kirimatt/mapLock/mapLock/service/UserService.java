package kirimatt.mapLock.mapLock.service;

import kirimatt.mapLock.mapLock.exceptions.UserNotFoundByIdException;
import kirimatt.mapLock.mapLock.model.User;
import kirimatt.mapLock.mapLock.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс сервис для отображения пользователей
 *
 * @author kirimatt
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll().stream().peek(user -> user.setPassword(null))
                .collect(Collectors.toList());
    }

    public User getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundByIdException("User with id " + id + " not found"));
        user.setPassword(null);

        return user;
    }

    @Deprecated
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
