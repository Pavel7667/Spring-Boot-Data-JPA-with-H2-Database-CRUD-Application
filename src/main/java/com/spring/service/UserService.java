package com.spring.service;

import com.spring.dao.UserRepository;
import com.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public User crateUser(User user) {
        return userRepo.save(user);
    }

    public List<User> crateUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        User oldUser;
        Optional<User> optionalUser = userRepo.findById(user.getId());
        if (optionalUser.isPresent()) {
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setAddress(user.getAddress());
            userRepo.save(oldUser);
        } else {
            return new User();
        }
        return oldUser;
    }

    public String deleteUserByID(int id) {
        userRepo.deleteById(id);
        return "User by " + id + " was deleted";
    }

}
