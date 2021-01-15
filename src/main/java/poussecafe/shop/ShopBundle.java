package poussecafe.shop;

import poussecafe.discovery.BundleConfigurer;

public class ShopBundle {

    public static BundleConfigurer configure() {
        return new BundleConfigurer.Builder()
                .module(Shop.class)
                .build();
    }

    private ShopBundle() {

    }
}
