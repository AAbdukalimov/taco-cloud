package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.dto.AuthenticationRequest;
import sia.tacocloud.entities.User;
import sia.tacocloud.services.user.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/authentication")
public class LoginController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public LoginController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping
    public String loginForm() {
        return "login-page";
    }

    @PostMapping("/submit")
    public String loginSubmit(AuthenticationRequest authenticationRequest) {
        if (!userService.isAuthenticated(authenticationRequest)) {
            return "auth-error";
        }
        User user = userService.findByUsername(authenticationRequest.getUsername());
        session.setAttribute("user", user);

        return "redirect:/design/page";
    }

}
