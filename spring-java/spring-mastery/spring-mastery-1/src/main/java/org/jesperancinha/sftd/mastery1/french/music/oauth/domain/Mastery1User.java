package org.jesperancinha.sftd.mastery1.french.music.oauth.domain;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Mastery1User {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    @Nullable
    private Timestamp date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Nullable
    public Timestamp getDate() {
        return date;
    }

    public void setDate(
            @Nullable
                    Timestamp date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
