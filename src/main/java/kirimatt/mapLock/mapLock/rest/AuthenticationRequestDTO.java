package kirimatt.mapLock.mapLock.rest;

import lombok.Data;

/**
 * Класс сущность для авторизации
 *
 * @author azamat
 */
@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
