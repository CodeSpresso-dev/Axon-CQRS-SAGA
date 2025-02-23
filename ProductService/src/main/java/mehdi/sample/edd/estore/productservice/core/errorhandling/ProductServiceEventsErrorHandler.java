package mehdi.sample.edd.estore.productservice.core.errorhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class ProductServiceEventsErrorHandler implements ListenerInvocationErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceEventsErrorHandler.class);
    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler){
        LOGGER.error("Error processing event: {}. Event: {}", exception.getMessage(), event.getPayloadType().getSimpleName(), exception);

        throw new RuntimeException(
                String.format("Error processing event: %s. Event: %s",exception.getMessage(),event.getPayloadType().getSimpleName())
                ,exception);
    }
}
