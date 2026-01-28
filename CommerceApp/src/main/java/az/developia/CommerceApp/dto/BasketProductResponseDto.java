package az.developia.CommerceApp.dto;

import lombok.Data;

@Data
public class BasketProductResponseDto {

	private Long basketId;
	private String brand;
	private String imgUrl;
	private Double price;

	private Integer quantity;

}
