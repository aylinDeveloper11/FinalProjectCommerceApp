package az.developia.CommerceApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.CommerceApp.entity.Product;
import az.developia.CommerceApp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Optional<Product> getById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> getByRating(Integer rating) {
		return productRepository.findByRatingGreaterThanEqual(rating);
	}

	public List<Product> search(String keyword) {
		return productRepository.findByBrandContainingIgnoreCase(keyword);
	}

	public List<Product> getByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> getPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	public List<Product> getPriceDesc() {
		return productRepository.findAllByOrderByPriceDesc();
	}

}
