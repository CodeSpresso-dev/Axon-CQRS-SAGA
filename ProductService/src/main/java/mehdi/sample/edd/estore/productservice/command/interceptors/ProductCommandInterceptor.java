package mehdi.sample.edd.estore.productservice.command.interceptors;

import mehdi.sample.edd.estore.productservice.command.CreateProductCommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class ProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandInterceptor.class);

    // Using a map to store validators for each command type
    private Map<Class<?>, List<MessageDispatchInterceptor<CommandMessage<?>>>> commandProcessors = new HashMap<>();

    public ProductCommandInterceptor(List<CommandProcessor<?>> commandProcessorList) {
        // Register validators dynamically
        commandProcessors.put(CreateProductCommand.class, List.of(new CreateProductCommandValidator(), new CreateProductCommandLogger()));
        // You can add more validators here without modifying existing logic

    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {
            List<MessageDispatchInterceptor<CommandMessage<?>>> processors = commandProcessors.get(commandMessage.getPayload().getClass());
            for (MessageDispatchInterceptor<CommandMessage<?>> commandProcessor : processors) {
                commandProcessor.handle(commandMessage);
            }

            return commandMessage;
        };
    }
}