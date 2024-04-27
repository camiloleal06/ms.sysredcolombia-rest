package ms.sysredcolombia.rest.controller;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.entidades.UserEntity;
import ms.sysredcolombia.rest.modelo.interfaces.UserInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserInterface userInterface;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userInterface.saveUser(userEntity),
                HttpStatus.CREATED);
    }
    @PostMapping("/registrar")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userInterface.registerUser(userEntity),
                HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserEntity> findAll() {
        return userInterface.findAll();

    }
}
