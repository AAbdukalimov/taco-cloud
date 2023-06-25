package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.dto.RegistrationRequest;
import sia.tacocloud.entities.User;
import sia.tacocloud.services.user.UserService;
import sia.tacocloud.services.user.UserServiceImpl;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationRequest registrationRequest) {
        User user = userService.toUser(registrationRequest);
        userService.save(user);
        return "redirect:/authentication";
    }

}
