package az.developia.CommerceApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.CommerceApp.dto.ProductDto;
import az.developia.CommerceApp.entity.Product;
import az.developia.CommerceApp.entity.UserEntity;
import az.developia.CommerceApp.repository.ProductRepository;
import az.developia.CommerceApp.repository.UserRepository;

@Service
public class UserProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	private UserEntity getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public Product saveProduct(ProductDto productDto) {
		UserEntity user = getCurrentUser();
		Product product = new Product();

		product.setModel(productDto.getModel());
		product.setBrand(productDto.getBrand());
		product.setCategory(productDto.getCategory());
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setPrice(productDto.getPrice());
		product.setRating(productDto.getRating());

		product.setOwnerId(user.getId());

		return productRepository.save(product);
	}

	public List<Product> getMyProduct() {
		UserEntity user = getCurrentUser();
		return productRepository.findByOwnerId(user.getId());
	}

	public void deleteProduct(Long id) {
		UserEntity user = getCurrentUser();
		boolean isOwner = productRepository.existsByIdAndOwnerId(id, user.getId());

		if (!isOwner) {
			throw new RuntimeException("You can't delete someone else's product.");
		}

		productRepository.deleteById(id);
	}

	public Product updateProduct(Long id, Product updatedProduct) {

		UserEntity user = getCurrentUser();
		boolean isOwner = productRepository.existsByIdAndOwnerId(id, user.getId());

		if (!isOwner) {
			throw new RuntimeException("вы не можете обновить чужую одежду");
		}

		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("clothing not found"));

		product.setModel(updatedProduct.getModel());
		product.setBrand(updatedProduct.getBrand());
		product.setCategory(updatedProduct.getCategory());
		product.setDescription(updatedProduct.getDescription());
		product.setImageUrl(updatedProduct.getImageUrl());
		product.setPrice(updatedProduct.getPrice());
		product.setRating(updatedProduct.getRating());
		product.setOwnerId(updatedProduct.getOwnerId());

		return productRepository.save(product);
	}

}