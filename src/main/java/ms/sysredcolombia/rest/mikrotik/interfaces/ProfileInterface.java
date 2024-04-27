package ms.sysredcolombia.rest.mikrotik.interfaces;

import me.legrange.mikrotik.MikrotikApiException;
import ms.sysredcolombia.rest.mikrotik.modelo.Profile;

import java.util.List;

public interface ProfileInterface {

   List<Profile> getProfiles ();
   List<Profile> createAllProfile(List<Profile> profileList)
           throws MikrotikApiException;
}
