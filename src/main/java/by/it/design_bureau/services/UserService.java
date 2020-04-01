package by.it.design_bureau.services;

import by.it.design_bureau.entities.Role;
import by.it.design_bureau.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean createUser(User user, Role role);
    boolean deleteUser(Long id);
    User update(User user, Role role);

}
