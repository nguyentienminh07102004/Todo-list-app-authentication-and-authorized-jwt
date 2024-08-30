package org.PTITB22DCCN539.Repository;

import org.PTITB22DCCN539.Model.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    List<RoleEntity> findAllByCodeIn(List<String> codes);
}
