package org.acme.app.service.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.user.UserModel;
import org.acme.app.model.user.mapper.UserModelMapper;
//import org.acme.app.service.exception.passwordservice.PasswordServicePasswordNotMatchException;
import org.acme.app.service.user.exception.UserNotFoundException;
import org.acme.app.service.user.exception.WrongCredentialsException;
import org.acme.db.psql.entity.user.RoleEntity;
import org.acme.db.psql.entity.user.UserEntity;
import org.acme.db.psql.repository.user.RoleRepository;
import org.acme.db.psql.repository.user.UserRepository;
import org.acme.db.psql.repository.user.exception.UserRepositoryConstraintUsernameUniqueException;
//import org.acme.db.psql.repository.user.exception.user.UserRepositoryUserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserService
{
    private Logger log;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserService(RoleRepository roleRepository, UserRepository userRepository)
    {
        this.log = Logger.getLogger(UserService.class.getSimpleName());
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
     * @return Token's lifespan in milliseconds.
     */
    public long getTokenExpiration()
    {
        return JWTService.getTokenExpiration();
    }

    /**
     * This method shall register a new user in the database.
     * @param userModel The user's username & password container.
     * @return A user container with its new internal id.
     * @throws UserRepositoryConstraintUsernameUniqueException Username already in use.
     */
    @Transactional
    public UserModel createUser(UserModel userModel) throws UserRepositoryConstraintUsernameUniqueException
    {
        // User roled caped!
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(PasswordService.hashPassword(userModel.getPassword()));
        RoleEntity roleEntity = this.roleRepository.findByName("user");
        userEntity.addRole(roleEntity);
        this.log.infof("Registering a new user: %s", userEntity);
        try
        {
            this.userRepository.persist(userEntity);
            this.userRepository.flush();
        }
        catch(ConstraintViolationException e)
        {
            if ("_user_username_unique".equals(e.getConstraintName()))
            {
                this.log.error("Error while trying to register a new user: username already exists!!!");
                throw new UserRepositoryConstraintUsernameUniqueException();
            }
        }
        UserModel model = UserModelMapper.fromEntity(userEntity);
        this.log.infof("New user registered: %s", model);
        return model;
    }

    /**
     * Create a JW authentication token
     *
     * @param userModel Username and password container.
     * @return A JSON Web Token containing claims like: user id and roles.
     * @throws UserNotFoundException Username does not exist in database
     * @throws WrongCredentialsException Wrong credentials
     */
    @Transactional
    public String createToken(UserModel userModel) throws UserNotFoundException, WrongCredentialsException
    {
        UserEntity userEntity = this.userRepository.findByUsername(userModel.getUsername());
        if (userEntity == null)
        {
            this.log.error("Error while generating a new authentication token: username not found.");
            throw new UserNotFoundException();
        }
        else if (!PasswordService.verifyPassword(userEntity.getPassword(), userModel.getPassword()))
        {
            this.log.error("Error while generating a new authentication token: wrong credentials.");
            throw new WrongCredentialsException();
        }
        String token = JWTService.generateSignedToken(userEntity.getId(), userEntity.getUsername(), userEntity.getRoles().stream().map(RoleEntity::getName).toList());
        this.log.info("New authorization token generated!");
        return token;
    }
}
