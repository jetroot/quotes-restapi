package com.springboot.redtest.entity.favorite;

import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "FavoriteEntity")
@Table(name = "favorites")
public class FavoriteEntity implements Serializable {
    private static final long serialVersionUID = -8326503119123645822L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private QuoteEntity quote;
}
