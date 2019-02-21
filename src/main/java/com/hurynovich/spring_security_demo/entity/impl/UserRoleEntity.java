package com.hurynovich.spring_security_demo.entity.impl;

import com.hurynovich.spring_security_demo.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRoleEntity extends AbstractEntity {
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
