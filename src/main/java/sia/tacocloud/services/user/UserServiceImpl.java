package sia.tacocloud.services.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sia.tacocloud.dto.AuthenticationRequest;
import sia.tacocloud.dto.RegistrationRequest;
import sia.tacocloud.entities.Role;
import sia.tacocloud.entities.User;
import sia.tacocloud.repositories.UserRepository;
import sia.tacocloud.services.role.RoleService;


@Data
@Slf4j
@Service
@Builder
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User save(User user) {
        log.debug("User :{}", user);
        Role admin = roleService.findByName("ROLE_ADMIN");
        Role roleUser = roleService.findByName("ROLE_USER");
        user.setRole(roleUser);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User toUser(RegistrationRequest registrationRequest) {
        return User.builder()
          .username(registrationRequest.getUsername())
          .password(passwordEncoder.encode(registrationRequest.getPassword()))
          .fullname(registrationRequest.getFullname())
          .street(registrationRequest.getStreet())
          .city(registrationRequest.getCity())
          .state(registrationRequest.getState())
          .zip(registrationRequest.getZip())
          .phoneNumber(registrationRequest.getPhoneNumber())
          .build();
    }

    @Override
    public boolean isAuthenticated(AuthenticationRequest authenticationRequest) {
        if (userRepository.findByUsername(authenticationRequest.getUsername()) != null) {
            User user = userRepository.findByUsername(authenticationRequest.getUsername());
            return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        }
        return false;
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
