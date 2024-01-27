package login.login.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUserRepository() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername_ValidUser() {
        String username = "user1";
        String password = "password1";
        User user = new User("user1", "password1", "USER");
        when(userRepository.findByUsername(username)).thenReturn(user);
        UserDetails userDetails = userService.loadUserByUsername(username);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_ValidAdmin() {
        String username = "admin";
        String password = "adminPass";
        User user = new User("admin", "adminPass", "ADMIN");
        when(userRepository.findByUsername(username)).thenReturn(user);
        UserDetails userDetails = userService.loadUserByUsername(username);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        verify(userRepository, times(1)).findByUsername(username);
    }
}