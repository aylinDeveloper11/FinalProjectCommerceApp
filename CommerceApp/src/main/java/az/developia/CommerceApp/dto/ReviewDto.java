package az.developia.CommerceApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDto {

	@NotBlank(message = "Comment is required")
	@Size(min = 2, max = 1000, message = "Comment must be between 2 and 1000 characters")
	private String comment;

	@NotNull(message = "Product ID is required")
	@Min(value = 1, message = "Product ID must be positive")
	private Long productId;

	@NotNull(message = "Rating is required")
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	private Integer rating;

}