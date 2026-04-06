package org.acme.db.psql.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.acme.app.service.user.PasswordService;
import org.acme.db.psql.entity.car.CarEntity;
import org.acme.db.psql.entity.user.RoleEntity;
import org.acme.db.psql.entity.user.UserEntity;
import org.acme.db.psql.repository.car.CarRepository;
import org.acme.db.psql.repository.user.RoleRepository;
import org.acme.db.psql.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class CarRepositoryTest
{
    CarRepository carRepository;

    public CarRepositoryTest(CarRepository carRepository)
    {
        this.carRepository = new CarRepository();
    }

    @Test
    @Transactional
    public void testFindAll()
    {
        List<CarEntity> wtv = this.carRepository.findAll().list();
        Assertions.assertNotNull(wtv);
    }
}