package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.UserRepository;
import com.viktor.recipebackend.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final QueryService queryService;

    @Autowired
    public UserService(UserRepository userRepository,
                       QueryService queryService) {
        this.userRepository = userRepository;
        this.queryService = queryService;
    }

    public Optional<User> getUserById(UUID idUser) {
        return userRepository.existsById(idUser) ? userRepository.findById(idUser) : Optional.empty();
    }

    public List<String> getTestAllUsers() {
        String sql = "select users.name from users";
        return queryService.executeSql(sql);
    }

    public List<User> getUsersByTheirIds(List<String> usersIds) {
        String[] ids = usersIds.toArray(new String[0]);
        String sql = "select u.* from users u where id in (" +
                QueryUtils.stringDataToStringForQuery(ids) + ")";
        return queryService.executeSql(sql, User.class);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addOrUpdateUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setLastname(user.getLastname());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
        }
    }
}