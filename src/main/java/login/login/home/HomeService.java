package login.login.home;

import login.login.ForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomeService {
    public String checkUserType(Authentication authentication, Model model) {
        if (authentication == null) {
            throw new ForbiddenException("Forbidden");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authorities = userDetails.getAuthorities().toString();

        if (authorities.toString().contains("ROLE_ADMIN")) {

            return "admin";
        }
        model.addAttribute("message", authorities);
        return "home";
    }
}
