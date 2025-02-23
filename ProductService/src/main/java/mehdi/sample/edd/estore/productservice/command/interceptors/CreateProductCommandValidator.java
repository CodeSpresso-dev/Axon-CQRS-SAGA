package mehdi.sample.edd.estore.productservice.command.interceptors;

import mehdi.sample.edd.estore.productservice.command.CreateProductCommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

public class CreateProductCommandValidator implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) ->{
            CreateProductCommand createProductCommand = (CreateProductCommand) commandMessage.getPayload();
            if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Price should be greater than zero");
            }
            if (createProductCommand.getTitle() == null
                    || createProductCommand.getTitle().isBlank()) {
                throw new IllegalArgumentException("Title can not be empty");
            }
            return commandMessage;
        };
    }
}
