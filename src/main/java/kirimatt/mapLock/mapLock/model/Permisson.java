package kirimatt.mapLock.mapLock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author azamat
 */
@AllArgsConstructor
@Getter
public enum Permisson {
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;
}
