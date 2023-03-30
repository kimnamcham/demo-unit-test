package com.example.demounittest.repository;

import com.example.demounittest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUserName(String userName);

    boolean existsAllByUserName(String userName);

    boolean existsAllByUserNameAndIdNot(String userName, Long id);

    boolean existsAllByEmail(String email);
}
