package com.hurynovich.spring_security_demo.repository;

import com.hurynovich.spring_security_demo.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(final String email);
}
