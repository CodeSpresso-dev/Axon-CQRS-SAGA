package mehdi.sample.edd.estore.productservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record Product(
        @NotBlank(message = "Product title is a require field")
        String title,
        @Min(value = 1, message = "Price can not be lower than 1")
        BigDecimal price,
        @Min(value = 1, message = "Quantity can not be lower than 1")
        @Max(value = 5, message = "Quantity can not be larger than 5")
        Integer quantity) {
}
