package com.springboot.redtest.repository.favorite;

import com.springboot.redtest.entity.favorite.FavoriteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteEntity, Long> {
}
