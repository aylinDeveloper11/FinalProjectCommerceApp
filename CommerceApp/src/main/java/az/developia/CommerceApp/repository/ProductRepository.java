package az.developia.CommerceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.CommerceApp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByBrandContainingIgnoreCase(String brand);

	List<Product> findByRatingGreaterThanEqual(Integer rating);

	List<Product> findByCategory(String category);

	List<Product> findAllByOrderByPriceAsc();

	List<Product> findAllByOrderByPriceDesc();

	List<Product> findByOwnerId(Long ownerId);

	boolean existsByIdAndOwnerId(Long id, Long ownerId);
	
}
