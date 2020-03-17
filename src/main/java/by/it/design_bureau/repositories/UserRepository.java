package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);
    List<User> findAll();
}
