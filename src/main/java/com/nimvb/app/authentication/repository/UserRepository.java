package com.nimvb.app.authentication.repository;

import com.nimvb.app.authentication.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
