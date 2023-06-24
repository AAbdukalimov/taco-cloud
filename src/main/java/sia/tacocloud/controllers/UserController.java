package sia.tacocloud.mvc;

import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.services.user.UserServiceImpl;

@Builder
@Controller
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController (UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


}
