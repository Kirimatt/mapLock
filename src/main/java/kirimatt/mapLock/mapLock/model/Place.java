package kirimatt.mapLock.mapLock.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс-сущность местоположения
 *
 * @author kirimatt
 */
@Data
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "relation_id")
    private long relationId;

}
