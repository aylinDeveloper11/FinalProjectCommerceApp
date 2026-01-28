package az.developia.CommerceApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class AuthorityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can contain only letters, numbers, dots, underscores and hyphens")
	private String username;

	@NotBlank(message = "Authority is required")
	@Size(max = 50, message = "Authority must not exceed 50 characters")
	@Pattern(regexp = "^ROLE_[A-Z_]+$", message = "Authority must follow ROLE_NAME format")
	private String authority;
}
