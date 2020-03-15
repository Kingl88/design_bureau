package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
