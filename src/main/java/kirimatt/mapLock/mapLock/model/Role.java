package kirimatt.mapLock.mapLock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author azamat
 */

@AllArgsConstructor
@Getter
public enum Role {
    USER(Set.of(Permisson.USERS_READ)),
    ADMIN(Set.of(Permisson.USERS_READ, Permisson.USERS_WRITE));

    private final Set<Permisson> permissons;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissons().stream()
                .map(permisson -> new SimpleGrantedAuthority(permisson.getPermission()))
                .collect(Collectors.toSet());
    }
}
