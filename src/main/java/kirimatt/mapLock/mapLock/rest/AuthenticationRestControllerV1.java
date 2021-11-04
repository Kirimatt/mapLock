package kirimatt.mapLock.mapLock.rest;

import kirimatt.mapLock.mapLock.model.User;
import kirimatt.mapLock.mapLock.repository.UserRepository;
import kirimatt.mapLock.mapLock.security.JwtTokenProvider;
import kirimatt.mapLock.mapLock.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest-контроллер отвечающий за взаимодействие с пользователем
 *
 * @author azamat
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());

            Map<Object, Object> response = new HashMap<>();

            response.put("email", request.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user) {
        return ResponseEntity.ok(userDetailService.addUser(user));
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<?> activate(Model model,
                                      @PathVariable String code) {
        boolean isActivated = userDetailService.activate(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
            return ResponseEntity.ok(model);
        } else {
            model.addAttribute("message", "Activation code is not found");
            return new ResponseEntity<>(model, HttpStatus.FORBIDDEN);
        }
    }
}
