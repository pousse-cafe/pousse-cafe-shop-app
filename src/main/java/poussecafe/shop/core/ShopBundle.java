package poussecafe.shop.core;

import poussecafe.discovery.BundleConfigurer;
import poussecafe.shop.comm.Communication;

public class ShopBundle {

    public static BundleConfigurer configure() {
        return new BundleConfigurer.Builder()
                .module(Shop.class)
                .module(Communication.class)
                .build();
    }

    private ShopBundle() {

    }
}
