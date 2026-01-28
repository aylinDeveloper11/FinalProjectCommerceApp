package az.developia.CommerceApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Brand is required")
	@Size(min = 2, max = 60, message = "Brand length must be between 2 and 60 characters")
	private String brand;

	@NotBlank(message = "Model is required")
	@Size(min = 1, max = 100, message = "Model length must be between 1 and 100 characters")
	private String model;

	@NotBlank(message = "Category is required")
	@Size(min = 3, max = 40, message = "Category length must be between 3 and 40 characters")
	private String category;

	@NotBlank(message = "Description is required")
	@Size(min = 20, max = 1000, message = "Description must be between 20 and 1000 characters")
	private String description;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
	private Double price;

	
	@NotBlank(message = "Image URL is required")
	private String imageUrl;

	@NotNull(message = "Rating is required")
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	private Integer rating;

	@NotNull(message = "Owner ID is required")
	@Positive(message = "Owner ID must be positive")
	private Long ownerId;
}
