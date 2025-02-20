package mehdi.sample.edd.estore.productservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products") // http://localhost:8080/products
public class ProductsController {

    private final Environment environment;

    public ProductsController(Environment environment) {
        this.environment = environment;
    }

    @PostMapping
    public String createProduct(){
        return "Http Post Handed";
    }

    @GetMapping
    public String getProduct(){
        return "Http Get Handled on service port " + environment.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct(){
        return "Http Put Handed";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "Http Delete Handed";
    }
}
