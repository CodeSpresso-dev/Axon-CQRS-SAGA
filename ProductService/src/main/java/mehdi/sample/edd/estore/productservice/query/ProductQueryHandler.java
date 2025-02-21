package mehdi.sample.edd.estore.productservice.query;

import mehdi.sample.edd.estore.productservice.core.data.ProductEntity;
import mehdi.sample.edd.estore.productservice.core.data.ProductRepository;
import mehdi.sample.edd.estore.productservice.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handleProducts(FindProductsQuery query) {

        List<ProductEntity> storedProducts = productRepository.findAll();

        List<ProductRestModel> productsRest = storedProducts.stream()
                .map(product -> {
                    ProductRestModel productRestModel = new ProductRestModel();
                    BeanUtils.copyProperties(product, productRestModel);
                    return productRestModel;
                })
                .collect(Collectors.toList());

        return productsRest;
    }
}
