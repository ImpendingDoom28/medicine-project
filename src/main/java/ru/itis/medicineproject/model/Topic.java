package ru.itis.medicineproject.model;

import java.util.List;

public class Topic {

    private String name;
    private Long id;
    private Long authorId;
    private List<Message> messages;
    private int messagesCount;
    private Message lastMessage;

    private Topic() {}

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public static Builder builder() {
        return (new Topic()).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setName(String name) {
            Topic.this.name = name;
            return this;
        }

        public Builder setAuthorId(Long authorId) {
            Topic.this.authorId = authorId;
            return this;
        }

        public Builder setId(Long id) {
            Topic.this.id = id;
            return this;
        }

        public Builder setLastMessage(Message lastMessage) {
            Topic.this.lastMessage = lastMessage;
            return this;
        }

        public Builder setMessages(List<Message> messages) {
            Topic.this.messages = messages;
            return this;
        }

        public Builder setMessagesCount(int messagesCount) {
            Topic.this.messagesCount = messagesCount;
            return this;
        }

        public Topic build() {
            return Topic.this;
        }
    }
}
