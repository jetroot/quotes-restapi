package com.springboot.redtest.entity.dislike;

import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "DislikeEntity")
@Table(name = "dislikes")
public class DislikeEntity implements Serializable {
    private static final long serialVersionUID = -2185796201095795609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private long dislikes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private QuoteEntity quote;

}
