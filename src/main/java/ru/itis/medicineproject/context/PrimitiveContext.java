package ru.itis.medicineproject.context;

import ru.itis.medicineproject.repositories.*;
import ru.itis.medicineproject.services.*;

public class PrimitiveContext {

    public UserRepository userRepository() {
        return new UserRepositoryImpl(roleRepository());
    }

    public SignUpService signUpService() {
        return new SignUpServiceImpl(userRepository(), securityService());
    }

    public SecurityService securityService() {
        return new SecurityServiceImpl();
    }

    public SignInService signInService() {
        return new SignInServiceImpl(userRepository(), securityService());
    }

    public RoleRepository roleRepository() {
        return new RoleRepositoryImpl();
    }

    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    public TopicService topicService() {
        return new TopicServiceImpl(topicRepository(), messagesRepository());
    }

    public TopicRepository topicRepository() {
        return new TopicRepositoryImpl(messagesRepository());
    }

    public MessageService messageService() {
        return new MessageServiceImpl(messagesRepository(), userRepository(), topicRepository());
    }

    public MessagesRepository messagesRepository() {
        return new MessagesRepositoryImpl(userRepository());
    }

    public DiseaseService diseaseService() {
        return new DiseaseServiceImpl(diseaseRepository());
    }

    private DiseaseRepository diseaseRepository() {
        return new DiseaseRepositoryImpl();
    }

    public NewsService newsService() {
        return new NewsServiceImpl(newsRepository());
    }

    public NewsRepository newsRepository() {
        return new NewsRepositoryImpl();
    }

    public FileUploadService fileUploadService() {
        return new FileUploadServiceImpl(diseaseRepository(), newsRepository());
    }

    public SearchService searchService() {
        return new SearchServiceImpl(diseaseRepository());
    }
}
