package com.springboot.redtest.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "AddressEntity")
@Table(name = "addresses")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = -782836062780825656L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 40)
    private String country;

    @Column(nullable = false, length = 40)
    private String city;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
