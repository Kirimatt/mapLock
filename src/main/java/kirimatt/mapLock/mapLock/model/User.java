package kirimatt.mapLock.mapLock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author azamat
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Long id;
    private String firstName;
    private String lastName;
}
