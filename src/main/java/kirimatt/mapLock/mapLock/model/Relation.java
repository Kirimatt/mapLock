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
    private Long id;
    @Column(name = "first_user_id")
    private Long firstUserId;
    @Column(name = "second_user_id")
    private Long secondUserId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "approved")
    private Boolean approved;
}
