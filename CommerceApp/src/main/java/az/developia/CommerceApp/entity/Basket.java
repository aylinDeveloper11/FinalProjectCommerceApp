package az.developia.CommerceApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "basket")
@Data
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "User ID is required")
	@Positive(message = "User ID must be positive")
	private Long userId;

	@NotNull(message = "Product ID is required")
	@Positive(message = "Product ID must be positive")
	private Long productId;

	@NotNull(message = "Quantity is required")
	private Integer quantity;
}
