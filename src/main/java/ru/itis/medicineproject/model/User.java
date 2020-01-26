package ru.itis.medicineproject.model;

import java.util.List;

public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Role> roles;

    @Override
    public String toString() {
        String user = "User:{\n" +
                "id = " + id + "\n" +
                "email = " + email + "\n" +
                "password = " + password + "\n"+
                "name = " + name + "\n"+
                "surname = " + surname + "\n";
        StringBuilder builder = new StringBuilder(user);
        builder.append("roles: {\n");
        for(int i = 0; i < roles.size(); i++) {
            builder.append("Role ").append(i + 1).append(" = ").append(roles.get(i).getName()).append("\n");
        }
        builder.append("}\n").append("}\n");
        return builder.toString();
    }

    private User() {}

    public static Builder builder() {
        return (new User()).new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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



    public class Builder {

        private Builder() {}

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setName(String name) {
            User.this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            User.this.surname = surname;
            return this;
        }

        public Builder setId(Long id) {
            User.this.id = id;
            return this;
        }

        public Builder setRole(List<Role> roles) {
            User.this.roles = roles;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
