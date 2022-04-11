package com.springboot.redtest.entity.blacklist;

import com.springboot.redtest.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "BlackListEntity")
@Table(name = "blacklist")
@Setter
@Getter
@NoArgsConstructor
public class BlackListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
