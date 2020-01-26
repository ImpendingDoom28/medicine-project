package ru.itis.medicineproject.model;

import java.sql.Date;

public class News {

    private String title;
    private String text;
    private Date createdAtTime;
    private Long authorId;
    private String imagePath;
    private String previewText;
    private Long id;

    private News() {}

    public String getPreviewText() {
        return previewText;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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
        return (new News()).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Long id) {
            News.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            News.this.title = title;
            return this;
        }

        public Builder setPreviewText(String previewText) {
            News.this.previewText = previewText;
            return this;
        }

        public Builder setAuthorId(Long userId) {
            News.this.authorId = userId;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            News.this.imagePath = imagePath;
            return this;
        }

        public Builder setText(String text) {
            News.this.text = text;
            return this;
        }

        public Builder setCreatedAtTime(Date createdAtTime) {
            News.this.createdAtTime = createdAtTime;
            return this;
        }

        public News build() {
            return News.this;
        }
    }
}
