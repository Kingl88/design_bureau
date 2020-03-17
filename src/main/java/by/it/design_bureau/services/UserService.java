package by.it.design_bureau.services;

import by.it.design_bureau.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    boolean createUser(User user);
}
