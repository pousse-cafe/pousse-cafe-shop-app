package poussecafe.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import poussecafe.journal.Journal;
import poussecafe.messaging.Messaging;
import poussecafe.runtime.Bundles;
import poussecafe.runtime.MessagingAndStorage;
import poussecafe.spring.mongo.storage.SpringMongoDbStorage;

@Configuration
@ComponentScan(basePackages = { "poussecafe.spring" })
public class AppConfiguration {

    @Bean
    protected Bundles bundles(Messaging messaging,
            SpringMongoDbStorage storage) {
        MessagingAndStorage messagingAndStorage = new MessagingAndStorage(messaging, storage);
        return new Bundles.Builder()
            .withBundle(Journal.configure()
                .defineThenImplement()
                .messagingAndStorage(messagingAndStorage)
                .build())
            .withBundle(Shop.configure()
                .defineThenImplement()
                .messagingAndStorage(messagingAndStorage)
                .build())
            .build();
    }
}
