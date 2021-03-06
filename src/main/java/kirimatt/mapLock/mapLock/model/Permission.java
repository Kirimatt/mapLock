package kirimatt.mapLock.mapLock.model;

/**
 * Перечисление доступных прав пользователя
 *
 * @author azamat
 */
public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE_CREDENTIALS("users:writeCredentials"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
