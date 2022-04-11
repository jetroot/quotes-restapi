package com.springboot.redtest.entity.role;

import com.springboot.redtest.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "RoleEntity")
@Table(name = "roles")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -5299077814427394790L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserEntity> users;

}
