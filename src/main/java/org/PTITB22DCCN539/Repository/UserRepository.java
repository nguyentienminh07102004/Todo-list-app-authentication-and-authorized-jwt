package org.PTITB22DCCN539.Repository;

import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
