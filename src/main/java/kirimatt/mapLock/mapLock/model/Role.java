package kirimatt.mapLock.mapLock.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Перечисление ролей с правами
 *
 * @author azamat
 */
public enum Role {
    USER(Set.of(Permission.USERS_READ, Permission.USERS_WRITE_CREDENTIALS)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_WRITE_CREDENTIALS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
