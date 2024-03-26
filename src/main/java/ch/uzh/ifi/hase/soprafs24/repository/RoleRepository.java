package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.constant.SD;
import ch.uzh.ifi.hase.soprafs24.entity.Role;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(SD.UserRole name);
    Boolean existsByName(SD.UserRole name);
}