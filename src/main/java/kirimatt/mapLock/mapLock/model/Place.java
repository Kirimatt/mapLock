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
    private Long id;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "relation_id")
    private Long relationId;

}
