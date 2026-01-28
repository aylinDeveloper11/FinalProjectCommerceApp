package az.developia.CommerceApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.CommerceApp.entity.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findByUserId(Long userId);

    Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);

    void deleteByUserIdAndProductId(Long userId, Long productId);
}
