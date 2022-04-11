package com.springboot.redtest.entity.quote;

import com.springboot.redtest.entity.category.CategoryEntity;
import com.springboot.redtest.entity.comment.CommentEntity;
import com.springboot.redtest.entity.dislike.DislikeEntity;
import com.springboot.redtest.entity.favorite.FavoriteEntity;
import com.springboot.redtest.entity.like.LikeEntity;
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
@Entity(name = "QuoteEntity")
@Table(name = "quotes")
public class QuoteEntity implements Serializable {
    private static final long serialVersionUID = 2134797906070089785L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 16)
    private String publicQuoteId;

    @Column(nullable = false, length = 700)
    private String quote;

    @Column(nullable = false, length = 100)
    private String quoteSource;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<DislikeEntity> dislikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteEntity> favorites;
}
