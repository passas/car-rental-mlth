package org.acme.db.psql.entity.reservation;

import jakarta.persistence.*;
import org.acme.db.psql.entity.car.CarEntity;
import org.acme.db.psql.entity.user.UserEntity;

import java.time.Instant;

@Entity(name="reservation")
public class ReservationEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservation_id")
    @SequenceGenerator(name = "seq_reservation_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity carEntity;

    @Column(name="from_date")
    private Instant dateFrom;

    @Column(name="to_date")
    private Instant dateTo;

    @Column(name = "period", columnDefinition = "tstzrange", insertable = false, updatable = false)
    private String period;
    // this.period = "[%s,%s)".formatted(this.dateFrom.toString(), this.dateTo.toString());

    public ReservationEntity()
    {
        this.id = null;
        this.userEntity = null;
        this.carEntity = null;
        this.dateFrom = null;
        this.dateTo = null;
        this.period = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    public void setCarEntity(CarEntity carEntity)
    {
        this.carEntity = carEntity;
    }

    public void setDateFrom(Instant dateFrom)
    {
        this.dateFrom = Instant.from(dateFrom);
    }

    public void setDateTo(Instant dateTo)
    {
        this.dateTo = Instant.from(dateTo);
    }

    public void setPeriod(String period)
    {
        this.period = period;
    }

    public Long getId()
    {
        return this.id;
    }

    public UserEntity getUserEntity()
    {
        return this.userEntity;
    }

    public CarEntity getCarEntity()
    {
        return this.carEntity;
    }

    public Instant getDateFrom()
    {
        return Instant.from(this.dateFrom);
    }

    public Instant getDateTo()
    {
        return Instant.from(this.dateTo);
    }

    public String getPeriod()
    {
        return this.period;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", userEntity: " + this.userEntity
                + ", carEntity: " + this.carEntity
                + ", dateFrom: " + this.dateFrom
                + ", dateTo: " + this.dateTo
                + ", period: " + this.period
                + "]";
    }
}
