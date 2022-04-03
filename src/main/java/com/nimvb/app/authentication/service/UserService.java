package com.nimvb.app.authentication.service;

import com.nimvb.app.authentication.model.User;
import com.nimvb.app.authentication.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void create(@NonNull User user){
        Assert.isNull(user.getId(),"id should be null");
        repository.save(user);
    }

    public User find(@NonNull String email){
        return repository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public List<User> find(){
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public void delete(@NonNull String email) {
        repository.delete(find(email));
    }
}
