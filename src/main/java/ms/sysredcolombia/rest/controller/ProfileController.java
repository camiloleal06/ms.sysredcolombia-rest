package ms.sysredcolombia.rest.controller;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.mikrotik.interfaces.ProfileInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileInterface profileInterface;

}
