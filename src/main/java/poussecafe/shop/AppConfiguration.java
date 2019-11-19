package poussecafe.shop;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import poussecafe.environment.Bundle;
import poussecafe.journal.Journal;
import poussecafe.messaging.internal.InternalMessaging;
import poussecafe.runtime.MessagingAndStorage;
import poussecafe.spring.RuntimeConfiguration;
import poussecafe.spring.mongo.storage.SpringMongoDbStorage;

@Configuration
@ComponentScan(basePackages = { "poussecafe.spring" })
public class AppConfiguration extends RuntimeConfiguration {

    @Override
    protected List<Bundle> bundles() {
        MessagingAndStorage messagingAndStorage = new MessagingAndStorage(InternalMessaging.instance(),
                SpringMongoDbStorage.instance());
        List<Bundle> bundles = new ArrayList<>();
        bundles.add(Journal.configure()
                .defineThenImplement()
                .messagingAndStorage(messagingAndStorage)
                .build());
        bundles.add(Shop.configure()
                .defineThenImplement()
                .messagingAndStorage(messagingAndStorage)
                .build());
        return bundles;
    }
}
