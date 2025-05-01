package com.example.DATK_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "userName", nullable = false, length = 50)
    private String userName;

    @Nationalized
    @Lob
    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Nationalized
    @Column(name = "Role", nullable = false, length = 10)
    private String role = "USER";

    @Nationalized
    @ColumnDefault("'tran'")
    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    public User() {
    }
    public User(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}