package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(long id);

    void editUser(User user);

    User showUserById(long id);

}
