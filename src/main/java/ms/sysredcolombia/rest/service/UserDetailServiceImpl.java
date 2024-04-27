package ms.sysredcolombia.rest.service;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.entidades.UserEntity;
import ms.sysredcolombia.rest.modelo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = this.userRepository.findByUsername(
                username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("No existe el usuario");
        }

        UserEntity userEntity = userOptional.orElseThrow();
        List<SimpleGrantedAuthority> grantedAuthorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .toList();
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                grantedAuthorities);
    }
}
