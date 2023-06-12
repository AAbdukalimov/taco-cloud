package sia.tacocloud.services.user;

import sia.tacocloud.dto.AuthenticationRequest;
import sia.tacocloud.dto.RegistrationRequest;
import sia.tacocloud.entities.User;

public interface UserService {

    User save(User user);
    User findByUsername(String username);
    User toUser(RegistrationRequest registrationRequest);
    boolean isAuthenticated(AuthenticationRequest authenticationRequest);
}
