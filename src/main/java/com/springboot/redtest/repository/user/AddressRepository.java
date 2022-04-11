package com.springboot.redtest.repository.user;

import com.springboot.redtest.entity.user.AddressEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    /**
     * Find an address by the user entity
     * @param userEntity
     * @return
     */
    AddressEntity findByUser(UserEntity userEntity);
}
