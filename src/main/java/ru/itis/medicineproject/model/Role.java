package ru.itis.medicineproject.model;

public class Role {

    String name;
    Long userId;

    private Role() {}

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return (new Role()).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setName(String name) {
            Role.this.name = name;
            return this;
        }

        public Builder setUserId(Long userId) {
            Role.this.userId = userId;
            return this;
        }

        public Role build() {
            return Role.this;
        }
    }
}
