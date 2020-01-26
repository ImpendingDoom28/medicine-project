package ru.itis.medicineproject.model;

import java.sql.Date;

public class Disease {

    private Long id;
    private String name;
    private String description;
    private Date createdAtTime;
    private Long authorId;
    private String imagePath;

    private Disease() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAtTime() {
        return createdAtTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static Builder builder() {
        return (new Disease()).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Long id) {
            Disease.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Disease.this.name = name;
            return this;
        }

        public Builder setAuthorId(Long userId) {
            Disease.this.authorId = userId;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            Disease.this.imagePath = imagePath;
            return this;
        }

        public Builder setDescription(String description) {
            Disease.this.description = description;
            return this;
        }

        public Builder setCreatedAtTime(Date createdAtTime) {
            Disease.this.createdAtTime = createdAtTime;
            return this;
        }

        public Disease build() {
            return Disease.this;
        }
    }
}
