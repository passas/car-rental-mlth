package org.acme.db.psql.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.acme.app.service.user.PasswordService;
import org.acme.db.psql.entity.user.RoleEntity;
import org.acme.db.psql.entity.user.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UserRepositoryTest
{
    RoleRepository roleRepository;
    UserRepository userRepository;

    public UserRepositoryTest(RoleRepository roleRepository, UserRepository userRepository)
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    @TestTransaction // delete
    public void testCreateUser()
    {
        String username = "u0";
        String password = "u0";

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(PasswordService.hashPassword(password));
        RoleEntity role = this.roleRepository.findByName("user");
        user.addRole(role);
        this.userRepository.persist(user);
        this.userRepository.flush();

        UserEntity ok = this.userRepository.findByUsername(username);
        Assertions.assertNotNull(ok);
    }
}