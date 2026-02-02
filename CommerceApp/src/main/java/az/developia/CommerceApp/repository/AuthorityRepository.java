package az.developia.CommerceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.CommerceApp.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    List<AuthorityEntity> findByUsername(String username); 
}
