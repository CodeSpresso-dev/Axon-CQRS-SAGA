package mehdi.sample.edd.estore.productservice.command.interceptors;

import mehdi.sample.edd.estore.productservice.command.CreateProductCommand;
import mehdi.sample.edd.estore.productservice.core.data.ProductLookupEntity;
import mehdi.sample.edd.estore.productservice.core.data.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandDuplication implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandDuplication.class);

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandDuplication(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {
            LOGGER.info("Intercepted command is : {}", commandMessage.getPayloadType());
            CreateProductCommand existCreateProductCommand = (CreateProductCommand) commandMessage.getPayload();
            ProductLookupEntity savedLookupEntity = productLookupRepository.findByProductIdOrTitle(existCreateProductCommand.getProductId()
                    , existCreateProductCommand.getTitle());
            if (savedLookupEntity != null) {
                throw new IllegalStateException(
                        String.format("Product with product id %s or title %s already exist"
                                , existCreateProductCommand.getProductId(), existCreateProductCommand.getTitle())
                );
            }
            return commandMessage;
        };
    }
}
