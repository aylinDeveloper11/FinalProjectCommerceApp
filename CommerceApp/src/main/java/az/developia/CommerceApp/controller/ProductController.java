package az.developia.CommerceApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CommerceApp.dto.ProductDto;
import az.developia.CommerceApp.entity.Product;
import az.developia.CommerceApp.service.ProductService;
import az.developia.CommerceApp.service.UserProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserProductService userProductService;

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAll();
	}

	@GetMapping("/getById/{id}")
	public Optional<Product> getById(@PathVariable Long id) {
		return productService.getById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createProduct")
	public Product createProduct(@Valid @RequestBody ProductDto productDto) {
		return userProductService.saveProduct(productDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Long id) {
		userProductService.deleteProduct(id);
	}

	@GetMapping("/search/{brand}")
	public List<Product> searchProduct(@PathVariable String brand) {
		return productService.search(brand);
	}

	@GetMapping("/rating/{rating}")
	public List<Product> getByRating(@PathVariable Integer rating) {
		return productService.getByRating(rating);
	}

	@GetMapping("/category/{category}")
	public List<Product> getByCategory(@PathVariable String category) {
		return productService.getByCategory(category);
	}

	@GetMapping("/price/asc")
	public List<Product> priceAsc() {
		return productService.getPriceAsc();

	}

	@GetMapping("/price/desc")
	public List<Product> priceDesc() {
		return productService.getPriceDesc();

	}
}
