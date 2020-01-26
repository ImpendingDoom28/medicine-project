package ru.itis.medicineproject.dto;

import ru.itis.medicineproject.model.Role;
import ru.itis.medicineproject.model.User;

import java.util.List;

public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private List<Role> roles;

    private UserDto() {}

    public Builder builder() {
        return (new UserDto()).new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public static UserDto from(User user) {
        return new UserDto().builder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setRole(user.getRoles())
                .build();
    }

    private class Builder {

        private Builder() {}

        public Builder setEmail(String email) {
            UserDto.this.email = email;
            return this;
        }
        public Builder setName(String name) {
            UserDto.this.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            UserDto.this.surname = surname;
            return this;
        }
        public Builder setId(Long id) {
            UserDto.this.id = id;
            return this;
        }
        public Builder setRole(List<Role> roles) {
            UserDto.this.roles = roles;
            return this;
        }

        public UserDto build() {
            return UserDto.this;
        }

    }
}
