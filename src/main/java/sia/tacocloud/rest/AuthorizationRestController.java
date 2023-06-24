package sia.tacocloud.restful;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.dto.AuthenticationRequest;
import sia.tacocloud.dto.AuthenticationResponse;
import sia.tacocloud.dto.RegistrationRequest;
import sia.tacocloud.entities.User;
import sia.tacocloud.services.user.UserService;
import sia.tacocloud.utility.JwtUtils;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api", produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class AuthorizationRestController {

    private final UserService userService;

    @Autowired
    public AuthorizationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest) {
        User user = userService.findByUserNameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        String token = JwtUtils.generateToken(user.getUsername());
        return AuthenticationResponse.builder().token(token).build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = User.builder()
                .fullname(registrationRequest.getFullname())
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .build();
        userService.save(user);
    }

}
