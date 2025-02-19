package mehdi.sample.edd.estore.productservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products") // http://localhost:8080/products
public class ProductsController {

    @PostMapping
    public String createProduct(){
        return "Http Post Handed";
    }

    @GetMapping
    public String getProduct(){
        return "Http Get Handed";
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
