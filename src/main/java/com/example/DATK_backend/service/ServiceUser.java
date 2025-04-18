package com.example.DATK_backend.service;
import com.example.DATK_backend.entity.User;
import com.example.DATK_backend.repository.RepositoryUser;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
    private final RepositoryUser repositoryUser;
    public ServiceUser(RepositoryUser repositoryUser) {
        this.repositoryUser= repositoryUser;
    }
    public User findUserByUserName(String userName) {
        return repositoryUser.findByUserName(userName);
    }
    public boolean existsByUserName(String userName) {
        return repositoryUser.existsByUserName(userName);
    }
}
