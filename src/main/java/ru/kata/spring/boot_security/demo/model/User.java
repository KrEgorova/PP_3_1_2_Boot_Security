package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    @Size(min = 2, max = 10, message = "Must be in the range of 2 to 10 characters")
    private String username;
    @Column(name = "age")
    @Min(value = 1, message = "Must be in the range from 1 to 122")
    @Max(value = 122, message = "Must be in the range from 1 to 122")
    private int age;
    @Column(name = "city")
    @Size(min = 2, max = 20, message = "Must be in the range of 2 to 20 characters")
    private String city;
    @Column(name = "password")
    @NotEmpty(message = "should not be empty")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    @NotEmpty(message = "should not be empty")
    private Set<Role> roles;

    public User(String username, int age, String city) {
        this.username = username;
        this.age = age;
        this.city = city;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
