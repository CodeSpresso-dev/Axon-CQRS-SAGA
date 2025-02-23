package mehdi.sample.edd.estore.productservice.command;

import mehdi.sample.edd.estore.productservice.core.data.ProductLookupEntity;
import mehdi.sample.edd.estore.productservice.core.data.ProductLookupRepository;
import mehdi.sample.edd.estore.productservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity lookupEntityToSave = new ProductLookupEntity(productCreatedEvent.getProductId(), productCreatedEvent.getTitle());
        productLookupRepository.save(lookupEntityToSave);
    }
}
