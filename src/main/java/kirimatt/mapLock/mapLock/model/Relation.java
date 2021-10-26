package kirimatt.mapLock.mapLock.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс-сущность пары между пользователями
 *
 * @author kirimatt
 */
@Data
@Entity
@Table(name = "relationships")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_user_id")
    private long firstUserId;
    @Column(name = "second_user_id")
    private long secondUserId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "approved")
    private boolean approved;
}
