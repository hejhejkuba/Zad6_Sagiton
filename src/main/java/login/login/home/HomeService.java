package login.login.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HomeService {
    public String checkUserType(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authorities = userDetails.getAuthorities().toString();
        List<Strategy> strategies = List.of(new AdminLoginStrategy(authentication, model));
        return strategies.stream().filter(strategy -> strategy.isApplicable(authorities)).findFirst().orElse(new UserLoginStrategy(authentication, model)).doStrategy();
//        for (Strategy strategy : strategies) {
//            if (strategy.isApplicable(authorities)) {
//                return strategy.doStrategy();
//            }
//        }
//        return new UserLoginStrategy(authentication, model).doStrategy();
    }
}
