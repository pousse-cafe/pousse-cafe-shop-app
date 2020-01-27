package poussecafe.sample;

import poussecafe.runtime.Runtime;
import poussecafe.shop.Shop;
import poussecafe.test.PousseCafeTest;

public abstract class ShopTest extends PousseCafeTest {

    @Override
    protected Runtime.Builder runtimeBuilder() {
        return super.runtimeBuilder()
                .withBundle(Shop.configure().defineAndImplementDefault().build());
    }
}
