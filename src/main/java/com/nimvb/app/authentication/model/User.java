package com.nimvb.app.authentication.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
@Getter
public class User {
    @Id
    private final Long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;

    @PersistenceConstructor
    @java.beans.ConstructorProperties({"id", "email", "firstName", "lastName", "password"})
    public User(Long id, String email, String firstName, String lastName, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public static User of(String email, String firstName, String lastName, String password) {
        return new User(null, email, firstName, lastName, password);
    }
}
