package mehdi.sample.edd.estore.productservice.controller;

import mehdi.sample.edd.estore.productservice.command.CreateProductCommand;
import mehdi.sample.edd.estore.productservice.model.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products") // http://localhost:8080/products
public class ProductsController {

    private final Environment environment;
    private final CommandGateway commandGateway;

    public ProductsController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .title(product.title())
                .price(product.price())
                .quantity(product.quantity())
                .productId(UUID.randomUUID().toString()).build();

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }
        return returnValue;
    }

//    @GetMapping
//    public String getProduct() {
//        return "Http Get Handled on service port " + environment.getProperty("local.server.port");
//    }
//
//    @PutMapping
//    public String updateProduct() {
//        return "Http Put Handed";
//    }
//
//    @DeleteMapping
//    public String deleteProduct() {
//        return "Http Delete Handed";
//    }
}
