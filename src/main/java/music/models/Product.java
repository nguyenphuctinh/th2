package music.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {
	@Id
	@NotBlank(message = "code is required")
	@Column(name="product_code")
	private String code;

	@NotBlank(message = "description is required")
	@Column(name="product_description")
	private String description;
	
	@Column(name="product_price")
	@NotNull(message = "price is required")
	@Min(value=0,message="Giá phải lớn hơn hoặc bằng 0!")
	private Double price;

}
