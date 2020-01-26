create table if not exists disease
(
    id              int auto_increment
        primary key,
    name            varchar(255)                       not null,
    description     longtext                           not null,
    author_id       int                                not null,
    image_path      varchar(255)                       not null,
    created_at_time datetime default CURRENT_TIMESTAMP null,
    constraint disease_name_uindex
        unique (name),
    constraint disease_user_id_fk
        foreign key (author_id) references user (id)
);

create table if not exists forum
(
    id       int auto_increment
        primary key,
    topic_id int not null,
    constraint forum_topic_id_uindex
        unique (topic_id)
);

create table if not exists message
(
    id        int auto_increment
        primary key,
    text      varchar(255)                       not null,
    author_id int                                not null,
    datetime  datetime default CURRENT_TIMESTAMP not null,
    constraint message_user_id_fk
        foreign key (author_id) references user (id)
);

create table if not exists news
(
    id              int auto_increment
        primary key,
    title           varchar(150)                       not null,
    text            longtext                           not null,
    created_at_time datetime default CURRENT_TIMESTAMP not null,
    author_id       int                                not null,
    image_path      varchar(250)                       not null,
    preview_text    varchar(70)                        not null,
    constraint news_user_id_fk
        foreign key (author_id) references user (id)
);

create table if not exists role
(
    id   int auto_increment
        primary key,
    name enum ('admin', 'user') not null
)
    charset = utf8mb4;

create table if not exists topic
(
    id             int auto_increment
        primary key,
    name           varchar(255)  not null,
    author_id      int           not null,
    last_message   varchar(255)  null,
    messages_count int default 0 not null,
    constraint topic_name_uindex
        unique (name),
    constraint topic_user_id_fk
        foreign key (author_id) references user (id)
);

create trigger topic_after_insert
    after INSERT
    on topic
    for each row
BEGIN
    INSERT INTO forum(topic_id) VALUE (NEW.id);
end;

create table if not exists topic_to_message
(
    topic_id   int not null,
    message_id int not null
);

create table if not exists user
(
    id       int auto_increment
        primary key,
    email    varchar(50)  not null,
    password varchar(150) not null,
    name     varchar(25)  null,
    surname  varchar(30)  null,
    constraint user_email_uindex
        unique (email)
)
    charset = utf8mb4;

create trigger user_after_insert
    after INSERT
    on user
    for each row
BEGIN
    INSERT INTO user_to_role(user_id, role_id) VALUE (NEW.id, 2);
end;

create table if not exists user_to_role
(
    user_id int not null,
    role_id int not null,
    constraint user_to_role_roles_id_fk
        foreign key (role_id) references role (id),
    constraint user_to_role_user_id_fk
        foreign key (user_id) references user (id)
)
    charset = utf8mb4;