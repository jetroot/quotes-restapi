package com.springboot.redtest.entity.user;

import com.springboot.redtest.entity.blacklist.BlackListEntity;
import com.springboot.redtest.entity.comment.CommentEntity;
import com.springboot.redtest.entity.dislike.DislikeEntity;
import com.springboot.redtest.entity.favorite.FavoriteEntity;
import com.springboot.redtest.entity.like.LikeEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.role.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7245253457648942050L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 16)
    private String publicUserId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = true, length = 30)
    private String image;

    @Column(nullable = false, length = 60)
    private String encryptedPassword;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<QuoteEntity> quotes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DislikeEntity> dislikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteEntity> favorites;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BlackListEntity blackList;
}
