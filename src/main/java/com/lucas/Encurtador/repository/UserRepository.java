package com.lucas.Encurtador.repository;

import com.lucas.Encurtador.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value="SELECT u.* FROM users u WHERE u.id = :p_id",nativeQuery = true)
    User byID(@Param("p_id") Long id);


}
