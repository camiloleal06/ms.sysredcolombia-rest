package ms.sysredcolombia.rest.modelo.interfaces;

import ms.sysredcolombia.rest.modelo.entidades.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    List<UserEntity> findAll ();
    UserEntity findById (Long Id);
    UserEntity saveUser (UserEntity userEntity);
    UserEntity registerUser (UserEntity userEntity);
    Optional<UserEntity> findByUsername(String username);
}
