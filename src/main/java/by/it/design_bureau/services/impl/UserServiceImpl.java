package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Role;
import by.it.design_bureau.entities.User;
import by.it.design_bureau.repositories.UserRepository;
import by.it.design_bureau.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean createUser(User user, Role role) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(role));
        userRepository.save(user);
        return true;
    }

    @Override
    public User update(User entity, Role role) {
            User newUser = new User();
            newUser.setId(entity.getId());
            newUser.setUsername(entity.getUsername());
            newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
            newUser.setEmployee(entity.getEmployee());
            newUser.setEnabled(true);
            newUser.setAuthorities(Collections.singleton(role));
            newUser = userRepository.save(newUser);
            return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
            if(user==null){
                System.out.println(passwordEncoder.encode("admin"));
                throw new UsernameNotFoundException("User not found");
            }
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .enabled(true).build();
    }
}