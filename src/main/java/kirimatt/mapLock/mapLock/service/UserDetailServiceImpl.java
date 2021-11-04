package kirimatt.mapLock.mapLock.service;

import kirimatt.mapLock.mapLock.model.Role;
import kirimatt.mapLock.mapLock.model.Status;
import kirimatt.mapLock.mapLock.model.User;
import kirimatt.mapLock.mapLock.repository.UserRepository;
import kirimatt.mapLock.mapLock.exceptions.JwtAuthenticationException;
import kirimatt.mapLock.mapLock.security.SecurityUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author azamat
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ApplicationContext context;
    private final MailSender mailSender;

    @Value("${mail.url}")
    private String urlToSend;

    public UserDetailServiceImpl(UserRepository userRepository, ApplicationContext context, MailSender mailSender) {
        this.userRepository = userRepository;
        this.context = context;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }

    public boolean addUser(User user) {
        try {
            userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        } catch (UsernameNotFoundException e) {

            //TODO: Передавать открытый пароль по сети не есть хорошо, поэтому надо извлекать хэш на конечном
            // приложении и отправлять.
            user.setPassword(
                    context.getBean(PasswordEncoder.class).encode(user.getPassword())
            );
            user.setRole(Role.USER);
            user.setStatus(Status.BANNED);

            user.setActivationCode(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);

            if (!user.getEmail().isEmpty()) {
                String message = String.format(
                        "Hello, %s \n" +
                                "Welcome to Map Lock. Please, visit next link: " +
                                "%s/%s",
                        user.getFirstName(),
                        urlToSend,
                        user.getActivationCode()
                );

                mailSender.send(user.getEmail(), "Activation code", message);
            }
        }

        return false;

    }

    public boolean activate(String code) {

        User user;
        try {
            user = userRepository.findByActivationCode(code)
                    .orElseThrow(() -> new JwtAuthenticationException("Activation code is invalid or not created!"));
        } catch (JwtAuthenticationException e) {
            return false;
        }
        user.setActivationCode(null);

        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        return true;

    }
}
