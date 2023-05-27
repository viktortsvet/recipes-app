package com.viktor.recipebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chats_users", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_chat", referencedColumnName = "id"))
    private List<Chat> chats;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_liked_recipes", joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_recipe"))
    private List<Recipe> recipes;

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }
    public String getLink() {
        return "<a href=\"customer.html?id=" + getId() + " target=\"blank\">" + getName() + " " +
                "" + getLastname() + "</a>";
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}