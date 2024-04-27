package ms.sysredcolombia.rest.service;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.entidades.Role;
import ms.sysredcolombia.rest.modelo.entidades.UserEntity;
import ms.sysredcolombia.rest.modelo.interfaces.UserInterface;
import ms.sysredcolombia.rest.modelo.repository.RoleRepository;
import ms.sysredcolombia.rest.modelo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ms.sysredcolombia.rest.utils.Constantes.ROLE_ADMIN;
import static ms.sysredcolombia.rest.utils.Constantes.ROLE_USER;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        Optional<Role> roleUser = this.roleRepository.findByNombre(ROLE_USER);
        List<Role> roleList = new ArrayList<>();
        roleUser.ifPresent(roleList::add);
        if (userEntity.isAdmin()) {
            Optional<Role> roleUserAdmin = this.roleRepository.findByNombre(
                    ROLE_ADMIN);
            roleUserAdmin.ifPresent(roleList::add);
        }
        userEntity.setRoles(roleList);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity registerUser(UserEntity userEntity) {
        userEntity.setAdmin(false);
        return this.saveUser(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.findByUsername(username);
    }
}
