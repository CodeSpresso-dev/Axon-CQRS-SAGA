package mehdi.sample.edd.estore.productservice.command.interceptors;

import mehdi.sample.edd.estore.productservice.command.CreateProductCommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

public class CreateProductCommandLogger implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandLogger.class);

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {
            CreateProductCommand createProductCommand = (CreateProductCommand) commandMessage.getPayload();

            LOGGER.info("Creating product with title: {}", createProductCommand.getTitle());
            LOGGER.info("Creating product with product id: {}", createProductCommand.getProductId());

            return commandMessage;
        };
    }
}
