package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.UserRepository;
import com.viktor.recipebackend.structures.UserDTO;
import com.viktor.recipebackend.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<UserDTO> getTestAllUsers() {
        String hql = "select new com.viktor.recipebackend.structures.UserDTO(" +
                "u.id, u.name, u.lastname" +
                ") from User u";
        return queryService.executeHql(hql, UserDTO.class);
    }

    public List<UserDTO> getUsersSqlDto() {
        String sql = "select u.name, u.id, r.recipe_name from users as u, recipes as r " +
                "where u.id = r.id_user";
        List<Object> list = queryService.executeSql(sql);
        List<UserDTO> result = new ArrayList<>();
        for (Object item : list) {
            String name = ((Object[])item)[0].toString();
            UUID id = UUID.fromString(((Object[])item)[1].toString());
            String lastname = ((Object[])item)[2].toString();
            result.add(new UserDTO(id, name, lastname));
        }
        return result;
    }

    public List<Recipe> getRecipes() {
        String sql = "select r.* from recipes as r";
        return queryService.executeSql(sql, Recipe.class);
    }

    public User getUserByUsername(String username) {
        String sql = "select u.* from users where u.username = " + username;
        List<User> user = queryService.executeSql(sql, User.class);
        return user.size() != 0 ? user.get(0) : null;
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