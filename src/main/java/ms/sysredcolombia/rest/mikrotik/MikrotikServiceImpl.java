package ms.sysredcolombia.rest.mikrotik;

import lombok.AllArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import ms.sysredcolombia.rest.mikrotik.interfaces.ProfileInterface;
import ms.sysredcolombia.rest.mikrotik.modelo.Profile;
import ms.sysredcolombia.rest.mikrotik.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MikrotikServiceImpl extends Conectar implements ProfileInterface {

    private final ProfileRepository profileRepository;

    public List<Profile> getProfilesMikrotik() throws MikrotikApiException {
        List<Profile> listProfile = new ArrayList<>();

        connect();
        for (Map<String, String> stringMap : con.execute(
                "/ppp/profile/print")) {
            listProfile.add(Profile.builder().profileName(stringMap.get("name"))
                    .build());
        }
        disconnect();

        return listProfile
                .stream()
                .filter(profile -> !profile.getProfileName()
                .contains("default"))
                .toList();
    }

    @Override
    public List<Profile> getProfiles() {
        return this.profileRepository.findAll();
    }

    @Override
    public List<Profile> createAllProfile(List<Profile> profileList)
            throws MikrotikApiException {

        if (profileList.size() != this.getProfiles().size()) {
            return profileRepository.saveAll(this.getProfilesMikrotik());
        }
        return Collections.emptyList();
    }
}