package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Role;
import by.it.design_bureau.entities.User;
import by.it.design_bureau.repositories.UserRepository;
import by.it.design_bureau.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user, Role role) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setEncodePassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(role));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.builder()
                .username(userRepository.findByUsername(username).getUsername())
                .password(passwordEncoder.encode(userRepository.findByUsername(username).getEncodePassword()))
                .authorities(userRepository.findByUsername(username).getAuthorities())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();
    }
}
