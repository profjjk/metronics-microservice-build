package com.metronics.metronicsmainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Table(name = "authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private Integer userId;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public enum Authority {
        ADMIN, CUSTOMER, TECHNICIAN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(userId, that.userId) && authority == that.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userId, authority);
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", authority=" + authority +
                '}';
    }
}
