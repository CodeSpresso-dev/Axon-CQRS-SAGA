package mehdi.sample.edd.estore.productservice;

import mehdi.sample.edd.estore.productservice.command.interceptors.ProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerProductCommandInterceptor(ApplicationContext applicationContext
            , CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(applicationContext.getBean(ProductCommandInterceptor.class));
    }

}
