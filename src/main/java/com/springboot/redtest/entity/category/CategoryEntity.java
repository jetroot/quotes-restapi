package com.springboot.redtest.entity.category;

import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.AddressEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "CategoryEntity")
@Table(name = "categories")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = -3553423778459052701L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 130, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuoteEntity> quotes;
}
