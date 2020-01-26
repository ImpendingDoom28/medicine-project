package ru.itis.medicineproject.model;

import java.sql.Date;

public class Message {

    private String text;
    private Long id;
    private Date datetime;
    private User author;

    private Message() {}

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public User getAuthor() {
        return author;
    }

    public static Builder builder() {
        return (new Message()).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setText(String text) {
            Message.this.text = text;
            return this;
        }

        public Builder setId(Long id) {
            Message.this.id = id;
            return this;
        }

        public Builder setAuthor(User user) {
            Message.this.author = user;
            return this;
        }

        public Builder setDatetime(Date datetime) {
            Message.this.datetime = datetime;
            return this;
        }

        public Message build() {
            return Message.this;
        }
    }
}
