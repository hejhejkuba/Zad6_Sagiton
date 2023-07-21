package login.login.home;

import jakarta.servlet.ServletException;
import login.login.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    HomeService homeService;


    @GetMapping("/home")
    String home(Authentication authentication, Model model) throws ServletException {
        return homeService.checkUserType(authentication, model);

    }

}
