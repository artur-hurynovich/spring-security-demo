package com.hurynovich.spring_security_demo.repository;

import com.hurynovich.spring_security_demo.entity.impl.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByName(final String name);
}
