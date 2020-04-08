package poussecafe.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import poussecafe.messaging.Messaging;
import poussecafe.runtime.Bundles;
import poussecafe.runtime.MessagingAndStorage;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@Configuration
@ComponentScan(basePackages = { "poussecafe.spring" })
public class ApplicationConfiguration {

    @Bean
    protected Bundles bundles(
            Messaging messaging,
            SpringJpaStorage storage) {
        MessagingAndStorage messagingAndStorage = new MessagingAndStorage(messaging, storage);
        return new Bundles.Builder()
            .withBundle(ShopBundle.configure()
                .defineThenImplement()
                .messagingAndStorage(messagingAndStorage)
                .build())
            .build();
    }
}
